package com.example.scriptscout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptscout.models.ProcessingQueue;

@Repository
public interface ProcessingQueueRepository extends JpaRepository<ProcessingQueue, Long>{

}
