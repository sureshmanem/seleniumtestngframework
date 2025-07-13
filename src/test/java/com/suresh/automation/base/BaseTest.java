// src/test/java/com/mycompany/automation/base/BaseTest.java
package com.suresh.automation.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public Properties prop;

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser").toLowerCase().trim();

        if (browserName.startsWith("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (browserName.equalsIgnoreCase("chrome-headless")) {
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080"); // Set a window size for headless mode
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Maximizing the window is not applicable for headless mode, so we check first
        if (!browserName.equalsIgnoreCase("chrome-headless")) {
            driver.manage().window().maximize();
        }
        
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
