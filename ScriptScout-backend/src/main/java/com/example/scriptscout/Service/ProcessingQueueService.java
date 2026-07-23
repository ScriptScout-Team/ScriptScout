package com.example.scriptscout.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.ProcessingQueue;
import com.example.scriptscout.repository.ProcessingQueueRepository;

@Service
public class ProcessingQueueService {

    @Autowired
    private ProcessingQueueRepository processingQueueRepository;
    public ProcessingQueue saveProcessingQueue(ProcessingQueue processingQueue) {
        return processingQueueRepository.save(processingQueue);
    }

    
    public List<ProcessingQueue> getAllProcessingQueues() {
        return processingQueueRepository.findAll();
    }
    public ProcessingQueue getProcessingQueueById(Long id) {
        return processingQueueRepository.findById(id).orElse(null);
    }
    public ProcessingQueue updateProcessingQueue(Long id,
            ProcessingQueue processingQueue) {

        ProcessingQueue existingQueue =
                processingQueueRepository.findById(id).orElse(null);

        if (existingQueue != null) {

            existingQueue.setVideoName(processingQueue.getVideoName());
            existingQueue.setStatus(processingQueue.getStatus());
            existingQueue.setProgress(processingQueue.getProgress());
            existingQueue.setStartedTime(processingQueue.getStartedTime());
            existingQueue.setCompletedTime(processingQueue.getCompletedTime());

            return processingQueueRepository.save(existingQueue);
        }

        return null;
    }
    public String deleteProcessingQueue(Long id) {

        if (processingQueueRepository.existsById(id)) {
            processingQueueRepository.deleteById(id);
            return "Processing Queue Deleted Successfully";
        }

        return "Processing Queue Not Found";
    }
}