package com.example.scriptscout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.Service.EditVideoService;
import com.example.scriptscout.models.Video;

@RestController
@RequestMapping("/api/editvideo")
@CrossOrigin(origins = "*")
public class EditVideoController {

    @Autowired
    private EditVideoService editVideoService;
    @GetMapping("/{id}")
    public Video getVideoById(@PathVariable Long id) {
        return editVideoService.getVideoById(id);
    }
    @PutMapping("/{id}")
    public Video updateVideo(@PathVariable Long id, @RequestBody Video video) {
        return editVideoService.updateVideo(id, video);
    }
}