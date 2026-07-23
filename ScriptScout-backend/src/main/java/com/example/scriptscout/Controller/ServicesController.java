package com.example.scriptscout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.models.Services;
import com.example.scriptscout.service.ServicesService;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;
    @PostMapping("/save")
    public Services saveService(@RequestBody Services service) {
        return servicesService.saveService(service);
    }
    @GetMapping("/all")
    public List<Services> getAllServices() {
        return servicesService.getAllServices();
    }
    @GetMapping("/{id}")
    public Services getServiceById(@PathVariable Long id) {
        return servicesService.getServiceById(id);
    }
    @PutMapping("/update/{id}")
    public Services updateService(@PathVariable Long id,
                                  @RequestBody Services service) {
        return servicesService.updateService(id, service);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        return servicesService.deleteService(id);
    }
}