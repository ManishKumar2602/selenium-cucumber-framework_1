package com.qaplayground.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qaplayground.utils.WaitUtils;

public class DashboardPage extends BasePage {

    @FindBy(id = "dashboard-page-container")
    private WebElement dashboardContainer;

    @FindBy(css = "[data-testid='page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='total-balance-card']")
    private WebElement totalBalanceCard;

    @FindBy(css = "[data-testid='total-balance']")
    private WebElement totalBalance;

    @FindBy(css = "[data-testid='accounts-count-card']")
    private WebElement accountsCountCard;

    @FindBy(css = "[data-testid='accounts-count']")
    private WebElement accountsCount;

    @FindBy(css = "[data-testid='transactions-count-card']")
    private WebElement transactionsCountCard;

    @FindBy(css = "[data-testid='transactions-count']")
    private WebElement transactionsCount;

    @FindBy(css = "[data-testid='skeleton-card']")
    private List<WebElement> skeletonCards;

    @FindBy(css = "[data-testid='quick-add-account']")
    private WebElement quickAddAccountButton;

    @FindBy(css = "[data-testid='quick-new-transaction']")
    private WebElement quickNewTransactionButton;

    @FindBy(css = "[data-testid='recent-transactions-table']")
    private WebElement recentTransactionsTable;

    @FindBy(css = "[data-testid='transactions-tbody'] tr")
    private List<WebElement> transactionRows;

    @FindBy(css = "[data-testid='pinned-accounts-section']")
    private WebElement pinnedAccountsSection;

    @FindBy(css = "[data-testid^='draggable-account-']")
    private List<WebElement> draggableAccounts;

    @FindBy(css = "[data-testid='drop-zone']")
    private WebElement dropZone;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToDashboard() {
        navigateTo("https://www.qaplayground.com/bank/dashboard");
    }

    public String getPageTitleText() {
        WaitUtils.hardWait(500);
        return getText(pageTitle);
    }

    public boolean isDashboardContainerDisplayed() {
        return isElementDisplayed(dashboardContainer);
    }

    public String getDataLoadingAttribute() {
        return getAttribute(dashboardContainer, "data-loading");
    }

    public boolean areSkeletonCardsDisplayed() {
        return !skeletonCards.isEmpty() && isElementDisplayed(skeletonCards.get(0));
    }

    public boolean isTotalBalanceCardDisplayed() {
        return isElementDisplayed(totalBalanceCard);
    }

    public boolean isAccountsCountCardDisplayed() {
        return isElementDisplayed(accountsCountCard);
    }

    public boolean isTransactionsCountCardDisplayed() {
        return isElementDisplayed(transactionsCountCard);
    }

    public String getTotalBalanceValue() {
        return getText(totalBalance);
    }

    public String getAccountsCountValue() {
        return getText(accountsCount);
    }

    public String getTransactionsCountValue() {
        return getText(transactionsCount);
    }

    public void waitForDataToLoad() {
        int attempts = 0;
        while (attempts < 20) { // Max 2 seconds (20 * 100ms)
            String dataLoading = getAttribute(dashboardContainer, "data-loading");
            if (dataLoading != null && dataLoading.equals("false")) {
                return;
            }
            WaitUtils.hardWait(100);
            attempts++;
        }
    }

    public void clickQuickAddAccount() {
        clickElement(quickAddAccountButton);
    }

    public void clickQuickNewTransaction() {
        clickElement(quickNewTransactionButton);
    }

    public boolean isRecentTransactionsTableDisplayed() {
        return isElementDisplayed(recentTransactionsTable);
    }

    public int getTransactionRowCount() {
        return transactionRows.size();
    }

    public List<WebElement> getTransactionRows() {
        return transactionRows;
    }

    public boolean isPinnedAccountsSectionDisplayed() {
        return isElementDisplayed(pinnedAccountsSection);
    }

    public int getDraggableAccountCount() {
        return draggableAccounts.size();
    }

    public List<WebElement> getDraggableAccounts() {
        return draggableAccounts;
    }

    public String getAccountDraggableAttribute(WebElement account) {
        return getAttribute(account, "draggable");
    }

    public boolean isDropZoneDisplayed() {
        return isElementDisplayed(dropZone);
    }
}
