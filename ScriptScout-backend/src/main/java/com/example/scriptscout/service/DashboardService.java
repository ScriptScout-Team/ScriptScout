package com.example.scriptscout.service;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private static final String DASHBOARD_MESSAGE = "Welcome to ScriptScout Dashboard";

    public String getDashboardMessage() {
        return DASHBOARD_MESSAGE;
    }

}