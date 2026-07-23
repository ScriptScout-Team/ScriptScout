package com.example.scriptscout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.MetadataEditor;
import com.example.scriptscout.repository.MetadataEditorRepository;

@Service
public class MetadataEditorService {

    @Autowired
    private MetadataEditorRepository metadataEditorRepository;

    public MetadataEditor saveMetadata(MetadataEditor metadataEditor) {
        return metadataEditorRepository.save(metadataEditor);
    }

    public List<MetadataEditor> getAllMetadata() {
        return metadataEditorRepository.findAll();
    }

    public MetadataEditor getMetadataById(Long id) {
        return metadataEditorRepository.findById(id).orElse(null);
    }

    public MetadataEditor updateMetadata(Long id, MetadataEditor metadataEditor) {

        MetadataEditor existingMetadata =
                metadataEditorRepository.findById(id).orElse(null);

        if (existingMetadata != null) {

            existingMetadata.setTitle(metadataEditor.getTitle());
            existingMetadata.setDescription(metadataEditor.getDescription());
            existingMetadata.setCategory(metadataEditor.getCategory());
            existingMetadata.setLanguage(metadataEditor.getLanguage());
            existingMetadata.setTags(metadataEditor.getTags());

            return metadataEditorRepository.save(existingMetadata);
        }

        return null;
    }

    public String deleteMetadata(Long id) {

        if (metadataEditorRepository.existsById(id)) {
            metadataEditorRepository.deleteById(id);
            return "Metadata Deleted Successfully";
        }

        return "Metadata Not Found";
    }
}