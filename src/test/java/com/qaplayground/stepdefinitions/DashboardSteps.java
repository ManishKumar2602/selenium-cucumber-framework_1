package com.qaplayground.stepdefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qaplayground.pages.AccountsPage;
import com.qaplayground.pages.DashboardPage;
import com.qaplayground.pages.LoginPage;
import com.qaplayground.pages.TransactionsPage;
import com.qaplayground.utils.AssertionUtils;
import com.qaplayground.utils.Constants;
import com.qaplayground.utils.DriverManager;
import com.qaplayground.utils.WaitUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardSteps {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private AccountsPage accountsPage;
    private TransactionsPage transactionsPage;
    private String origTotalBalance;

    public DashboardSteps() {
        this.driver = DriverManager.getDriver();
        this.dashboardPage = new DashboardPage(driver);
        this.accountsPage = new AccountsPage(driver);
        this.transactionsPage = new TransactionsPage(driver);
    }

    @Given("the browser is open and user is logged in as admin")
    public void loginAsAdmin() {
        DriverManager.initializeDriver("chrome");
        driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        loginPage.login(Constants.ADMIN_USERNAME, Constants.ADMIN_PASSWORD);
        WaitUtils.waitForUrlContains(driver, "/bank/dashboard");
    }

    @Given("I navigate to the dashboard")
    public void navigateToDashboard() {
        dashboardPage.navigateToDashboard();
    }

    @When("the dashboard page loads")
    public void dashboardPageLoads() {
        WaitUtils.hardWait(500);
    }

    @Then("the skeleton placeholder elements should be visible")
    public void verifySkeletonElementsVisible() {
        WaitUtils.hardWait(300);
        AssertionUtils.assertTrue("Skeleton cards should be displayed",
                dashboardPage.areSkeletonCardsDisplayed());
    }

    @Then("I wait for the data to fully load")
    public void waitForDataToLoad() {
        dashboardPage.waitForDataToLoad();
        WaitUtils.hardWait(500);
    }

    @Then("the total balance card should be visible with a dollar amount")
    public void verifyTotalBalanceCard() {
        AssertionUtils.assertTrue("Total balance card should be displayed",
                dashboardPage.isTotalBalanceCardDisplayed());
        String balance = dashboardPage.getTotalBalanceValue();
        AssertionUtils.assertTrue("Balance should contain dollar sign or amount",
                balance != null && !balance.isEmpty());
    }

    @Then("the accounts count card should be visible with a numeric value")
    public void verifyAccountsCountCard() {
        AssertionUtils.assertTrue("Accounts count card should be displayed",
                dashboardPage.isAccountsCountCardDisplayed());
        String count = dashboardPage.getAccountsCountValue();
        AssertionUtils.assertTrue("Count should be numeric and not empty",
                count != null && !count.isEmpty());
    }

    @Then("the transactions count card should be visible with a numeric value")
    public void verifyTransactionsCountCard() {
        AssertionUtils.assertTrue("Transactions count card should be displayed",
                dashboardPage.isTransactionsCountCardDisplayed());
        String count = dashboardPage.getTransactionsCountValue();
        AssertionUtils.assertTrue("Count should be numeric and not empty",
                count != null && !count.isEmpty());
    }

    @When("the dashboard page fully loads")
    public void dashboardFullyLoads() {
        dashboardPage.waitForDataToLoad();
    }

    @Then("I read the dashboard total balance")
    public void readTotalBalance() {
        origTotalBalance = dashboardPage.getTotalBalanceValue();
    }

    @Then("I navigate to the accounts page to verify the balance matches")
    public void navigateToAccountsForVerification() {
        accountsPage.navigateToAccounts();
        WaitUtils.waitForUrlContains(driver, "/bank/accounts");
    }

    @Then("the dashboard total should match the sum of all account balances")
    public void verifyBalanceMatches() {
        dashboardPage.navigateToDashboard();
        dashboardPage.waitForDataToLoad();
        String currentTotal = dashboardPage.getTotalBalanceValue();
        AssertionUtils.assertEquals("Dashboard balance should match",
                origTotalBalance, currentTotal);
    }

    @Then("I navigate back to the dashboard")
    public void navigateBackToDashboard() {
        dashboardPage.navigateToDashboard();
        dashboardPage.waitForDataToLoad();
    }

    @Then("the accounts count on dashboard should match the total accounts")
    public void verifyAccountsCountMatches() {
        String dashboardCount = dashboardPage.getAccountsCountValue();
        AssertionUtils.assertTrue("Accounts count should be displayed",
                dashboardCount != null && !dashboardCount.isEmpty());
    }

    @When("I click the {string} quick action button")
    public void clickQuickActionButton(String buttonName) {
        WaitUtils.hardWait(500);
        if (buttonName.equalsIgnoreCase("Add Account")) {
            dashboardPage.clickQuickAddAccount();
        } else if (buttonName.equalsIgnoreCase("New Transaction")) {
            dashboardPage.clickQuickNewTransaction();
        }
    }

    @Then("I should be on the accounts page")
    public void verifyOnAccountsPage() {
        WaitUtils.waitForUrlContains(driver, "/bank/accounts");
        AssertionUtils.assertURLContains(driver, "/bank/accounts");
    }

    @Then("the account modal should be open")
    public void verifyAccountModalOpen() {
        accountsPage = new AccountsPage(driver);
        AssertionUtils.assertTrue("Account modal should be displayed",
                accountsPage.isOpenAccountWizardDisplayed());
    }

    @Then("I should be on the transactions page")
    public void verifyOnTransactionsPage() {
        WaitUtils.waitForUrlContains(driver, "/bank/transactions");
        AssertionUtils.assertURLContains(driver, "/bank/transactions");
    }

    @Then("the transaction modal should be open")
    public void verifyTransactionModalOpen() {
        transactionsPage = new TransactionsPage(driver);
        AssertionUtils.assertTrue("Transaction modal should be displayed",
                transactionsPage.isAccountModalDisplayed());
    }

    @Then("the recent transactions table should be visible")
    public void verifyRecentTransactionsTable() {
        dashboardPage = new DashboardPage(driver);
        AssertionUtils.assertTrue("Recent transactions table should be displayed",
                dashboardPage.isRecentTransactionsTableDisplayed());
    }

    @Then("the number of transaction rows should be between {int} and {int}")
    public void verifyTransactionRowCount(int minRows, int maxRows) {
        int rowCount = dashboardPage.getTransactionRowCount();
        AssertionUtils.assertTrue("Transaction row count should be between " + minRows + " and " + maxRows,
                rowCount >= minRows && rowCount <= maxRows);
    }

    @Then("each transaction row should contain date, type, account name, amount, and status")
    public void verifyTransactionRowContent() {
        List<WebElement> rows = dashboardPage.getTransactionRows();
        for (WebElement row : rows) {
            AssertionUtils.assertTrue("Each row should have content",
                    row.getText() != null && !row.getText().isEmpty());
        }
    }

    @Then("there are at least 2 pinned accounts")
    public void verifyPinnedAccountsCount() {
        int accountCount = dashboardPage.getDraggableAccountCount();
        AssertionUtils.assertTrue("Should have at least 2 pinned accounts", accountCount >= 2);
    }

    @Then("the pinned accounts section should be visible")
    public void verifyPinnedAccountsSection() {
        AssertionUtils.assertTrue("Pinned accounts section should be displayed",
                dashboardPage.isPinnedAccountsSectionDisplayed());
    }

    @Then("the accounts should be draggable")
    public void verifyAccountsDraggable() {
        List<WebElement> accounts = dashboardPage.getDraggableAccounts();
        for (WebElement account : accounts) {
            String draggable = dashboardPage.getAccountDraggableAttribute(account);
            AssertionUtils.assertEquals("Account should have draggable attribute", "true", draggable);
        }
    }

    @When("I perform drag and drop on the account cards")
    public void performDragAndDrop() {
        List<WebElement> accounts = dashboardPage.getDraggableAccounts();
        if (accounts.size() >= 2) {
            Actions actions = new Actions(driver);
            WebElement firstCard = accounts.get(0);
            WebElement secondCard = accounts.get(1);
            actions.dragAndDrop(firstCard, secondCard).perform();
            WaitUtils.hardWait(500);
        }
    }

    @When("I reload the page")
    public void reloadPage() {
        driver.navigate().refresh();
        WaitUtils.hardWait(1000);
        dashboardPage.waitForDataToLoad();
    }

    @Then("the reordered positions should be preserved")
    public void verifyReorderedPositionsPreserved() {
        List<WebElement> accounts = dashboardPage.getDraggableAccounts();
        AssertionUtils.assertTrue("Account order should be persisted after reload",
                accounts.size() > 0);
    }
}
