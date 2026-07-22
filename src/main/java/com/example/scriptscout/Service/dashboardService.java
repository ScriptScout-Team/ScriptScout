package com.example.scriptscout.Service;

import org.springframework.stereotype.Service;

@Service
public class dashboardService {

    public String getDashboardMessage() {
        return "Welcome to ScriptScout Dashboard";
    }

}