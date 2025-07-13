// src/test/java/com/mycompany/automation/utils/Listeners.java
package com.suresh.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

// The Listener class no longer needs to extend BaseTest
public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    // ThreadLocal is used to make the ExtentTest object thread-safe for parallel execution
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report for each test method that starts
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // This check handles cases where a test fails before onTestStart is called (e.g., in @BeforeMethod)
        if (extentTest.get() == null) {
            ExtentTest test = extent.createTest(result.getMethod().getMethodName());
            extentTest.set(test);
        }

        // Log the failure and the stack trace
        extentTest.get().fail(result.getThrowable());
        
        WebDriver driver = null;
        try {
            // Retrieve the driver instance from the test class to take a screenshot
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Attach screenshot if driver is available
        if (driver != null) {
            String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            extentTest.get().addScreenCaptureFromBase64String(screenshot, result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // This check handles cases where a test is skipped before onTestStart is called
        if (extentTest.get() == null) {
            ExtentTest test = extent.createTest(result.getMethod().getMethodName());
            extentTest.set(test);
        }
        extentTest.get().log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Write all the logs to the report file
        extent.flush();
    }
}