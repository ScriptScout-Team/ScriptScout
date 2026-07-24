package com.example.scriptscout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.Services;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>{

}
