package com.example.scriptscout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.models.Tag;
import com.example.scriptscout.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;
    @PostMapping("/save")
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }
    @GetMapping("/all")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }
    @PutMapping("/update/{id}")
    public Tag updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        return tagService.updateTag(id, tag);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
}