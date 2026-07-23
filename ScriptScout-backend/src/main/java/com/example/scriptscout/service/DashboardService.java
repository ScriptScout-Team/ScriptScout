package com.example.scriptscout.service;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    public String getDashboardMessage() {
        return "Welcome to ScriptScout Dashboard";
    }

}