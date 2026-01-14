@e2e @regression @purchase
Feature: Complete E-Commerce Purchase Flow
  As a customer
  I want to complete the entire purchase journey
  So that I can buy products and track them until delivery

  Background:
    Given User navigates to the e-commerce website

  @smoke @search
  Scenario: Search for a product successfully
    When User enters "Samsung Galaxy S23" in the search box
    And User clicks on the search button
    Then Search results page should be displayed
    And Results should contain products related to "Samsung Galaxy S23"
    And At least 5 products should be displayed

  @cart
  Scenario: Add product to cart and update quantity
    Given User searches for "Laptop"
    When User clicks on the first product in search results
    Then Product details page should be displayed
    When User clicks on "Add to Cart" button
    Then Success message "Product added to cart" should be displayed
    When User navigates to shopping cart
    Then Cart should contain 1 item
    When User updates the quantity to 2
    Then Cart total should be updated accordingly

  @checkout @payment @tracking
  Scenario: Complete purchase from search to delivery tracking
    Given User searches for "iPhone 15 Pro"
    And User selects the first product from results
    And User adds the product to cart
    When User proceeds to checkout
    Then Checkout page should be displayed
    
    When User enters shipping information
      | Field         | Value                  |
      | FirstName     | John                   |
      | LastName      | Doe                    |
      | Email         | john.doe@example.com   |
      | Phone         | 555-123-4567           |
      | Address       | 123 Main Street        |
      | City          | New York               |
      | State         | NY                     |
      | ZipCode       | 10001                  |
      | Country       | United States          |
    And User selects shipping method "Standard Delivery"
    And User clicks "Continue to Payment"
    
    Then Payment page should be displayed
    When User selects payment method "Credit Card"
    And User enters payment details
      | Field          | Value              |
      | CardNumber     | 4111111111111111   |
      | CardHolderName | John Doe           |
      | ExpiryMonth    | 12                 |
      | ExpiryYear     | 2025               |
      | CVV            | 123                |
    And User clicks "Place Order" button
    
    Then Order confirmation page should be displayed
    And Order confirmation message should contain "Thank you for your order"
    And Order number should be generated
    And Order summary should display correct product details
    And Order summary should display correct shipping address
    And Order summary should display correct payment method
    
    When User clicks on "Track Your Order" link
    Then Order tracking page should be displayed
    And Order status should be "Processing"
    And Estimated delivery date should be displayed
    And Tracking number should be displayed

  @guest-checkout
  Scenario: Guest checkout without account creation
    Given User has items in the cart
    When User proceeds to checkout as guest
    And User completes the checkout process
    Then Order should be placed without account creation
    And Guest order confirmation email should be received

  @payment-methods
  Scenario Outline: Checkout with different payment methods
    Given User has "<Product>" in the cart
    When User proceeds to checkout
    And User enters valid shipping information
    And User selects "<PaymentMethod>" as payment method
    And User enters valid "<PaymentMethod>" payment details
    And User confirms the order
    Then Order should be placed successfully
    And Order confirmation should show "<PaymentMethod>" as payment method
    
    Examples:
      | Product           | PaymentMethod |
      | Laptop            | Credit Card   |
      | Smartphone        | Debit Card    |
      | Headphones        | PayPal        |
      | Smart Watch       | Apple Pay     |

  @order-tracking
  Scenario: Track order status through delivery stages
    Given User has placed an order with order number "ORD123456"
    When User navigates to order tracking page
    And User enters order number "ORD123456"
    And User enters email address "john.doe@example.com"
    And User clicks "Track Order" button
    Then Order tracking details should be displayed
    And Order status should show current stage
    And Tracking history should display:
      | Status              | Description                        |
      | Order Placed        | Your order has been received       |
      | Processing          | Order is being prepared            |
      | Shipped             | Order has been shipped             |
      | Out for Delivery    | Order is out for delivery          |
      | Delivered           | Order has been delivered           |

  @negative @validation
  Scenario: Checkout with invalid payment details
    Given User has items in the cart
    When User proceeds to checkout
    And User enters valid shipping information
    And User selects "Credit Card" as payment method
    And User enters invalid card number "1234567890123456"
    And User clicks "Place Order" button
    Then Error message "Invalid card number" should be displayed
    And Order should not be placed

  @promo-code
  Scenario: Apply promo code during checkout
    Given User has items worth $100 in the cart
    When User proceeds to checkout
    And User enters promo code "SAVE20"
    And User clicks "Apply" button
    Then Discount of 20% should be applied
    And Total amount should be $80
    And Promo code "SAVE20" should be displayed in order summary
