package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;
import utils.ConfigReader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Step definitions for ShoppingCart.feature, targeting the SauceDemo demo
 * site (https://www.saucedemo.com). Each scenario gets a fresh browser from
 * Hooks, so login is performed in the Background steps.
 */
public class ShoppingCartSteps {

    private static final String VALID_USER = "standard_user";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String DEFAULT_PRODUCT = "Sauce Labs Backpack";

    private SauceLoginPage loginPage;
    private SauceInventoryPage inventoryPage;

    /** Product most recently acted on; used by button-state assertions. */
    private String currentProduct;
    /** Prices captured on the inventory page, for the subtotal cross-check. */
    private final List<BigDecimal> addedPrices = new ArrayList<>();

    private SauceLoginPage login() {
        if (loginPage == null) {
            loginPage = new SauceLoginPage();
        }
        return loginPage;
    }

    private SauceInventoryPage page() {
        if (inventoryPage == null) {
            inventoryPage = new SauceInventoryPage();
        }
        return inventoryPage;
    }

    private String inventoryUrl() {
        String base = ConfigReader.getProperty("baseUrl");
        return base.endsWith("/") ? base + "inventory.html" : base + "/inventory.html";
    }

    private void loginAsValidUser() {
        login().open(ConfigReader.getProperty("baseUrl"));
        login().loginAs(VALID_USER, VALID_PASSWORD);
        Assert.assertTrue(login().isLoggedIn(), "Login failed in cart scenario setup");
    }

    private void ensureOnProductsPage() {
        if (!page().isOnProductsPage()) {
            page().goToProductsPage(inventoryUrl());
        }
    }

    private void addProduct(String productName) {
        ensureOnProductsPage();
        addedPrices.add(page().addProductToCart(productName));
        currentProduct = productName;
    }

    // ----- Background -----

    @Given("I am logged in to the e-commerce website")
    public void iAmLoggedInToTheEcommerceWebsite() {
        loginAsValidUser();
    }

    @Given("I am on the products page")
    public void iAmOnTheProductsPage() {
        ensureOnProductsPage();
        Assert.assertTrue(page().isOnProductsPage(), "Not on the products page");
    }

    // ----- Adding to cart -----

    @When("I click on {string} button for {string}")
    public void iClickOnButtonFor(String buttonLabel, String productName) {
        if (buttonLabel.equalsIgnoreCase("Add to cart")) {
            addProduct(productName);
        } else if (buttonLabel.equalsIgnoreCase("Remove")) {
            page().removeProductFromCart(productName);
        } else {
            Assert.fail("Unsupported button: " + buttonLabel);
        }
    }

    @When("I add the following products to cart:")
    public void iAddTheFollowingProductsToCart(DataTable table) {
        for (String productName : table.asList(String.class)) {
            addProduct(productName.trim());
        }
    }

    @When("I add {string} to the cart")
    public void iAddToTheCart(String productName) {
        addProduct(productName);
    }

    @Given("I have added {string} to the cart")
    public void iHaveAddedToTheCart(String productName) {
        loginAsValidUser();
        addProduct(productName);
    }

    @Given("I have added multiple products to the cart")
    public void iHaveAddedMultipleProductsToTheCart() {
        loginAsValidUser();
        addProduct("Sauce Labs Backpack");
        addProduct("Sauce Labs Bike Light");
    }

    @When("I add a product to the cart")
    public void iAddAProductToTheCart() {
        addProduct(DEFAULT_PRODUCT);
    }

    // ----- Cart badge -----

    @Then("the cart badge should display {string}")
    public void theCartBadgeShouldDisplay(String expected) {
        Assert.assertTrue(page().isCartBadgeDisplayed(), "Cart badge is not displayed");
        Assert.assertEquals(String.valueOf(page().getCartBadgeCount()), expected,
                "Cart badge count mismatch");
    }

    @Then("the cart badge should still display {string}")
    public void theCartBadgeShouldStillDisplay(String expected) {
        theCartBadgeShouldDisplay(expected);
    }

    @Then("the cart badge should not be displayed")
    public void theCartBadgeShouldNotBeDisplayed() {
        Assert.assertFalse(page().isCartBadgeDisplayed(), "Cart badge should not be displayed");
    }

    @Then("the cart badge should be displayed")
    public void theCartBadgeShouldBeDisplayed() {
        Assert.assertTrue(page().isCartBadgeDisplayed(), "Cart badge is not displayed");
    }

    // ----- Button state -----

    @Then("the button should change to {string}")
    public void theButtonShouldChangeTo(String expectedLabel) {
        Assert.assertNotNull(currentProduct, "No product was added before button assertion");
        Assert.assertEquals(page().getProductButtonText(currentProduct), expectedLabel,
                "Product button label mismatch");
    }

    // ----- Cart page -----

    @When("I click on the cart icon")
    public void iClickOnTheCartIcon() {
        page().openCart();
    }

    @Then("I should see {string} in the cart")
    public void iShouldSeeInTheCart(String productName) {
        Assert.assertTrue(page().getCartItemNames().contains(productName),
                productName + " not found in cart");
    }

    @Then("I should see {int} items in the cart")
    public void iShouldSeeItemsInTheCart(int expectedCount) {
        Assert.assertEquals(page().getCartItemsCount(), expectedCount,
                "Cart item count mismatch");
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        Assert.assertTrue(page().isCartEmpty(), "Cart is not empty");
    }

    @Given("the cart is empty")
    public void theCartIsEmpty() {
        Assert.assertTrue(page().isCartEmpty(), "Cart is not empty");
    }

    @When("I remove all items from the cart")
    public void iRemoveAllItemsFromTheCart() {
        page().removeAllItemsFromCart();
    }

    // ----- Continue shopping / navigation -----

    @When("I click on {string} button")
    public void iClickOnButton(String buttonLabel) {
        if (buttonLabel.equalsIgnoreCase("Continue Shopping")) {
            page().clickContinueShopping();
        } else {
            Assert.fail("Unsupported button: " + buttonLabel);
        }
    }

    @Then("I should be on the products page")
    public void iShouldBeOnTheProductsPage() {
        Assert.assertTrue(page().isOnProductsPage(), "Not on the products page");
    }

    @Then("I should see {string} button")
    public void iShouldSeeButton(String buttonLabel) {
        if (buttonLabel.equalsIgnoreCase("Continue Shopping")) {
            Assert.assertTrue(page().isContinueShoppingDisplayed(),
                    "Continue Shopping button not visible");
        } else {
            Assert.fail("Unsupported button: " + buttonLabel);
        }
    }

    @When("I navigate to product details page")
    public void iNavigateToProductDetailsPage() {
        String product = currentProduct != null ? currentProduct : DEFAULT_PRODUCT;
        ensureOnProductsPage();
        page().openProductDetails(product);
    }

    @When("I navigate back to products page")
    public void iNavigateBackToProductsPage() {
        page().navigateBack();
    }

    // ----- Subtotal -----

    @Then("the cart subtotal should be correctly calculated")
    public void theCartSubtotalShouldBeCorrectlyCalculated() {
        BigDecimal expected = addedPrices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal actual = page().getCartPricesTotal();
        Assert.assertEquals(actual, expected,
                "Cart total does not match sum of item prices");
    }

    // ----- Checkout -----

    @Then("the {string} button should be visible")
    public void theButtonShouldBeVisible(String buttonLabel) {
        if (buttonLabel.equalsIgnoreCase("Checkout")) {
            Assert.assertTrue(page().isCheckoutButtonVisible(),
                    "Checkout button is not visible");
        } else {
            Assert.fail("Unsupported button: " + buttonLabel);
        }
    }
}
