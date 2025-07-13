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
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentManager.createInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ensureTestInitialized(result);
        ExtentManager.getTest().fail(result.getThrowable());
        WebDriver driver = getDriverFromTestResult(result);
        if (driver != null) {
            String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            ExtentManager.getTest().addScreenCaptureFromBase64String(screenshot, result.getMethod().getMethodName());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        ensureTestInitialized(result);
        ExtentManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        try {
            File reportFile = Paths.get(System.getProperty("user.dir"), "reports", "index.html").toFile();
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureTestInitialized(ITestResult result) {
        if (ExtentManager.getTest() == null) {
            ExtentTest test = extent.createTest(result.getMethod().getMethodName());
            ExtentManager.setTest(test);
        }
    }

    private WebDriver getDriverFromTestResult(ITestResult result) {
        try {
            return (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
