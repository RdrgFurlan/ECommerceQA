Feature: ECommerce Cart Flow

  Scenario: Force error scenario to visualize on report and test jenkins pipeline
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I click on Add to cart
    And Close browser

  Scenario: Add product to cart
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I search for a product "Faded Short Sleeve T-shirts"
    Then I visualize the first product on page
    Then I click on Add to cart
    Then I close the cart preview
    And Close browser

  Scenario: Add product to cart and check price
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I search for a product "Faded Short Sleeve T-shirts"
    Then I visualize the first product on page
    Then I store the product price
    Then I click on Add to cart
    Then I confirm that the product price was not changed
    Then I close the cart preview
    And Close browser

  Scenario Outline: Add product to cart and change quantity
    Given I launch browser
    When I open the Automation Ecommerce WebSite
    Then I search for a product "Faded Short Sleeve T-shirts"
    Then I visualize the first product on page
    Then I click on Add to cart
    Then I close the cart preview
    Then I open the full cart shopping
    Then I will validate the results: <changeValueTo> <finalValue> <expectedResult>
    And Close browser
    Examples:
      | changeValueTo | finalValue | expectedResult |
      | 5             | 5          | accepted       |
      | 100           | 100        | accepted       |
      | -10           | 1          | refused        |
      | 0             | 0          | removed        |