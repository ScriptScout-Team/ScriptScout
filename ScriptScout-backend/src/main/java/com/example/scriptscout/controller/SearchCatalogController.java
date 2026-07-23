package com.example.scriptscout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.models.Video;
import com.example.scriptscout.service.SearchCatalogService;

@RestController
@RequestMapping("/api/searchcatalog")
@CrossOrigin(origins = "*")
public class SearchCatalogController {

    @Autowired
    private SearchCatalogService searchCatalogService;
    @GetMapping("/all")
    public List<Video> getAllVideos() {
        return searchCatalogService.getAllVideos();
    }
    @GetMapping("/title/{title}")
    public List<Video> searchByTitle(@PathVariable String title) {
        return searchCatalogService.searchByTitle(title);
    }
    @GetMapping("/category/{category}")
    public List<Video> searchByCategory(@PathVariable String category) {
        return searchCatalogService.searchByCategory(category);
    }
    @GetMapping("/filename/{fileName}")
    public List<Video> searchByFileName(@PathVariable String fileName) {
        return searchCatalogService.searchByFileName(fileName);
    }
}