package com.example.scriptscout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.repository.VideoRepository;

@Service
public class VideoPlayerService {

    @Autowired
    private VideoRepository videoRepository;

    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

}