package com.example.scriptscout.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.Service.MetadataEditorService;
import com.example.scriptscout.models.MetadataEditor;

@RestController
@RequestMapping("/api/metadataeditor")
@CrossOrigin(origins = "*")
public class MetadataEditorController {

    @Autowired
    private MetadataEditorService metadataEditorService;

    @PostMapping
    public MetadataEditor saveMetadata(@RequestBody MetadataEditor metadataEditor) {
        return metadataEditorService.saveMetadata(metadataEditor);
    }

    @GetMapping
    public List<MetadataEditor> getAllMetadata() {
        return metadataEditorService.getAllMetadata();
    }

    @GetMapping("/{id}")
    public MetadataEditor getMetadataById(@PathVariable Long id) {
        return metadataEditorService.getMetadataById(id);
    }

    @PutMapping("/{id}")
    public MetadataEditor updateMetadata(@PathVariable Long id,@RequestBody MetadataEditor metadataEditor) {
        return metadataEditorService.updateMetadata(id, metadataEditor);
    }

    @DeleteMapping("/{id}")
    public String deleteMetadata(@PathVariable Long id) {
        return metadataEditorService.deleteMetadata(id);
    }
}