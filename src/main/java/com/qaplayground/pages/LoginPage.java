package com.qaplayground.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-btn")
    private WebElement loginButton;

    @FindBy(css = "[data-testid='login-alert']")
    private WebElement loginAlert;

    @FindBy(css = "[data-testid='toggle-password-btn']")
    private WebElement togglePasswordButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLoginAlertDisplayed() {
        try {
            return isElementDisplayed(loginAlert);
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginAlertText() {
        return getText(loginAlert);
    }

    public String getPasswordFieldType() {
        return getAttribute(passwordField, "type");
    }

    public void clickTogglePasswordButton() {
        clickElement(togglePasswordButton);
    }

    public void enterPasswordAndPressEnter(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void navigateToLogin() {
        navigateTo("https://www.qaplayground.com/bank");
    }

    public boolean isUsernameFieldDisplayed() {
        return isElementDisplayed(usernameField);
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginButton);
    }
}
