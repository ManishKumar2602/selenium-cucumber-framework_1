Feature: Dashboard Page Functionality

  Background:
    Given the browser is open and user is logged in as admin
    And I navigate to the dashboard

  Scenario: TC-DASH-01 - Skeleton loading state appears on page load then data renders
    When the dashboard page loads
    Then the skeleton placeholder elements should be visible
    And I wait for the data to fully load
    Then the total balance card should be visible with a dollar amount
    And the accounts count card should be visible with a numeric value
    And the transactions count card should be visible with a numeric value

  Scenario: TC-DASH-02 - Stat card values match actual account and transaction data
    When the dashboard page fully loads
    Then I read the dashboard total balance
    And I navigate to the accounts page to verify the balance matches
    Then the dashboard total should match the sum of all account balances
    And I navigate back to the dashboard
    And the accounts count on dashboard should match the total accounts

  Scenario: TC-DASH-03 - Quick Actions navigate to correct pages
    When I click the "Add Account" quick action button
    Then I should be on the accounts page
    And the account modal should be open
    When I navigate back to the dashboard
    And I click the "New Transaction" quick action button
    Then I should be on the transactions page
    And the transaction modal should be open

  Scenario: TC-DASH-04 - Recent Transactions table shows up to 5 latest transactions
    When the dashboard page fully loads
    Then the recent transactions table should be visible
    And the number of transaction rows should be between 0 and 5
    And each transaction row should contain date, type, account name, amount, and status

  Scenario: TC-DASH-05 - Pinned Accounts section supports drag-and-drop reorder
    When the dashboard page fully loads
    And there are at least 2 pinned accounts
    Then the pinned accounts section should be visible
    And the accounts should be draggable
    When I perform drag and drop on the account cards
    And I reload the page
    Then the reordered positions should be preserved
