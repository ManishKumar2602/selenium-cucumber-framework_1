package com.qaplayground.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.qaplayground.pages.LoginPage;
import com.qaplayground.utils.AssertionUtils;
import com.qaplayground.utils.Constants;
import com.qaplayground.utils.DriverManager;
import com.qaplayground.utils.WaitUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("the browser is open and navigated to the login page")
    public void navigateToLoginPage() {
        loginPage.navigateToLogin();
    }

    @When("I enter {string} in the username field")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter {string} in the password field")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the Login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the dashboard")
    public void verifyRedirectToDashboard() {
        WaitUtils.waitForUrlContains(driver, "dashboard");
        AssertionUtils.assertURLContains(driver, "dashboard");
    }

    @Then("the page title should contain {string}")
    public void verifyPageTitle(String expectedTitle) {
        WaitUtils.hardWait(1000);
        String pageTitle = driver.findElement(
                org.openqa.selenium.By.cssSelector("[data-testid='page-title']")).getText();
        AssertionUtils.assertElementTextContains(
                driver.findElement(org.openqa.selenium.By.cssSelector("[data-testid='page-title']")),
                expectedTitle);
    }

    @Then("the login alert should be displayed")
    public void verifyLoginAlertDisplayed() {
        WaitUtils.hardWait(500);
        AssertionUtils.assertTrue("Login alert is not displayed", loginPage.isLoginAlertDisplayed());
    }

    @Then("the alert text should contain {string}")
    public void verifyAlertText(String expectedText) {
        String alertText = loginPage.getLoginAlertText();
        AssertionUtils.assertElementTextContains(
                driver.findElement(org.openqa.selenium.By.cssSelector("[data-testid='login-alert']")),
                expectedText);
    }

    @Then("I should remain on the login page")
    public void verifyRemainOnLoginPage() {
        AssertionUtils.assertURLContains(driver, "/bank");
        AssertionUtils.assertTrue("Username field should be displayed on login page",
                loginPage.isUsernameFieldDisplayed());
    }

    @Then("the password input type should be {string}")
    public void verifyPasswordInputType(String expectedType) {
        String actualType = loginPage.getPasswordFieldType();
        AssertionUtils.assertEquals("Password input type mismatch", expectedType, actualType);
    }

    @When("I click the toggle password button")
    public void clickTogglePasswordButton() {
        loginPage.clickTogglePasswordButton();
    }

    @When("I click the toggle password button again")
    public void clickTogglePasswordButtonAgain() {
        loginPage.clickTogglePasswordButton();
    }

    @When("I click into the password field and enter {string}")
    public void clickPasswordFieldAndEnter(String password) {
        driver.findElement(org.openqa.selenium.By.id("password")).click();
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys(password);
    }

    @When("I press Enter key in the password field")
    public void pressEnterInPasswordField() {
        driver.findElement(org.openqa.selenium.By.id("password")).submit();
    }

    @Then("I should be redirected to the dashboard without clicking the login button")
    public void verifyDashboardRedirectWithoutClick() {
        WaitUtils.waitForUrlContains(driver, "/bank/dashboard");
        AssertionUtils.assertURLContains(driver, "/bank/dashboard");
    }

    @Then("the role indicator should display {string}")
    public void verifyRoleIndicator(String expectedRole) {
        WaitUtils.hardWait(1000);
        String roleText = driver.findElement(
                org.openqa.selenium.By.cssSelector("[data-testid='role-indicator']")).getText();
        AssertionUtils.assertTrue("Role indicator does not contain: " + expectedRole,
                roleText.contains(expectedRole));
    }

    @Then("I should navigate to accounts page")
    public void navigateToAccountsPage() {
        driver.navigate().to(Constants.ACCOUNTS_URL);
        WaitUtils.waitForUrlContains(driver, "/bank/accounts");
    }

    @Then("the Add New Account button should not be present")
    public void verifyAddNewAccountButtonNotPresent() {
        boolean buttonNotPresent = driver.findElements(
                org.openqa.selenium.By.cssSelector("[data-testid='open-wizard-button']")).isEmpty();
        AssertionUtils.assertTrue("Add New Account button should not be present for viewer",
                buttonNotPresent);
    }
}
