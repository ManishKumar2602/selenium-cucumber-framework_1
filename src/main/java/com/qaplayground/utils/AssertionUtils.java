package com.qaplayground.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertionUtils {

    public static void assertURLContains(WebDriver driver, String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        System.out.println("actualUrl"+actualUrl);
        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "URL does not contain: " + expectedUrl + " | Actual URL: " + actualUrl);
    }

    public static void assertElementText(WebElement element, String expectedText) {
        String actualText = element.getText();
        Assert.assertEquals(actualText, expectedText,
                "Element text does not match");
    }

    public static void assertElementTextContains(WebElement element, String expectedText) {
        String actualText = element.getText();
        Assert.assertTrue(actualText.contains(expectedText),
                "Element text does not contain: " + expectedText + " | Actual text: " + actualText);
    }

    public static void assertElementVisible(WebElement element) {
        Assert.assertTrue(element.isDisplayed(),
                "Element is not visible");
    }

    public static void assertElementNotVisible(WebElement element) {
        try {
            Assert.assertFalse(element.isDisplayed(),
                    "Element is visible but should not be");
        } catch (Exception e) {
            // Element not present = pass
        }
    }

    public static void assertElementEnabled(WebElement element) {
        Assert.assertTrue(element.isEnabled(),
                "Element is not enabled");
    }

    public static void assertElementAttribute(WebElement element, String attribute, String expectedValue) {
        String actualValue = element.getAttribute(attribute);
        Assert.assertEquals(actualValue, expectedValue,
                "Attribute value does not match for: " + attribute);
    }

    // Custom wrappers (fixed order)
    public static void assertEquals(String message, Object expected, Object actual) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertTrue(String message, boolean condition) {
        Assert.assertTrue(condition, message);
    }

    public static void assertFalse(String message, boolean condition) {
        Assert.assertFalse(condition, message);
    }
}
