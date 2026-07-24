package com.example.scriptscout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
	List<Video> findByTitleContainingIgnoreCase(String title);

	List<Video> findByCategoryContainingIgnoreCase(String category);

	List<Video> findByFileNameContainingIgnoreCase(String fileName);

}
