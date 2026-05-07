package com.qaplayground.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountsPage extends BasePage {

    @FindBy(css = "[data-testid='open-wizard-button']")
    private WebElement openWizardButton;

    @FindBy(css = "[data-testid='open-account-wizard']")
    private WebElement openAccountWizard;

    @FindBy(css = "[data-testid='wizard-step-indicator']")
    private WebElement wizardStepIndicator;

    @FindBy(css = "[data-testid='type-card-savings']")
    private WebElement savingsTypeCard;

    @FindBy(css = "[data-testid='type-card-checking']")
    private WebElement checkingTypeCard;

    @FindBy(css = "[data-testid='type-card-credit']")
    private WebElement creditTypeCard;

    @FindBy(css = "[data-testid='wizard-next']")
    private WebElement wizardNextButton;

    @FindBy(css = "[data-testid='wizard-account-name']")
    private WebElement wizardAccountNameField;

    @FindBy(css = "[data-testid='wizard-initial-deposit']")
    private WebElement wizardInitialDepositField;

    @FindBy(css = "[data-testid='wizard-confirm']")
    private WebElement wizardConfirmButton;

    @FindBy(css = "[data-testid='accounts-tbody'] tr")
    private List<WebElement> accountRows;

    @FindBy(css = "[data-editable='true']")
    private List<WebElement> editableNameCells;

    @FindBy(css = "[data-testid='inline-edit-input']")
    private WebElement inlineEditInput;

    @FindBy(css = "[data-testid='delete-modal']")
    private WebElement deleteModal;

    @FindBy(css = "[data-testid='delete-message']")
    private WebElement deleteMessage;

    @FindBy(css = "[data-testid='cancel-delete-button']")
    private WebElement cancelDeleteButton;

    @FindBy(css = "[data-testid='confirm-delete-button']")
    private WebElement confirmDeleteButton;

    @FindBy(css = "[data-testid='filter-type-select']")
    private WebElement filterTypeSelect;

    @FindBy(css = "[data-testid='reset-filters-button']")
    private WebElement resetFiltersButton;

    @FindBy(css = "[data-testid='sort-balance-header']")
    private WebElement sortBalanceHeader;

    public AccountsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToAccounts() {
        navigateTo("https://www.qaplayground.com/bank/accounts");
    }

    public void clickOpenWizardButton() {
        clickElement(openWizardButton);
    }

    public boolean isOpenAccountWizardDisplayed() {
        return isElementDisplayed(openAccountWizard);
    }

    public String getWizardStepIndicatorText() {
        return getText(wizardStepIndicator);
    }

    public void selectAccountType(String accountType) {
        if (accountType.equalsIgnoreCase("Savings")) {
            clickElement(savingsTypeCard);
        } else if (accountType.equalsIgnoreCase("Checking")) {
            clickElement(checkingTypeCard);
        } else if (accountType.equalsIgnoreCase("Credit")) {
            clickElement(creditTypeCard);
        }
    }

    public String getAccountTypeCardAttribute(WebElement card, String attribute) {
        return getAttribute(card, attribute);
    }

    public void clickWizardNextButton() {
        clickElement(wizardNextButton);
    }

    public void enterAccountName(String accountName) {
        sendKeys(wizardAccountNameField, accountName);
    }

    public void enterInitialDeposit(String amount) {
        sendKeys(wizardInitialDepositField, amount);
    }

    public void clickWizardConfirmButton() {
        clickElement(wizardConfirmButton);
    }

    public int getAccountRowCount() {
        return accountRows.size();
    }

    public List<WebElement> getAccountRows() {
        return accountRows;
    }

    public void doubleClickAccountNameCell(int rowIndex) {
        WebElement nameCell = accountRows.get(rowIndex)
                .findElement(By.cssSelector("[data-editable='true']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(nameCell).perform();
    }


    public String getAccountNameCellEditingAttribute(int rowIndex) {
        WebElement nameCell = accountRows.get(rowIndex).findElement(By.cssSelector("[data-editable='true']"));
        return getAttribute(nameCell, "data-editing");
    }

    public boolean isInlineEditInputDisplayed() {
        return isElementDisplayed(inlineEditInput);
    }

    public void clearAndEnterNewAccountName(String newName) {
        inlineEditInput.clear();
        inlineEditInput.sendKeys(newName);
        inlineEditInput.submit();
    }

    public void deleteAccountByIndex(int rowIndex) {
        String deleteButtonSelector = "[data-testid='delete-account-" + rowIndex + "']";
        WebElement deleteButton = driver.findElement(By.cssSelector(deleteButtonSelector));
        clickElement(deleteButton);
    }

    public boolean isDeleteModalDisplayed() {
        return isElementDisplayed(deleteModal);
    }

    public String getDeleteMessageText() {
        return getText(deleteMessage);
    }

    public void clickCancelDeleteButton() {
        clickElement(cancelDeleteButton);
    }

    public void clickConfirmDeleteButton() {
        clickElement(confirmDeleteButton);
    }

    public void selectAccountTypeFilter(String accountType) {
        WebElement dropdown = filterTypeSelect;
        clickElement(dropdown);
        WebElement option = driver.findElement(By.xpath("//option[text()='" + accountType + "']"));
        clickElement(option);
    }

    public void clickResetFiltersButton() {
        clickElement(resetFiltersButton);
    }

    public void clickSortBalanceHeader() {
        clickElement(sortBalanceHeader);
    }

    public String getSortBalanceHeaderDirection() {
        return getAttribute(sortBalanceHeader, "data-sort-direction");
    }

    public List<WebElement> getAllTypeBadges() {
        return driver.findElements(By.cssSelector("[data-testid='account-type-badge']"));
    }
}
