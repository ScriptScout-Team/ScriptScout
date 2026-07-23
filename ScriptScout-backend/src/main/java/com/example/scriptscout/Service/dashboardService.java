package com.example.scriptscout.service;

import org.springframework.stereotype.Service;

@Service
public class dashboardService {

    public String getDashboardMessage() {
        return "Welcome to ScriptScout Dashboard";
    }

}