package com.example.scriptscout.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.repository.VideoRepository;

@Service
public class SearchCatalogService {

    @Autowired
    private VideoRepository videoRepository;
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }
    public List<Video> searchByTitle(String title) {
        return videoRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Video> searchByCategory(String category) {
        return videoRepository.findByCategoryContainingIgnoreCase(category);
    }
    public List<Video> searchByFileName(String fileName) {
        return videoRepository.findByFileNameContainingIgnoreCase(fileName);
    }
}