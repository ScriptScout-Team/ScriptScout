package com.example.scriptscout.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.models.Transcript;
import com.example.scriptscout.models.MetadataEditor;
import com.example.scriptscout.service.UploadService;
import com.example.scriptscout.service.PythonAiService;
import com.example.scriptscout.repository.TranscriptRepository;
import com.example.scriptscout.repository.MetadataEditorRepository;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private PythonAiService pythonAiService;

    @Autowired
    private TranscriptRepository transcriptRepository;

    @Autowired
    private MetadataEditorRepository metadataEditorRepository;

    @PostMapping
    public Video uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category) throws IOException {
        String uploadDir = "uploads/";

        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        File localFile = new File(uploadDir + fileName);
        file.transferTo(localFile);

        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setCategory(category);
        video.setFileName(fileName);

        Video savedVideo = uploadService.uploadVideo(video);

        // Call the Python AI service
        PythonAiService.AiResponse aiResponse = pythonAiService.uploadAndAnalyze(localFile);
        if (aiResponse != null) {
            // Save transcript
            if (aiResponse.getTranscript() != null) {
                Transcript transcript = new Transcript();
                transcript.setVideoId(savedVideo.getId());
                transcript.setTranscriptText(aiResponse.getTranscript());
                transcriptRepository.save(transcript);
            }

            // Parse and save metadata
            if (aiResponse.getMetadata() != null) {
                PythonAiService.ParsedMetadata pm = pythonAiService.parseMetadata(aiResponse.getMetadata());
                MetadataEditor metadataEditor = new MetadataEditor();
                metadataEditor.setId(savedVideo.getId());
                metadataEditor.setTitle(savedVideo.getTitle());
                metadataEditor.setDescription(pm.getSummary() != null ? pm.getSummary() : savedVideo.getDescription());
                metadataEditor.setCategory(pm.getGenre() != null ? pm.getGenre() : savedVideo.getCategory());
                metadataEditor.setLanguage(pm.getLanguage());
                metadataEditor.setTags(pm.getTags());
                metadataEditorRepository.save(metadataEditor);

                // Update the video with parsed metadata
                if (pm.getSummary() != null) {
                    savedVideo.setDescription(pm.getSummary());
                }
                if (pm.getGenre() != null) {
                    savedVideo.setCategory(pm.getGenre());
                }
                savedVideo = uploadService.uploadVideo(savedVideo);
            }
        }

        return savedVideo;
    }
}