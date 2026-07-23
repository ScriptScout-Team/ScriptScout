package com.example.scriptscout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.service.VideoPlayerService;

@RestController
@RequestMapping("/api/videoplayer")
public class VideoPlayerController {

    @Autowired
    private VideoPlayerService videoPlayerService;

    @GetMapping("/{id}")
    public Video playVideo(@PathVariable Long id) {
        return videoPlayerService.getVideoById(id);
    }

}