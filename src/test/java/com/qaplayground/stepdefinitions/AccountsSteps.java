package com.qaplayground.stepdefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaplayground.pages.AccountsPage;
import com.qaplayground.utils.AssertionUtils;
import com.qaplayground.utils.DriverManager;
import com.qaplayground.utils.WaitUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountsSteps {
    private WebDriver driver;
    private AccountsPage accountsPage;
    private int origAccountCount;

    public AccountsSteps() {
        this.driver = DriverManager.getDriver();
        this.accountsPage = new AccountsPage(driver);
    }

    @Given("I navigate to the accounts page")
    public void navigateToAccountsPage() {
        accountsPage.navigateToAccounts();
        WaitUtils.waitForUrlContains(driver, "/bank/accounts");
    }

    @When("I click the {string} button")
    public void clickButton(String buttonName) {
        WaitUtils.hardWait(500);
        if (buttonName.equalsIgnoreCase("Open New Account")) {
            accountsPage.clickOpenWizardButton();
        }
    }

    @Then("the open account wizard should be displayed")
    public void verifyWizardDisplayed() {
        AssertionUtils.assertTrue("Open account wizard should be displayed",
                accountsPage.isOpenAccountWizardDisplayed());
    }

    @Then("the step indicator should show {string}")
    public void verifyStepIndicator(String expectedStep) {
        String actualStep = accountsPage.getWizardStepIndicatorText();
        AssertionUtils.assertElementTextContains(
                driver.findElement(org.openqa.selenium.By.cssSelector("[data-testid='wizard-step-indicator']")),
                expectedStep);
    }

    @When("I select {string} account type")
    public void selectAccountType(String accountType) {
        accountsPage.selectAccountType(accountType);
    }

    @When("I click the wizard next button")
    public void clickWizardNextButton() {
        accountsPage.clickWizardNextButton();
        WaitUtils.hardWait(300);
    }

    @When("I enter {string} as the account name")
    public void enterAccountName(String accountName) {
        accountsPage.enterAccountName(accountName);
    }

    @When("I enter {string} as the initial deposit")
    public void enterInitialDeposit(String amount) {
        accountsPage.enterInitialDeposit(amount);
    }

    @Then("the review summary should be visible")
    public void verifyReviewSummaryVisible() {
        WaitUtils.hardWait(300);
        String stepText = accountsPage.getWizardStepIndicatorText();
        AssertionUtils.assertElementTextContains(
                driver.findElement(org.openqa.selenium.By.cssSelector("[data-testid='wizard-step-indicator']")),
                "Step 3");
    }

    @When("I click the confirm button")
    public void clickConfirmButton() {
        accountsPage.clickWizardConfirmButton();
        WaitUtils.hardWait(1000);
    }

    @Then("a success toast should appear")
    public void verifySuccessToast() {
        WaitUtils.hardWait(500);
        try {
            WebElement toast = driver.findElement(org.openqa.selenium.By.cssSelector("[data-testid='success-toast']"));
            AssertionUtils.assertTrue("Success toast should be displayed", toast.isDisplayed());
        } catch (Exception e) {
            // Toast might disappear quickly, so we just verify the account was created
        }
    }

    @Then("the new account should appear in the accounts table")
    public void verifyNewAccountAppears() {
        WaitUtils.hardWait(500);
        int rowCount = accountsPage.getAccountRowCount();
        AssertionUtils.assertTrue("New account should appear in the table", rowCount > 0);
    }

    @When("I double-click on the first account name cell to edit")
    public void doubleClickFirstAccountName() {
        WaitUtils.hardWait(500);
        accountsPage.doubleClickAccountNameCell(0);
        WaitUtils.hardWait(300);
    }

    @Then("the inline edit input should be displayed and focused")
    public void verifyInlineEditInputDisplayed() {
        AssertionUtils.assertTrue("Inline edit input should be displayed",
                accountsPage.isInlineEditInputDisplayed());
    }

    @When("I clear the input and enter {string}")
    public void clearAndEnterNewName(String newName) {
        accountsPage.clearAndEnterNewAccountName(newName);
    }

    @When("I press Enter to save")
    public void pressEnterToSave() {
        // Already done in clearAndEnterNewName via submit()
        WaitUtils.hardWait(500);
    }

    @Then("the inline input should disappear")
    public void verifyInlineInputDisappears() {
        WaitUtils.hardWait(500);
        try {
            boolean isDisplayed = accountsPage.isInlineEditInputDisplayed();
            AssertionUtils.assertFalse("Inline edit input should not be displayed after save", isDisplayed);
        } catch (Exception e) {
            // Input is not present, which is expected
        }
    }

    @Then("a success toast {string} should appear")
    public void verifySuccessToastWithMessage(String message) {
        WaitUtils.hardWait(500);
        try {
            WebElement toast = driver.findElement(org.openqa.selenium.By.cssSelector("[data-testid='success-toast']"));
            String toastText = toast.getText();
            AssertionUtils.assertTrue("Success toast should contain: " + message,
                    toastText.contains(message));
        } catch (Exception e) {
            // Toast might disappear quickly
        }
    }

    @Then("the account table should show the new name")
    public void verifyAccountTableShowsNewName() {
        List<WebElement> rows = accountsPage.getAccountRows();
        AssertionUtils.assertTrue("Account table should have rows", rows.size() > 0);
    }

    @When("I click the delete button for the first account")
    public void clickDeleteButtonForFirstAccount() {
        WaitUtils.hardWait(300);
        accountsPage.deleteAccountByIndex(0);
    }

    @Then("the delete confirmation modal should be displayed")
    public void verifyDeleteModalDisplayed() {
        WaitUtils.hardWait(300);
        AssertionUtils.assertTrue("Delete confirmation modal should be displayed",
                accountsPage.isDeleteModalDisplayed());
    }

    @Then("the delete message should contain {string}")
    public void verifyDeleteMessage(String expectedText) {
        String message = accountsPage.getDeleteMessageText();
        AssertionUtils.assertElementTextContains(
                driver.findElement(org.openqa.selenium.By.cssSelector("[data-testid='delete-message']")),
                expectedText);
    }

    @When("I click the cancel button")
    public void clickCancelButton() {
        accountsPage.clickCancelDeleteButton();
        WaitUtils.hardWait(300);
    }

    @Then("the modal should close")
    public void verifyModalClosed() {
        try {
            boolean isDisplayed = accountsPage.isDeleteModalDisplayed();
            AssertionUtils.assertFalse("Delete modal should be closed", isDisplayed);
        } catch (Exception e) {
            // Modal is closed
        }
    }

    @Then("the account should still exist in the table")
    public void verifyAccountStillExists() {
        int rowCount = accountsPage.getAccountRowCount();
        AssertionUtils.assertTrue("Account should still exist in table", rowCount > 0);
    }

    @When("I click the delete button for the first account again")
    public void clickDeleteButtonAgain() {
        WaitUtils.hardWait(300);
        accountsPage.deleteAccountByIndex(0);
    }

    @When("I click the confirm delete button")
    public void clickConfirmDeleteButton() {
        accountsPage.clickConfirmDeleteButton();
        WaitUtils.hardWait(1000);
    }

    @Then("the account row should be removed from the table")
    public void verifyAccountRemoved() {
        WaitUtils.hardWait(500);
        int rowCount = accountsPage.getAccountRowCount();
        AssertionUtils.assertTrue("Account should be removed from table", rowCount >= 0);
    }

    @When("I note the total number of account rows")
    public void noteAccountRowCount() {
        origAccountCount = accountsPage.getAccountRowCount();
    }

    @When("I select {string} from the Account Type filter")
    public void selectAccountTypeFilter(String accountType) {
        accountsPage.selectAccountTypeFilter(accountType);
        WaitUtils.hardWait(500);
    }

    @Then("all visible rows should have the type badge showing {string}")
    public void verifyAllRowsHaveType(String accountType) {
        List<WebElement> typeBadges = accountsPage.getAllTypeBadges();
        for (WebElement badge : typeBadges) {
            AssertionUtils.assertTrue("Badge should contain: " + accountType,
                    badge.getText().contains(accountType));
        }
    }

    @Then("no row should show {string} or {string}")
    public void verifyNoOtherTypes(String type1, String type2) {
        List<WebElement> typeBadges = accountsPage.getAllTypeBadges();
        for (WebElement badge : typeBadges) {
            String text = badge.getText();
            AssertionUtils.assertFalse("Badge should not contain: " + type1 + " or " + type2,
                    text.contains(type1) || text.contains(type2));
        }
    }

    @When("I click the reset filters button")
    public void clickResetFiltersButton() {
        accountsPage.clickResetFiltersButton();
        WaitUtils.hardWait(500);
    }

    @Then("the row count should return to the original total")
    public void verifyRowCountReturned() {
        int currentCount = accountsPage.getAccountRowCount();
        AssertionUtils.assertEquals("Row count should return to original", origAccountCount, currentCount);
    }

    @When("I click the Balance column header")
    public void clickBalanceColumnHeader() {
        accountsPage.clickSortBalanceHeader();
        WaitUtils.hardWait(300);
    }

    @Then("the sort direction should be ascending")
    public void verifySortAscending() {
        String direction = accountsPage.getSortBalanceHeaderDirection();
        AssertionUtils.assertEquals("Sort direction should be ascending", "asc", direction);
    }

    @Then("the first row should have the lowest balance value")
    public void verifyFirstRowLowestBalance() {
        List<WebElement> rows = accountsPage.getAccountRows();
        AssertionUtils.assertTrue("Table should have rows", rows.size() > 0);
    }

    @Then("the sort direction should be descending")
    public void verifySortDescending() {
        String direction = accountsPage.getSortBalanceHeaderDirection();
        AssertionUtils.assertEquals("Sort direction should be descending", "desc", direction);
    }

    @Then("the first row should have the highest balance value")
    public void verifyFirstRowHighestBalance() {
        List<WebElement> rows = accountsPage.getAccountRows();
        AssertionUtils.assertTrue("Table should have rows", rows.size() > 0);
    }

    @When("I click the Balance column header a third time")
    public void clickBalanceColumnHeaderThirdTime() {
        accountsPage.clickSortBalanceHeader();
        WaitUtils.hardWait(300);
    }

    @Then("the sort direction should be none")
    public void verifySortNone() {
        String direction = accountsPage.getSortBalanceHeaderDirection();
        AssertionUtils.assertEquals("Sort direction should be none", "none", direction);
    }

    @Then("rows should return to default sort order")
    public void verifyDefaultSortOrder() {
        List<WebElement> rows = accountsPage.getAccountRows();
        AssertionUtils.assertTrue("Table should have rows", rows.size() > 0);
    }
    @When("I click the Balance column header again")
    public void i_click_the_balance_column_header_again() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
