package com.example.scriptscout.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.Service.SettingsService;
import com.example.scriptscout.models.Settings;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "*")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @PostMapping
    public Settings saveSettings(@RequestBody Settings settings) {
        return settingsService.saveSettings(settings);
    }

    @GetMapping
    public List<Settings> getAllSettings() {
        return settingsService.getAllSettings();
    }

    @GetMapping("/{id}")
    public Settings getSettingsById(@PathVariable Long id) {
        return settingsService.getSettingsById(id);
    }

    @PutMapping("/{id}")
    public Settings updateSettings(@PathVariable Long id,@RequestBody Settings settings) {
        return settingsService.updateSettings(id, settings);
    }

    @DeleteMapping("/{id}")
    public String deleteSettings(@PathVariable Long id) {
        return settingsService.deleteSettings(id);
    }
}