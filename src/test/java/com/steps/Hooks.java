package com.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

public class Hooks {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Properties properties = new Properties();

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    @Before
    public void setUp() throws IOException {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new FileNotFoundException("application.properties not found in the classpath");
            }
            properties.load(input);
        }

        String browser = properties.getProperty("browser", "chrome").toLowerCase();
        String executionMode = properties.getProperty("execution.mode", "remote").trim(); // Trim whitespace

        // Debugging output
        System.out.println("Execution mode: '" + executionMode + "'");
        System.out.println("Browser selected: " + browser);

        if (executionMode.equalsIgnoreCase("remote")) { // Use equalsIgnoreCase for comparison
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (browser.equals("firefox")) {
                capabilities.setBrowserName("firefox");
            } else {
                // Default to Chrome
                capabilities.setBrowserName("chrome");
            }
            capabilities.setVersion("109.0");
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", false));
            driver = new RemoteWebDriver(new URL(properties.getProperty("remote.url")), capabilities);
        } else {

            System.setProperty("webdriver.chrome.driver", properties.getProperty("local.driver.path"));
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