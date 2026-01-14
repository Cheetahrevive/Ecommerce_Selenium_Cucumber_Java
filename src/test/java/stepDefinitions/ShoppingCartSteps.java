package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import hooks.Hooks;
import pages.ProductPage;
import pages.CartPage;

public class ShoppingCartSteps {
    private WebDriver driver;
    private ProductPage productPage;
    private CartPage cartPage;
    private int initialCartCount;

    public ShoppingCartSteps() {
        this.driver = Hooks.getDriver();
        this.productPage = new ProductPage(driver);
        this.cartPage = new CartPage(driver);
    }

    @When("the user adds {string} to the cart")
    public void theUserAddsProductToCart(String productName) {
        productPage.addProductToCart(productName);
    }

    @When("the user adds multiple products to the cart")
    public void theUserAddsMultipleProductsToCart() {
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.addProductToCart("Sauce Labs Bike Light");
    }

    @When("the user views the shopping cart")
    public void theUserViewsShoppingCart() {
        productPage.clickCartIcon();
    }

    @When("the user removes {string} from the cart")
    public void theUserRemovesProductFromCart(String productName) {
        cartPage.removeProductFromCart(productName);
    }

    @When("the user removes all items from the cart")
    public void theUserRemovesAllItemsFromCart() {
        cartPage.removeAllProducts();
    }

    @When("the user continues shopping from the cart")
    public void theUserContinuesShopping() {
        cartPage.clickContinueShopping();
    }

    @When("the user notes the current cart count")
    public void theUserNotesCurrentCartCount() {
        initialCartCount = cartPage.getCartItemCount();
    }

    @Then("the product should be added to the cart successfully")
    public void theProductShouldBeAddedSuccessfully() {
        Assert.assertTrue(cartPage.getCartItemCount() > 0);
    }

    @Then("the shopping cart should display {int} item")
    public void theShoppingCartShouldDisplayItem(int count) {
        Assert.assertEquals(cartPage.getCartItemCount(), count);
    }

    @Then("the shopping cart should display {int} items")
    public void theShoppingCartShouldDisplayItems(int count) {
        Assert.assertEquals(cartPage.getCartItemCount(), count);
    }

    @Then("the user should see {string} in the cart")
    public void theUserShouldSeeProductInCart(String productName) {
        Assert.assertTrue(cartPage.isProductInCart(productName));
    }

    @Then("the product should be removed from the cart")
    public void theProductShouldBeRemovedFromCart() {
        Assert.assertTrue(cartPage.isCartEmpty() || cartPage.getCartItemCount() < initialCartCount);
    }

    @Then("the shopping cart should be empty")
    public void theShoppingCartShouldBeEmpty() {
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @Then("the user should be redirected back to the products page")
    public void theUserShouldBeRedirectedBackToProductsPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("the cart badge should not be visible")
    public void theCartBadgeShouldNotBeVisible() {
        Assert.assertTrue(cartPage.isCartEmpty());
    }
}
