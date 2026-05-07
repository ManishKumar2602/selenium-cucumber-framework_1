package com.qaplayground.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qaplayground.utils.WaitUtils;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickElement(WebElement element) {
        WaitUtils.waitForElementClickability(driver, getLocator(element));
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        WaitUtils.waitForElementVisibility(driver, getLocator(element));
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        WaitUtils.waitForElementVisibility(driver, getLocator(element));
        return element.getText();
    }

    public String getAttribute(WebElement element, String attributeName) {
        WaitUtils.waitForElementVisibility(driver, getLocator(element));
        return element.getAttribute(attributeName);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement waitForElement(By locator) {
        return WaitUtils.waitForElementVisibility(driver, locator);
    }

    public void waitForURLContains(String urlPart) {
        WaitUtils.waitForUrlContains(driver, urlPart);
    }

    // Helper method to get locator from WebElement
    private By getLocator(WebElement element) {
        // This is a simplified approach; in production, you might use a more robust
        // method
        return By.xpath(".//*");
    }
}
