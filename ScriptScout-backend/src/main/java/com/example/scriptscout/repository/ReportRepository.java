package com.example.scriptscout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{

}
