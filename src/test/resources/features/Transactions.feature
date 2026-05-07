Feature: Transactions Page Functionality

  Background:
    Given the browser is open and user is logged in as admin

  Scenario: TC-TXN-01 - Create a deposit transaction and verify balance update
    And I navigate to the accounts page
    When I note the balance of "Primary Savings" account
    And I navigate to the transactions page
    And I click the "New Transaction" button
    Then the transaction modal should be displayed
    When I select "Deposit" as the transaction type
    And I select "Primary Savings" as the account
    And I enter "500" as the transaction amount
    And I click submit
    Then a success toast should appear
    And the new transaction should appear in the transactions table
    When I navigate back to the accounts page
    Then the "Primary Savings" balance should be increased by 500

  Scenario: TC-TXN-02 - Filter transactions by account and verify only matching rows appear
    And I navigate to the transactions page
    When I note the total transaction count
    And I select "Primary Savings" from the Account filter
    And I click the apply filters button
    Then every row in the table should show "Primary Savings" in the Account column
    When I click the reset filters button
    Then all transaction rows should return
    And the row count should match the original total

  Scenario: TC-TXN-03 - Filter transactions by date range using the calendar date picker
    And I navigate to the transactions page
    When I click the From date picker
    Then a calendar popup should appear
    When I select today's date
    And I click the To date picker
    And I select today's date
    And I click apply filters
    Then every row in the results should have a date matching today
    When I click the clear button on the From date picker
    Then the From date should be cleared

  Scenario: TC-TXN-04 - Export transactions as CSV and verify file is downloaded
    And I navigate to the transactions page with at least one transaction
    When I click the Export button
    Then a success toast "Transactions exported successfully!" should appear
    And a CSV file should be downloaded
    When I navigate to the transactions page with no transactions
    And I click the Export button
    Then an error toast "No transactions to export" should appear

  Scenario: TC-TXN-05 - Open transaction detail page and verify all fields via breadcrumb navigation
    And I navigate to the transactions page
    When I click on a transaction ID link
    Then the URL should change to the transaction detail page
    And the breadcrumb should show "Dashboard" and "Transactions"
    And the transaction type, amount, date, account, and status should be visible
    When I click the back button
    Then I should navigate back to the transactions page
