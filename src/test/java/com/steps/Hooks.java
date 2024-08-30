package com.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
public class Hooks {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    @Before
    public void setUp() {
        String runMode = System.getProperty("run.mode", "local");
        if ("remote".equalsIgnoreCase(runMode)) {
            // Setup for remote execution with Selenoid
            try {
                URL remoteUrl = new URL("http://149.154.71.152:4444/wd/hub");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("109");
                capabilities.setCapability("enableVnc", true);
                driver = new RemoteWebDriver(remoteUrl, capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid remote URL", e);
            }
        } else {
            // Local execution
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


