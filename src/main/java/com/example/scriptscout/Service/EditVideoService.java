package com.example.scriptscout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.repository.VideoRepository;

@Service
public class EditVideoService {

    @Autowired
    private VideoRepository videoRepository;
    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }
    public Video updateVideo(Long id, Video video) {

        Video existingVideo = videoRepository.findById(id).orElse(null);

        if (existingVideo != null) {

            existingVideo.setTitle(video.getTitle());
            existingVideo.setDescription(video.getDescription());
            existingVideo.setCategory(video.getCategory());
            existingVideo.setFileName(video.getFileName());

            return videoRepository.save(existingVideo);
        }

        return null;
    }
}