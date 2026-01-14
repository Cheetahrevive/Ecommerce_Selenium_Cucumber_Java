Feature: Product Search
  As a customer
  I want to search for products on the e-commerce website
  So that I can find items I'm interested in purchasing

  Background:
    Given the user is on the home page

  @search @smoke
  Scenario: Search for a product by name
    When the user enters "laptop" in the search bar
    And the user clicks on the search button
    Then the search results page should be displayed
    And the search results should contain products related to "laptop"

  @search
  Scenario: Search with no results
    When the user enters "invalidproduct12345" in the search bar
    And the user clicks on the search button
    Then the user should see a "No products found" message
    And the user should see suggestions for other products

  @search
  Scenario: Search for products by category
    When the user selects "Electronics" from the category dropdown
    And the user clicks on the search button
    Then the search results should only show products from "Electronics" category

  @search @filter
  Scenario: Apply price filter to search results
    When the user enters "phone" in the search bar
    And the user clicks on the search button
    And the user applies price filter from "100" to "500"
    Then all displayed products should be within the price range

  @search @filter
  Scenario: Apply brand filter to search results
    When the user enters "laptop" in the search bar
    And the user clicks on the search button
    And the user selects "Dell" from the brand filter
    Then all displayed products should be from "Dell" brand

  @search @sort
  Scenario: Sort search results by price low to high
    When the user enters "headphones" in the search bar
    And the user clicks on the search button
    And the user sorts results by "Price: Low to High"
    Then the products should be displayed in ascending price order

  @search @sort
  Scenario: Sort search results by price high to low
    When the user enters "headphones" in the search bar
    And the user clicks on the search button
    And the user sorts results by "Price: High to Low"
    Then the products should be displayed in descending price order

  @search @sort
  Scenario: Sort search results by customer ratings
    When the user enters "camera" in the search bar
    And the user clicks on the search button
    And the user sorts results by "Customer Rating"
    Then the products should be displayed in descending rating order

  @search
  Scenario Outline: Search for multiple product types
    When the user enters "<product>" in the search bar
    And the user clicks on the search button
    Then the search results should contain products related to "<product>"
    And at least <count> products should be displayed

    Examples:
      | product  | count |
      | laptop   | 5     |
      | phone    | 10    |
      | tablet   | 3     |
      | watch    | 8     |

  @search @autocomplete
  Scenario: Verify search autocomplete suggestions
    When the user starts typing "lap" in the search bar
    Then autocomplete suggestions should appear
    And suggestions should include "laptop", "laptop bag", "laptop stand"

  @search
  Scenario: Clear search results
    When the user enters "keyboard" in the search bar
    And the user clicks on the search button
    And the user clicks on the clear search button
    Then the search bar should be empty
    And all products should be displayed
