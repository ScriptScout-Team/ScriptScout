package com.example.scriptscout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Chapterbreakdown;
import com.example.scriptscout.repository.ChapterbreakdownRepository;

@Service
public class Chapterbreakdownservice {

    @Autowired
    private ChapterbreakdownRepository chapterBreakdownRepository;

    public Chapterbreakdown saveChapter(Chapterbreakdown chapterBreakdown) {
        return chapterBreakdownRepository.save(chapterBreakdown);
    }

    public List<Chapterbreakdown> getAllChapters() {
        return chapterBreakdownRepository.findAll();
    }

    public Chapterbreakdown getChapterById(Long id) {
        return chapterBreakdownRepository.findById(id).orElse(null);
    }

    public Chapterbreakdown updateChapter(Long id, Chapterbreakdown chapterBreakdown) {

        Chapterbreakdown existingChapter =
                chapterBreakdownRepository.findById(id).orElse(null);

        if (existingChapter != null) {
        	existingChapter.setChapterTitle(chapterBreakdown.getChapterTitle());
            existingChapter.setDescription(chapterBreakdown.getDescription());
            existingChapter.setStartTime(chapterBreakdown.getStartTime());
            existingChapter.setEndTime(chapterBreakdown.getEndTime());

            return chapterBreakdownRepository.save(existingChapter);
        }

        return null;
    }

    public String deleteChapter(Long id) {
    	
        if (chapterBreakdownRepository.existsById(id)) {
            chapterBreakdownRepository.deleteById(id);
            return "Chapter Deleted Successfully";
        }

        return "Chapter Not Found";
    }
}