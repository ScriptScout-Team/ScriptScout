package com.example.scriptscout.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.Report;
import com.example.scriptscout.repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
    public Report getReportById(Long id) {
        return reportRepository.findById(id).orElse(null);
    }
    public Report updateReport(Long id, Report report) {

        Report existingReport = reportRepository.findById(id).orElse(null);

        if (existingReport != null) {
            existingReport.setReportName(report.getReportName());
            existingReport.setReportType(report.getReportType());
            existingReport.setGeneratedDate(report.getGeneratedDate());
            existingReport.setStatus(report.getStatus());

            return reportRepository.save(existingReport);
        }

        return null;
    }
    public String deleteReport(Long id) {

        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return "Report Deleted Successfully";
        }

        return "Report Not Found";
    }
}