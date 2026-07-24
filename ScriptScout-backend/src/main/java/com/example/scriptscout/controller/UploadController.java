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
        File localFile = saveUploadedFile(file);
        Video savedVideo = createAndSaveInitialVideo(title, description, category, file.getOriginalFilename());

        PythonAiService.AiResponse aiResponse = pythonAiService.uploadAndAnalyze(localFile);
        if (aiResponse != null) {
            savedVideo = processAiResponse(savedVideo, aiResponse);
        }

        return savedVideo;
    }

    private File saveUploadedFile(MultipartFile file) throws IOException {
        String uploadDir = "uploads/";
        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        File localFile = new File(uploadDir + fileName);
        file.transferTo(localFile);
        return localFile;
    }

    private Video createAndSaveInitialVideo(String title, String description, String category, String fileName) {
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setCategory(category);
        video.setFileName(fileName);
        return uploadService.uploadVideo(video);
    }

    private Video processAiResponse(Video savedVideo, PythonAiService.AiResponse aiResponse) {
        if (aiResponse.getTranscript() != null) {
            saveTranscript(savedVideo.getId(), aiResponse.getTranscript());
        }
        if (aiResponse.getMetadata() != null) {
            return processMetadata(savedVideo, aiResponse.getMetadata());
        }
        return savedVideo;
    }

    private void saveTranscript(Long videoId, String transcriptText) {
        Transcript transcript = new Transcript();
        transcript.setVideoId(videoId);
        transcript.setTranscriptText(transcriptText);
        transcriptRepository.save(transcript);
    }

    private Video processMetadata(Video savedVideo, String rawMetadata) {
        PythonAiService.ParsedMetadata pm = pythonAiService.parseMetadata(rawMetadata);
        MetadataEditor metadataEditor = new MetadataEditor();
        metadataEditor.setId(savedVideo.getId());
        metadataEditor.setTitle(savedVideo.getTitle());
        metadataEditor.setDescription(pm.getSummary() != null ? pm.getSummary() : savedVideo.getDescription());
        metadataEditor.setCategory(pm.getGenre() != null ? pm.getGenre() : savedVideo.getCategory());
        metadataEditor.setLanguage(pm.getLanguage());
        metadataEditor.setTags(pm.getTags());
        metadataEditorRepository.save(metadataEditor);

        if (pm.getSummary() != null) {
            savedVideo.setDescription(pm.getSummary());
        }
        if (pm.getGenre() != null) {
            savedVideo.setCategory(pm.getGenre());
        }
        return uploadService.uploadVideo(savedVideo);
    }
}