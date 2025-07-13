package com.suresh.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static final ExtentReports extentReports = new ExtentReports();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ExtentReports createInstance() {
        String path = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Test Results");
        reporter.config().setDocumentTitle("Test Automation Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Your Name");
        return extentReports;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }
}