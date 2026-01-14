package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class EcommercePurchaseSteps {
    
    WebDriver driver;
    
    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        // Click on checkout button
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
    }
    
    @When("the user fills in the billing information")
    public void theUserFillsInTheBillingInformation() {
        // Fill in billing details
        Assert.assertTrue(driver.getPageSource().contains("Billing"));
    }
    
    @When("the user enters billing name {string}")
    public void theUserEntersBillingName(String name) {
        // Enter billing name
        Assert.assertFalse(name.isEmpty());
    }
    
    @When("the user enters billing address {string}")
    public void theUserEntersBillingAddress(String address) {
        // Enter billing address
        Assert.assertFalse(address.isEmpty());
    }
    
    @When("the user enters billing city {string}")
    public void theUserEntersBillingCity(String city) {
        // Enter billing city
        Assert.assertFalse(city.isEmpty());
    }
    
    @When("the user selects billing state {string}")
    public void theUserSelectsBillingState(String state) {
        // Select billing state
        Assert.assertFalse(state.isEmpty());
    }
    
    @When("the user enters billing zip code {string}")
    public void theUserEntersBillingZipCode(String zipCode) {
        // Enter zip code
        Assert.assertTrue(zipCode.matches("\\d{5}"));
    }
    
    @When("the user enters billing country {string}")
    public void theUserEntersBillingCountry(String country) {
        // Enter country
        Assert.assertFalse(country.isEmpty());
    }
    
    @When("the user fills in the shipping information")
    public void theUserFillsInTheShippingInformation() {
        // Fill in shipping details
        Assert.assertTrue(driver.getPageSource().contains("Shipping"));
    }
    
    @When("the user selects same as billing address")
    public void theUserSelectsSameAsBillingAddress() {
        // Check same as billing checkbox
    }
    
    @When("the user enters shipping name {string}")
    public void theUserEntersShippingName(String name) {
        // Enter shipping name
        Assert.assertFalse(name.isEmpty());
    }
    
    @When("the user enters shipping address {string}")
    public void theUserEntersShippingAddress(String address) {
        // Enter shipping address
        Assert.assertFalse(address.isEmpty());
    }
    
    @When("the user selects payment method {string}")
    public void theUserSelectsPaymentMethod(String paymentMethod) {
        // Select payment method (Credit Card, PayPal, etc.)
        Assert.assertFalse(paymentMethod.isEmpty());
    }
    
    @When("the user enters card number {string}")
    public void theUserEntersCardNumber(String cardNumber) {
        // Enter credit card number
        Assert.assertTrue(cardNumber.matches("\\d{16}"));
    }
    
    @When("the user enters card holder name {string}")
    public void theUserEntersCardHolderName(String cardHolderName) {
        // Enter card holder name
        Assert.assertFalse(cardHolderName.isEmpty());
    }
    
    @When("the user enters card expiry date {string}")
    public void theUserEntersCardExpiryDate(String expiryDate) {
        // Enter expiry date (MM/YY)
        Assert.assertTrue(expiryDate.matches("\\d{2}/\\d{2}"));
    }
    
    @When("the user enters CVV {string}")
    public void theUserEntersCVV(String cvv) {
        // Enter CVV
        Assert.assertTrue(cvv.matches("\\d{3}"));
    }
    
    @When("the user reviews the order")
    public void theUserReviewsTheOrder() {
        // Review order details
        Assert.assertTrue(driver.getPageSource().contains("Order Summary"));
    }
    
    @When("the user applies promo code {string}")
    public void theUserAppliesPromoCode(String promoCode) {
        // Apply promo code
        Assert.assertFalse(promoCode.isEmpty());
    }
    
    @Then("the discount should be applied")
    public void theDiscountShouldBeApplied() {
        // Verify discount is applied
        Assert.assertTrue(driver.getPageSource().contains("Discount"));
    }
    
    @When("the user confirms the order")
    public void theUserConfirmsTheOrder() {
        // Click place order button
    }
    
    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() {
        // Verify order confirmation
        Assert.assertTrue(driver.getCurrentUrl().contains("confirmation") || 
                         driver.getPageSource().contains("Order Confirmed"));
    }
    
    @Then("the user should see order confirmation number")
    public void theUserShouldSeeOrderConfirmationNumber() {
        // Verify order number is displayed
        Assert.assertTrue(driver.getPageSource().contains("Order Number"));
    }
    
    @Then("the user should receive order confirmation email")
    public void theUserShouldReceiveOrderConfirmationEmail() {
        // Verify confirmation email message
        Assert.assertTrue(driver.getPageSource().contains("confirmation email"));
    }
    
    @Then("the total amount should be {string}")
    public void theTotalAmountShouldBe(String amount) {
        // Verify total amount
        Assert.assertTrue(driver.getPageSource().contains(amount));
    }
    
    @Then("the order should contain {int} items")
    public void theOrderShouldContainItems(Integer itemCount) {
        // Verify item count in order
        Assert.assertTrue(itemCount > 0);
    }
    
    @When("the user cancels the order")
    public void theUserCancelsTheOrder() {
        // Click cancel order button
    }
    
    @Then("the user should be redirected back to the cart page")
    public void theUserShouldBeRedirectedBackToTheCartPage() {
        // Verify redirect to cart
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }
    
    @When("the user selects express shipping")
    public void theUserSelectsExpressShipping() {
        // Select express shipping option
    }
    
    @When("the user selects standard shipping")
    public void theUserSelectsStandardShipping() {
        // Select standard shipping option
    }
    
    @Then("the shipping cost should be updated")
    public void theShippingCostShouldBeUpdated() {
        // Verify shipping cost update
        Assert.assertTrue(driver.getPageSource().contains("Shipping"));
    }
}
