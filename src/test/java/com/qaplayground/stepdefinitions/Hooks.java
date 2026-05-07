package com.qaplayground.stepdefinitions;

import com.qaplayground.utils.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before(order = 0)
    public void setUp() {
        try {
            System.out.println("[HOOK] Initializing WebDriver...");
            DriverManager.initializeDriver("Chrome");
            System.out.println("[HOOK] WebDriver initialized successfully");
        } catch (Exception e) {
            System.err.println("[HOOK] Failed to initialize WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Hook setup failed", e);
        }
    }

    @After(order = 0)
    public void tearDown() {
        try {
            System.out.println("[HOOK] Tearing down WebDriver...");
            DriverManager.quitDriver();
            System.out.println("[HOOK] WebDriver quit successfully");
        } catch (Exception e) {
            System.err.println("[HOOK] Error during teardown: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
