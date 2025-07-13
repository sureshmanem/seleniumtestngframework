package com.suresh.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.suresh.automation.base.BaseTest;

// This class inherits from BaseTest, so it has access to the 'driver' instance
// and the setUp/tearDown methods will run automatically.
public class SampleTest extends BaseTest {

    // The @Test annotation marks this method as a test case.
    @Test(description = "Verify the title of the Suresh Manem homepage")
    public void verifySureshManemTitle() {
        // The driver is already initialized by the setUp() method in BaseTest.
        driver.get("https://www.sureshmanem.com");

        // Get the actual title of the page.
        String pageTitle = driver.getTitle();

        // Use TestNG's Assert class to verify the expected outcome.
        // If the assertion fails, the test is marked as failed.
        Assert.assertEquals(pageTitle, "Suresh Manem", "Page title mismatch!");

        // A log message for successful execution.
        System.out.println("Test Passed: Page title is 'Suresh Manem'.");
    }
}