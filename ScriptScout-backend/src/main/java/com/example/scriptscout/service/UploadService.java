package com.example.scriptscout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.repository.VideoRepository;

@Service
public class UploadService {
    @Autowired
    private VideoRepository videoRepository;
    public Video uploadVideo(Video video) {
        return videoRepository.save(video);
    }
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }
    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }
    public String deleteVideo(Long id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return "Video Deleted Successfully";
        }

        return "Video Not Found";
    }
    

}