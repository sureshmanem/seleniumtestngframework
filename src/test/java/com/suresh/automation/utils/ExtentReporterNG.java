package com.suresh.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {
        // Define the path for the report
        String path = System.getProperty("user.dir") + "/reports/index.html";
        
        // ExtentSparkReporter is responsible for the look and feel of the report
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        // ExtentReports is the main class to drive reporting
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Your Name");
        return extent;
    }
}

