package com.example.scriptscout.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Transcript;
import com.example.scriptscout.repository.TranscriptRepository;

@Service
public class TranscriptService {

    @Autowired
    private TranscriptRepository transcriptRepository;
    public Transcript saveTranscript(Transcript transcript) {
        return transcriptRepository.save(transcript);
    }
    public List<Transcript> getAllTranscripts() {
        return transcriptRepository.findAll();
    }
    public Transcript getTranscriptById(Long id) {
        return transcriptRepository.findById(id).orElse(null);
    }
    public String deleteTranscript(Long id) {

        if (transcriptRepository.existsById(id)) {
            transcriptRepository.deleteById(id);
            return "Transcript Deleted Successfully";
        }

        return "Transcript Not Found";
    }
}