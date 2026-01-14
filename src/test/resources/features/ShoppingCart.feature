@ShoppingCart @Regression
Feature: Shopping Cart Management
  As a logged-in user
  I want to manage my shopping cart
  So that I can add, remove, and update items before checkout

  Background:
    Given I am logged in to the e-commerce website
    And I am on the products page

  @Smoke @Positive
  Scenario: Add single product to cart
    When I click on "Add to cart" button for "Sauce Labs Backpack"
    Then the cart badge should display "1"
    And the button should change to "Remove"
    When I click on the cart icon
    Then I should see "Sauce Labs Backpack" in the cart

  @Positive
  Scenario: Add multiple products to cart
    When I add the following products to cart:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    Then the cart badge should display "3"
    When I click on the cart icon
    Then I should see 3 items in the cart

  @Positive
  Scenario: Remove product from cart
    Given I have added "Sauce Labs Backpack" to the cart
    When I click on the cart icon
    And I click on "Remove" button for "Sauce Labs Backpack"
    Then the cart should be empty
    And the cart badge should not be displayed

  @Positive
  Scenario: Continue shopping from cart
    Given I have added "Sauce Labs Backpack" to the cart
    When I click on the cart icon
    And I click on "Continue Shopping" button
    Then I should be on the products page
    And the cart badge should still display "1"

  @Positive
  Scenario: Cart persists across pages
    When I add "Sauce Labs Backpack" to the cart
    And I navigate to product details page
    And I navigate back to products page
    Then the cart badge should display "1"
    When I click on the cart icon
    Then I should see "Sauce Labs Backpack" in the cart

  @Positive
  Scenario: Verify cart total calculation
    When I add the following products to cart:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    And I click on the cart icon
    Then the cart subtotal should be correctly calculated

  @Functional
  Scenario: Empty cart message
    When I click on the cart icon
    Then the cart should be empty
    And I should see "Continue Shopping" button

  @Negative
  Scenario: Checkout with empty cart
    When I click on the cart icon
    And the cart is empty
    Then the "Checkout" button should be visible

  @Positive
  Scenario: Remove all items from cart
    Given I have added multiple products to the cart
    When I click on the cart icon
    And I remove all items from the cart
    Then the cart should be empty
    And the cart badge should not be displayed

  @Smoke
  Scenario: Cart icon badge visibility
    When the cart is empty
    Then the cart badge should not be displayed
    When I add a product to the cart
    Then the cart badge should be displayed
    And the cart badge should display "1"
