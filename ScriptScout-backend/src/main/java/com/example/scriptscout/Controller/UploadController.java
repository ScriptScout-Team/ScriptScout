package com.example.scriptscout.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.scriptscout.Service.UploadService;
import com.example.scriptscout.models.Video;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private UploadService uploadService;

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

        file.transferTo(new File(uploadDir + fileName));

        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setCategory(category);
        video.setFileName(fileName);

        return uploadService.uploadVideo(video);
    }
}