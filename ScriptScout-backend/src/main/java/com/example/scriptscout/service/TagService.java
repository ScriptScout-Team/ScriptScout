package com.example.scriptscout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Tag;
import com.example.scriptscout.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }
    public Tag updateTag(Long id, Tag tag) {

        Tag existingTag = tagRepository.findById(id).orElse(null);

        if (existingTag != null) {
            existingTag.setTagName(tag.getTagName());
            existingTag.setKeyword(tag.getKeyword());

            return tagRepository.save(existingTag);
        }

        return null;
    }
    public String deleteTag(Long id) {

        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return "Tag Deleted Successfully";
        }

        return "Tag Not Found";
    }
}