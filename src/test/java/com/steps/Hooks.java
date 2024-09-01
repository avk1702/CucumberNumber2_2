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
import java.util.Map;


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
    public void setUp() throws MalformedURLException{
        URL remoteUrl = new URL("http://149.154.71.152:4444/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("109");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC" , true,
                        "enableVideo", false));

        driver = new RemoteWebDriver(remoteUrl, capabilities);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


