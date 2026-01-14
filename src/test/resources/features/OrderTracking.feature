Feature: Order Tracking
  As a customer
  I want to track my order status
  So that I can know when my order will be delivered

  Background:
    Given the user is on the home page
    And the user is logged in

  @tracking @smoke
  Scenario: Track order with valid order number
    Given the user has placed an order
    When the user navigates to the order tracking page
    And the user enters the order number
    And the user clicks on the track button
    Then the order status should be displayed
    And the estimated delivery date should be shown

  @tracking
  Scenario: Track order with invalid order number
    When the user navigates to the order tracking page
    And the user enters invalid order number "INVALID123"
    And the user clicks on the track button
    Then the user should see an "Order not found" error message

  @tracking
  Scenario: View order details from tracking page
    Given the user has placed an order
    When the user navigates to the order tracking page
    And the user enters the order number
    And the user clicks on the track button
    And the user clicks on view order details
    Then the order details should be displayed
    And the product list should be shown
    And the order total should be displayed

  @tracking @status
  Scenario: Verify order status - Order Placed
    Given the user has an order with status "Order Placed"
    When the user tracks the order
    Then the order status should show "Order Placed"
    And the status indicator should be at the first stage

  @tracking @status
  Scenario: Verify order status - Processing
    Given the user has an order with status "Processing"
    When the user tracks the order
    Then the order status should show "Processing"
    And the status indicator should be at the second stage

  @tracking @status
  Scenario: Verify order status - Shipped
    Given the user has an order with status "Shipped"
    When the user tracks the order
    Then the order status should show "Shipped"
    And the tracking number should be displayed
    And the carrier information should be shown

  @tracking @status
  Scenario: Verify order status - Out for Delivery
    Given the user has an order with status "Out for Delivery"
    When the user tracks the order
    Then the order status should show "Out for Delivery"
    And the delivery agent information should be displayed

  @tracking @status
  Scenario: Verify order status - Delivered
    Given the user has an order with status "Delivered"
    When the user tracks the order
    Then the order status should show "Delivered"
    And the delivery confirmation should be displayed
    And the delivery date and time should be shown

  @tracking
  Scenario: View order history
    Given the user has multiple orders
    When the user navigates to order history page
    Then all past orders should be displayed
    And each order should show order number, date, and status

  @tracking @cancel
  Scenario: Cancel an order that is not yet shipped
    Given the user has an order with status "Processing"
    When the user tracks the order
    And the user clicks on cancel order button
    And the user confirms the cancellation
    Then the order status should be updated to "Cancelled"
    And a cancellation confirmation message should be displayed

  @tracking @cancel
  Scenario: Attempt to cancel a shipped order
    Given the user has an order with status "Shipped"
    When the user tracks the order
    Then the cancel order button should not be displayed

  @tracking
  Scenario: Download invoice for delivered order
    Given the user has an order with status "Delivered"
    When the user tracks the order
    And the user clicks on download invoice button
    Then the invoice should be downloaded

  @tracking @notification
  Scenario: Verify tracking notification preferences
    When the user navigates to the order tracking page
    And the user enters the order number
    And the user enables email notifications for order updates
    Then a confirmation message should be displayed
    And the user should receive email notifications for status changes

  @tracking
  Scenario Outline: Track multiple orders with different statuses
    Given the user has an order "<orderNumber>" with status "<status>"
    When the user tracks the order "<orderNumber>"
    Then the order status should show "<status>"

    Examples:
      | orderNumber | status           |
      | ORD001      | Order Placed     |
      | ORD002      | Processing       |
      | ORD003      | Shipped          |
      | ORD004      | Out for Delivery |
      | ORD005      | Delivered        |
