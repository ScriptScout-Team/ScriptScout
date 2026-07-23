package com.example.scriptscout.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.models.Chapterbreakdown;
import com.example.scriptscout.service.Chapterbreakdownservice;

@RestController
@RequestMapping("/api/chapterbreakdown")
@CrossOrigin(origins = "*")
public class Chapterbreakdowncontroller {
	@Autowired
	private Chapterbreakdownservice chapterBreakdownService;

	@PostMapping
	public Chapterbreakdown saveChapter(@RequestBody Chapterbreakdown chapterBreakdown) {
		return chapterBreakdownService.saveChapter(chapterBreakdown);
	}

	@GetMapping
	public List<Chapterbreakdown> getAllChapters() {
		return chapterBreakdownService.getAllChapters();
	}

	@GetMapping("/{id}")
	public Chapterbreakdown getChapterById(@PathVariable Long id) {
		return chapterBreakdownService.getChapterById(id);
	}

	@PutMapping("/{id}")
	public Chapterbreakdown updateChapter(@PathVariable Long id, @RequestBody Chapterbreakdown chapterBreakdown) {
		return chapterBreakdownService.updateChapter(id, chapterBreakdown);
	}

	@DeleteMapping("/{id}")
	public String deleteChapter(@PathVariable Long id) {
		return chapterBreakdownService.deleteChapter(id);
	}
}