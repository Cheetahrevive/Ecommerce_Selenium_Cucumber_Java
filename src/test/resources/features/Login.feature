@Login @Regression
Feature: User Login Functionality
  As a user
  I want to log in to the e-commerce website
  So that I can access my account and shop

  Background:
    Given I am on the e-commerce homepage

  @Smoke @Positive
  Scenario: Successful login with valid credentials
    When I enter valid username and password
    And I click on the login button
    Then I should be successfully logged in
    And I should see the products page

  @Negative
  Scenario: Login with invalid username
    When I enter invalid username "invalid_user" and valid password
    And I click on the login button
    Then I should see an error message "Epic sadface: Username and password do not match"
    And I should remain on the login page

  @Negative
  Scenario: Login with invalid password
    When I enter valid username and invalid password "wrong_pass"
    And I click on the login button
    Then I should see an error message
    And I should remain on the login page

  @Negative
  Scenario: Login with empty credentials
    When I leave username and password fields empty
    And I click on the login button
    Then I should see an error message "Epic sadface: Username is required"

  @Negative
  Scenario: Login with locked user
    When I enter locked user credentials
    And I click on the login button
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out"

  @Positive
  Scenario Outline: Login with multiple valid users
    When I enter username "<username>" and password "<password>"
    And I click on the login button
    Then I should be successfully logged in
    And I should see the products page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |

  @Security
  Scenario: Verify password field is masked
    When I enter password in the password field
    Then the password should be displayed as masked characters

  @Functional
  Scenario: Logout functionality
    When I login with valid credentials
    And I click on the menu button
    And I click on logout
    Then I should be redirected to the login page
