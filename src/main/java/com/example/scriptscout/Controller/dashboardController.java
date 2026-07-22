package com.example.scriptscout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scriptscout.Service.dashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class dashboardController {

    @Autowired
    private dashboardService dashboardService;

    @GetMapping
    public String dashboard() {
        return dashboardService.getDashboardMessage();
    }

}