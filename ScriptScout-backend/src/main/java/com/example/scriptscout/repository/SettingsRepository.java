package com.example.scriptscout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long>{

}
