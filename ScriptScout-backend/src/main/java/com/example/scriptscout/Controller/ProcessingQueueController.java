
package com.example.scriptscout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.models.ProcessingQueue;
import com.example.scriptscout.service.ProcessingQueueService;

@RestController
@RequestMapping("/api/processingqueue")
@CrossOrigin(origins = "*")
public class ProcessingQueueController {

    @Autowired
    private ProcessingQueueService processingQueueService;

    @PostMapping
    public ProcessingQueue saveProcessingQueue(
            @RequestBody ProcessingQueue processingQueue) {
        return processingQueueService.saveProcessingQueue(processingQueue);
    }

    @GetMapping
    public List<ProcessingQueue> getAllProcessingQueues() {
        return processingQueueService.getAllProcessingQueues();
    }

    @GetMapping("/{id}")
    public ProcessingQueue getProcessingQueueById(@PathVariable Long id) {
        return processingQueueService.getProcessingQueueById(id);
    }

    @PutMapping("/{id}")
    public ProcessingQueue updateProcessingQueue(
            @PathVariable Long id,
            @RequestBody ProcessingQueue processingQueue) {

        return processingQueueService.updateProcessingQueue(id, processingQueue);
    }

    @DeleteMapping("/{id}")
    public String deleteProcessingQueue(@PathVariable Long id) {
        return processingQueueService.deleteProcessingQueue(id);
    }
}