package com.example.scriptscout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.Chapterbreakdown;

@Repository
public interface ChapterbreakdownRepository extends JpaRepository<Chapterbreakdown, Long>{

}
