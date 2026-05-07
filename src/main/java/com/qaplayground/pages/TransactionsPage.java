package com.qaplayground.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransactionsPage extends BasePage {

    @FindBy(css = "[data-testid='new-transaction-button']")
    private WebElement newTransactionButton;

    @FindBy(css = "[data-testid='account-modal']")
    private WebElement accountModal;

    @FindBy(css = "[data-testid='from-account-select']")
    private WebElement fromAccountSelect;

    @FindBy(css = "[data-testid='transactions-tbody'] tr")
    private List<WebElement> transactionRows;

    @FindBy(css = "[data-testid='filter-account-select']")
    private WebElement filterAccountSelect;

    @FindBy(css = "[data-testid='apply-filters-button']")
    private WebElement applyFiltersButton;

    @FindBy(css = "[data-testid='reset-filters-button']")
    private WebElement resetFiltersButton;

    @FindBy(css = "[data-testid='date-from-input']")
    private WebElement dateFromInput;

    @FindBy(css = "[data-testid='date-to-input']")
    private WebElement dateToInput;

    @FindBy(css = "[data-testid='date-picker-calendar']")
    private WebElement datePickerCalendar;

    @FindBy(css = "[data-testid='export-button']")
    private WebElement exportButton;

    @FindBy(css = "[data-testid='transaction-id-link']")
    private List<WebElement> transactionIdLinks;

    @FindBy(css = "[data-testid='breadcrumb-item-1']")
    private WebElement breadcrumbItem1;

    @FindBy(css = "[data-testid='breadcrumb-item-2']")
    private WebElement breadcrumbItem2;

    @FindBy(css = "[data-testid='back-button']")
    private WebElement backButton;

    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToTransactions() {
        navigateTo("https://www.qaplayground.com/bank/transactions");
    }

    public void clickNewTransactionButton() {
        clickElement(newTransactionButton);
    }

    public boolean isAccountModalDisplayed() {
        return isElementDisplayed(accountModal);
    }

    public void selectTransactionType(String type) {
        WebElement typeOption = driver.findElement(By.xpath("//option[text()='" + type + "']"));
        clickElement(typeOption);
    }

    public void selectFromAccount(String accountName) {
        WebElement dropdown = fromAccountSelect;
        clickElement(dropdown);
        WebElement option = driver.findElement(By.xpath("//option[contains(text(), '" + accountName + "')]"));
        clickElement(option);
    }

    public void enterTransactionAmount(String amount) {
        WebElement amountField = driver.findElement(By.cssSelector("[data-testid='amount-input']"));
        sendKeys(amountField, amount);
    }

    public void clickSubmitTransaction() {
        WebElement submitButton = driver.findElement(By.cssSelector("[data-testid='submit-transaction-button']"));
        clickElement(submitButton);
    }

    public int getTransactionRowCount() {
        return transactionRows.size();
    }

    public List<WebElement> getTransactionRows() {
        return transactionRows;
    }

    public void selectAccountFilter(String accountName) {
        clickElement(filterAccountSelect);
        WebElement option = driver.findElement(By.xpath("//option[contains(text(), '" + accountName + "')]"));
        clickElement(option);
    }

    public void clickApplyFiltersButton() {
        clickElement(applyFiltersButton);
    }

    public void clickResetFiltersButton() {
        clickElement(resetFiltersButton);
    }

    public void selectFromDateFilter(String date) {
        clickElement(dateFromInput);
        // Assuming the date picker is in format YYYY-MM-DD
        dateFromInput.sendKeys(date);
    }

    public void selectToDateFilter(String date) {
        clickElement(dateToInput);
        dateToInput.sendKeys(date);
    }

    public boolean isDatePickerCalendarDisplayed() {
        return isElementDisplayed(datePickerCalendar);
    }

    public void clickExportButton() {
        clickElement(exportButton);
    }

    public int getTransactionIdLinkCount() {
        return transactionIdLinks.size();
    }

    public void clickTransactionIdLink(int index) {
        clickElement(transactionIdLinks.get(index));
    }

    public String getBreadcrumbItem1Text() {
        return getText(breadcrumbItem1);
    }

    public String getBreadcrumbItem2Text() {
        return getText(breadcrumbItem2);
    }

    public void clickBackButton() {
        clickElement(backButton);
    }

    public String getTransactionDetailValue(String detailType) {
        WebElement detailElement = driver.findElement(By.cssSelector("[data-testid='detail-" + detailType + "']"));
        return getText(detailElement);
    }
}
