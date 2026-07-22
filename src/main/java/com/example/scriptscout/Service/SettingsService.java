package com.example.scriptscout.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Settings;
import com.example.scriptscout.repository.SettingsRepository;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;
    public Settings saveSettings(Settings settings) {
        return settingsRepository.save(settings);
    }
    public List<Settings> getAllSettings() {
        return settingsRepository.findAll();
    }
    public Settings getSettingsById(Long id) {
        return settingsRepository.findById(id).orElse(null);
    }
    public Settings updateSettings(Long id, Settings settings) {

        Settings existingSettings = settingsRepository.findById(id).orElse(null);

        if (existingSettings != null) {

            existingSettings.setTheme(settings.getTheme());
            existingSettings.setLanguage(settings.getLanguage());
            existingSettings.setNotifications(settings.isNotifications());
            existingSettings.setTimezone(settings.getTimezone());

            return settingsRepository.save(existingSettings);
        }

        return null;
    }
    public String deleteSettings(Long id) {

        if (settingsRepository.existsById(id)){
            settingsRepository.deleteById(id);
            return "Settings Deleted Successfully";
        }

        return "Settings Not Found";
    }
}