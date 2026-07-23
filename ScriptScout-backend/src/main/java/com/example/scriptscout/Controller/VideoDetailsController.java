package com.example.scriptscout.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.Service.VideoDetailsService;
import com.example.scriptscout.models.Video;

@RestController
@RequestMapping("/api/videodetails")
@CrossOrigin(origins = "*")
public class VideoDetailsController {

    @Autowired
    private VideoDetailsService videoDetailsService;
    @GetMapping
    public List<Video> getAllVideos() {
        return videoDetailsService.getAllVideos();
    }
    @GetMapping("/{id}")
    public Video getVideoById(@PathVariable Long id) {
        return videoDetailsService.getVideoById(id);
    }
    @PutMapping("/{id}")
    public Video updateVideo(@PathVariable Long id, @RequestBody Video video) {
        return videoDetailsService.updateVideo(id, video);
    }
    @DeleteMapping("/{id}")
    public String deleteVideo(@PathVariable Long id) {
        return videoDetailsService.deleteVideo(id);
    }
}