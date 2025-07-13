package com.suresh.automation.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report for each test method
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Mark the test as passed in the report
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Mark the test as failed and log the error
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Mark the test as skipped
        test.skip(result.getThrowable());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        // Write all the logs to the report file
        extent.flush();
    }
}
