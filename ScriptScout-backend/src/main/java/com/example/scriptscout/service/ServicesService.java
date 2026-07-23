package com.example.scriptscout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Services;
import com.example.scriptscout.repository.ServicesRepository;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;
    public Services saveService(Services service) {
        return servicesRepository.save(service);
    }
    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }
    public Services getServiceById(Long id) {
        return servicesRepository.findById(id).orElse(null);
    }
    public Services updateService(Long id, Services service) {

        Services existingService = servicesRepository.findById(id).orElse(null);

        if (existingService != null) {

            existingService.setServiceName(service.getServiceName());
            existingService.setDescription(service.getDescription());
            existingService.setStatus(service.getStatus());

            return servicesRepository.save(existingService);
        }

        return null;
    }
    public String deleteService(Long id) {

        if (servicesRepository.existsById(id)) {
            servicesRepository.deleteById(id);
            return "Service Deleted Successfully";
        }

        return "Service Not Found";
    }
}