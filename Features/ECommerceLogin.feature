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