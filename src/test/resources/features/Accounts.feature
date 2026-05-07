Feature: Accounts Page Functionality    

  Scenario: TC-ACC-01 - Create a new account using the 3-step Open Account wizard
    When I click the "Open New Account" button
    Then the open account wizard should be displayed
    And the step indicator should show "Step 1 of 3"
    When I select "Savings" account type
    And I click the wizard next button
    Then the step indicator should show "Step 2 of 3"
    When I enter "Test Savings Account" as the account name
    And I enter "1000" as the initial deposit
    And I click the wizard next button
    Then the step indicator should show "Step 3 of 3"
    And the review summary should be visible
    When I click the confirm button
    Then a success toast should appear
    And the new account should appear in the accounts table

  Scenario: TC-ACC-02 - Edit account name inline by double-clicking the name cell
    When I double-click on the first account name cell to edit
    Then the inline edit input should be displayed and focused
    When I clear the input and enter "Updated Account Name"
    And I press Enter to save
    Then the inline input should disappear
    And a success toast "Account name updated" should appear
    And the account table should show the new name

  Scenario: TC-ACC-03 - Delete an account with confirmation and verify it is removed
    When I click the delete button for the first account
    Then the delete confirmation modal should be displayed
    And the delete message should contain "cannot be undone"
    When I click the cancel button
    Then the modal should close
    And the account should still exist in the table
    When I click the delete button for the first account again
    And I click the confirm delete button
    Then the account row should be removed from the table
    And a success toast "Account deleted successfully" should appear

  Scenario: TC-ACC-04 - Filter accounts by account type
    When I note the total number of account rows
    And I select "Savings" from the Account Type filter
    Then all visible rows should have the type badge showing "Savings"
    And no row should show "Checking" or "Credit"
    When I click the reset filters button
    Then the row count should return to the original total

  Scenario: TC-ACC-05 - Sort accounts by Balance column header
    When I click the Balance column header
    Then the sort direction should be ascending
    And the first row should have the lowest balance value
    When I click the Balance column header again
    Then the sort direction should be descending
    And the first row should have the highest balance value
    When I click the Balance column header a third time
    Then the sort direction should be none
    And rows should return to default sort order
