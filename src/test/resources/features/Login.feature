Feature: Login Page Functionality

  Background:
    Given the browser is open and navigated to the login page

  Scenario: TC-LOGIN-01 - Successful login with admin credentials
    When I enter "admin" in the username field
    And I enter "admin123" in the password field
    And I click the Login button
    Then I should be redirected to the dashboard
    And the page title should contain "dashboard"

  Scenario: TC-LOGIN-02 - Failed login shows error alert for invalid credentials
    When I enter "wrong" in the username field
    And I enter "wrong123" in the password field
    And I click the Login button
    Then the login alert should be displayed
    And the alert text should contain "Invalid username or password"
    And I should remain on the login page

  Scenario: TC-LOGIN-03 - Toggle password visibility hides and reveals password text
    When I enter "admin123" in the password field
    Then the password input type should be "password"
    When I click the toggle password button
    Then the password input type should be "text"
    When I click the toggle password button again
    Then the password input type should be "password"

  Scenario: TC-LOGIN-04 - Pressing Enter in the password field submits the login form
    When I enter "admin" in the username field
    And I click into the password field and enter "admin123"
    And I press Enter key in the password field
    Then I should be redirected to the dashboard without clicking the login button

  Scenario: TC-LOGIN-05 - Read-only viewer login grants restricted access
    When I enter "viewer" in the username field
    And I enter "viewer123" in the password field
    And I click the Login button
    Then I should be redirected to the dashboard
    And the role indicator should display "Read-only Viewer"
    And I should navigate to accounts page
    And the Add New Account button should not be present
