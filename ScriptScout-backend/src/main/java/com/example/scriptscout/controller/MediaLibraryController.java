package com.example.scriptscout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.service.MediaLibraryService;

@RestController
@RequestMapping("/api/library")
public class MediaLibraryController {

    @Autowired
    private MediaLibraryService mediaLibraryService;

    @GetMapping("/all")
    public List<Video> getAllVideos() {
        return mediaLibraryService.getAllVideos();
    }

}