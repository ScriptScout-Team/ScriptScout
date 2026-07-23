package com.example.scriptscout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.Transcript;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, Long> {

}
