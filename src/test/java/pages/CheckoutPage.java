package pages;

import org.openqa.selenium.By;

/**
 * CheckoutPage class representing checkout and payment page
 * Contains locators and methods for checkout process
 */
public class CheckoutPage extends BasePage {
    
    // Shipping Information Locators
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By emailInput = By.id("email");
    private final By phoneInput = By.id("phone");
    private final By addressInput = By.id("address");
    private final By cityInput = By.id("city");
    private final By stateInput = By.id("state");
    private final By zipCodeInput = By.id("zip-code");
    private final By countryDropdown = By.id("country");
    
    // Shipping Method Locators
    private final By standardShipping = By.id("standard-shipping");
    private final By expressShipping = By.id("express-shipping");
    private final By overnightShipping = By.id("overnight-shipping");
    
    // Payment Locators
    private final By cardNumberInput = By.id("card-number");
    private final By cardHolderNameInput = By.id("card-holder-name");
    private final By expiryDateInput = By.id("expiry-date");
    private final By cvvInput = By.id("cvv");
    private final By billingAddressSameAsShipping = By.id("billing-same-shipping");
    
    // Order Summary Locators
    private final By orderSummary = By.cssSelector(".order-summary");
    private final By orderTotal = By.cssSelector(".order-total");
    private final By shippingCost = By.cssSelector(".shipping-cost");
    private final By taxAmount = By.cssSelector(".tax-amount");
    
    // Action Buttons
    private final By placeOrderBtn = By.id("place-order");
    private final By continueToPaymentBtn = By.id("continue-payment");
    private final By backToCartBtn = By.id("back-to-cart");
    
    // Confirmation
    private final By orderConfirmationMessage = By.cssSelector(".order-confirmation");
    private final By orderNumberText = By.cssSelector(".order-number");
    
    /**
     * Enter shipping information
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @param phone Phone number
     * @param address Street address
     * @param city City
     * @param state State
     * @param zipCode ZIP code
     */
    public void enterShippingInformation(String firstName, String lastName, String email, 
                                         String phone, String address, String city, 
                                         String state, String zipCode) {
        typeText(firstNameInput, firstName);
        typeText(lastNameInput, lastName);
        typeText(emailInput, email);
        typeText(phoneInput, phone);
        typeText(addressInput, address);
        typeText(cityInput, city);
        typeText(stateInput, state);
        typeText(zipCodeInput, zipCode);
    }
    
    /**
     * Select shipping method
     * @param method Shipping method (standard, express, overnight)
     */
    public void selectShippingMethod(String method) {
        switch (method.toLowerCase()) {
       case "standard":
                    click(standardShipping);
                    break;
                case "express":
                    click(expressShipping);
                    break;
                case "overnight":
                    click(overnightShipping);
                    break;
        }
    }
}
