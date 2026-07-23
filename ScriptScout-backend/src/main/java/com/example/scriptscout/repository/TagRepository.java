package com.example.scriptscout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
