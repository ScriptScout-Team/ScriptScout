package com.example.scriptscout.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	private String reportName;
    private String reportType;
    private String generatedDate;
    private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getGeneratedDate() {
		return generatedDate;
	}
	public void setGeneratedDate(String generatedDate) {
		this.generatedDate = generatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Report(Long id, String reportName, String reportType, String generatedDate, String status) {
		super();
		this.id = id;
		this.reportName = reportName;
		this.reportType = reportType;
		this.generatedDate = generatedDate;
		this.status = status;
	}
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}


}
