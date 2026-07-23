package com.example.scriptscout.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.repository.VideoRepository;

@Service
public class MediaLibraryService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

}