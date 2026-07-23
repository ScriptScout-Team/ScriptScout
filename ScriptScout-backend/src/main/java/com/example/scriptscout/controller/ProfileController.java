package com.example.scriptscout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.models.Profile;
import com.example.scriptscout.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @PostMapping("/save")
    public Profile saveProfile(@RequestBody Profile profile) {
        return profileService.saveProfile(profile);
    }
    @GetMapping("/all")
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }
    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }
    @PutMapping("/update/{id}")
    public Profile updateProfile(@PathVariable Long id,@RequestBody Profile profile) {
        return profileService.updateProfile(id, profile);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProfile(@PathVariable Long id) {
        return profileService.deleteProfile(id);
    }

}