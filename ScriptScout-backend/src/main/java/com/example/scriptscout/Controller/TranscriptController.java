package com.example.scriptscout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.models.Transcript;
import com.example.scriptscout.service.TranscriptService;

@RestController
@RequestMapping("/api/transcript")
public class TranscriptController {

    @Autowired
    private TranscriptService transcriptService;
    @PostMapping("/save")
    public Transcript saveTranscript(@RequestBody Transcript transcript) {
        return transcriptService.saveTranscript(transcript);
    }
    @GetMapping("/all")
    public List<Transcript> getAllTranscripts() {
        return transcriptService.getAllTranscripts();
    }
    @GetMapping("/{id}")
    public Transcript getTranscriptById(@PathVariable Long id) {
        return transcriptService.getTranscriptById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteTranscript(@PathVariable Long id) {
        return transcriptService.deleteTranscript(id);
    }
}