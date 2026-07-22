package com.example.scriptscout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.MetadataEditor;

@Repository
public interface MetadataEditorRepository extends JpaRepository<MetadataEditor, Long> {

}
