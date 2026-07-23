package com.example.scriptscout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.models.Report;
import com.example.scriptscout.service.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @PostMapping("/save")
    public Report saveReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @GetMapping("/all")
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }
    @GetMapping("/{id}")
    public Report getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @PutMapping("/update/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report report) {
        return reportService.updateReport(id, report);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteReport(@PathVariable Long id) {
        return reportService.deleteReport(id);
    }
}