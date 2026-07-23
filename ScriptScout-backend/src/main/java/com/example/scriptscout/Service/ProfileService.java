package com.example.scriptscout.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Profile;
import com.example.scriptscout.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }
    public Profile updateProfile(Long id, Profile profile) {

        Profile existingProfile = profileRepository.findById(id).orElse(null);

        if (existingProfile != null) {
            existingProfile.setFullName(profile.getFullName());
            existingProfile.setEmail(profile.getEmail());
            existingProfile.setPhoneNumber(profile.getPhoneNumber());
            existingProfile.setAddress(profile.getAddress());

            return profileRepository.save(existingProfile);
        }

        return null;
    }
    public String deleteProfile(Long id) {

        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return "Profile Deleted Successfully";
        }

        return "Profile Not Found";
    }

}