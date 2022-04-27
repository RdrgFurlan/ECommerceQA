Feature: ECommerce Login

  Scenario: Login on ECommerce platform
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I click on Login button
    Then I input "rdrg.furlan@gmail.com" in email address
    Then I input "123123" in password
    Then I click on signin button
    Then I will see the Logout button
    And Close browser

  Scenario: Error when tried to login with invalid credentials
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I click on Login button
    Then I input "invalid@invalid.com" in email address
    Then I input "123123" in password
    Then I click on signin button
    Then I will see the error message "Authentication failed"
    And Close browser

  Scenario: Login on ECommerce with DataTables values
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I try to signin with these credentials:
      | rdrg.furlan@gmail.com | 123123 |
    Then I will see the Logout button
    And Close browser

  Scenario: Error when tried to login with invalid credentials in DataTables values
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I try to signin with these credentials:
      | invalid@invalid.com | 123123 |
    Then I will see the error message "Authentication failed"
    And Close browser

  Scenario Outline: Sign In with parameterized data
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I will validate the credentials: <emailAddress> <password> <allowed>
    And Close browser
    Examples:
      | emailAddress               | password | allowed |
      | invalid@invalid.com        | 123123   | false   |
      | anotherInvalid@invalid.com | 123123   | false   |
      | rdrg.furlan@gmail.com      | 123123   | true    |
