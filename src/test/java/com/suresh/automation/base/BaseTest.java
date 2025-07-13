// src/test/java/com/mycompany/automation/base/BaseTest.java
package com.suresh.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    // Declare the WebDriver instance to be accessible by child test classes
    public WebDriver driver;

    /**
     * This method, annotated with @BeforeMethod, will run before each
     * test method in any inheriting test class.
     */
    @BeforeMethod
    public void setUp() {
        // WebDriverManager automatically downloads the latest chromedriver
        // and sets the system property for Selenium to use it.
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window for consistency
    }

    /**
     * This method, annotated with @AfterMethod, will run after each
     * test method, ensuring the browser is closed even if the test fails.
     */
    @AfterMethod
    public void tearDown() {
        // A null check ensures we don't get a NullPointerException
        // if the driver failed to initialize.
        if (driver != null) {
            driver.quit(); // .quit() closes all browser windows and ends the session.
        }
    }
}