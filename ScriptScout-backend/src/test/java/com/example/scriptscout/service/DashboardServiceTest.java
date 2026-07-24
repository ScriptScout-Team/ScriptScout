package com.example.scriptscout.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DashboardServiceTest {

    private final DashboardService dashboardService = new DashboardService();

    @Test
    void testGetDashboardMessage() {
        String message = dashboardService.getDashboardMessage();
        assertNotNull(message);
        assertEquals("Welcome to ScriptScout Dashboard", message);
    }
}
