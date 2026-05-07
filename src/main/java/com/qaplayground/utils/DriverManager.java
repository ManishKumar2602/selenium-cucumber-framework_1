package com.qaplayground.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initializeDriver(String browserType) {
        try {
            if (browserType.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
//                options.addArguments("--disable-blink-features=AutomationControlled");
                driver.set(new ChromeDriver(options));
            } else if (browserType.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--start-maximized");
                driver.set(new FirefoxDriver(options));
            } else {
                throw new IllegalArgumentException("Browser type: " + browserType + " is not supported");
            }

            WebDriver driverInstance = driver.get();
            if (driverInstance != null) {
                driverInstance.manage().window().maximize();
            }
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    public static WebDriver getDriver() {
        WebDriver driverInstance = driver.get();
        if (driverInstance == null) {
            throw new RuntimeException("WebDriver is not initialized. Call initializeDriver() first.");
        }
        return driverInstance;
    }

    public static void quitDriver() {
        try {
            WebDriver driverInstance = driver.get();
            if (driverInstance != null) {
                driverInstance.quit();
                driver.remove();
            }
        } catch (Exception e) {
            System.err.println("Error quitting WebDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
