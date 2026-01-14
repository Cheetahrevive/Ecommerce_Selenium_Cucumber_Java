package pages;

import org.openqa.selenium.By;

/**
 * OrderTrackingPage class representing order tracking and delivery page
 * Contains locators and methods for tracking orders
 */
public class OrderTrackingPage extends BasePage {
    
    // Locators
    private final By orderNumberInput = By.id("order-number");
    private final By emailInput = By.id("tracking-email");
    private final By trackOrderBtn = By.id("track-order");
    private final By orderStatusText = By.cssSelector(".order-status");
    private final By orderDetailsSection = By.cssSelector(".order-details");
    private final By trackingTimeline = By.cssSelector(".tracking-timeline");
    private final By estimatedDeliveryDate = By.cssSelector(".estimated-delivery");
    private final By currentLocation = By.cssSelector(".current-location");
    private final By deliveryAddress = By.cssSelector(".delivery-address");
    private final By trackingHistoryItems = By.cssSelector(".tracking-history-item");
    private final By carrierName = By.cssSelector(".carrier-name");
    private final By trackingNumber = By.cssSelector(".tracking-number");
    private final By orderItems = By.cssSelector(".order-item");
    private final By deliveryConfirmationMessage = By.cssSelector(".delivery-confirmation");
    
    /**
     * Enter order number and email for tracking
     * @param orderNumber Order number
     * @param email Email address
     */
    public void enterTrackingInformation(String orderNumber, String email) {
        typeText(orderNumberInput, orderNumber);
        typeText(emailInput, email);
    }
    
    /**
     * Click track order button
     */
    public void clickTrackOrder() {
        click(trackOrderBtn);
    }
    
    /**
     * Get order status
     * @return Order status text
     */
    public String getOrderStatus() {
        return getText(orderStatusText);
    }
    
    /**
     * Get estimated delivery date
     * @return Estimated delivery date
     */
    public String getEstimatedDeliveryDate() {
        return getText(estimatedDeliveryDate);
    }
    
    /**
     * Get current location of package
     * @return Current location
     */
    public String getCurrentLocation() {
        return getText(currentLocation);
    }
    
    /**
     * Get delivery address
     * @return Delivery address
     */
    public String getDeliveryAddress() {
        return getText(deliveryAddress);
    }
    
    /**
     * Get carrier name
     * @return Carrier name
     */
    public String getCarrierName() {
        return getText(carrierName);
    }
    
    /**
     * Get tracking number
     * @return Tracking number
     */
    public String getTrackingNumber() {
        return getText(trackingNumber);
    }
    
    /**
     * Verify tracking details are displayed
     * @return true if tracking details are displayed
     */
    public boolean isTrackingDetailsDisplayed() {
        return isElementDisplayed(orderStatusText) && isElementDisplayed(trackingTimeline);
    }
    
    /**
     * Verify order is delivered
     * @return true if order is delivered
     */
    public boolean isOrderDelivered() {
        return isElementDisplayed(deliveryConfirmationMessage);
    }
    
    /**
     * Get tracking history count
     * @return Number of tracking history items
     */
    public int getTrackingHistoryCount() {
        return getElements(trackingHistoryItems).size();
    }
}
