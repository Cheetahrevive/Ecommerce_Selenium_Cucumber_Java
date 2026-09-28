package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Page object for the SauceDemo inventory (products) and cart pages.
 * Items are located by their displayed product name, so no id-slug
 * knowledge is needed.
 */
public class SauceInventoryPage extends BasePage {

    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By inventoryItemName = By.cssSelector(".inventory_item_name");
    private final By inventoryItemPrice = By.cssSelector(".inventory_item_price");
    private final By cartItems = By.cssSelector(".cart_item");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By checkoutBtn = By.id("checkout");

    /** Find an item container (inventory or cart) by product name. */
    private WebElement findItemByName(By containerLocator, String productName) {
        List<WebElement> items = driver.findElements(containerLocator);
        for (WebElement item : items) {
            String name = item.findElement(inventoryItemName).getText().trim();
            if (name.equalsIgnoreCase(productName.trim())) {
                return item;
            }
        }
        throw new NoSuchElementException(
                "Product not found: " + productName);
    }

    public boolean isOnProductsPage() {
        return getCurrentUrl() != null && getCurrentUrl().contains("inventory.html");
    }

    public void goToProductsPage(String inventoryUrl) {
        navigateToUrl(inventoryUrl);
        waitForElementToBeVisible(inventoryItems);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    /** Add a product to the cart from the inventory page. Returns its displayed price. */
    public BigDecimal addProductToCart(String productName) {
        WebElement item = findItemByName(inventoryItems, productName);
        BigDecimal price = parsePrice(item.findElement(inventoryItemPrice).getText());
        item.findElement(By.tagName("button")).click();
        return price;
    }

    /** Current text of the product's inventory button ("Add to cart" or "Remove"). */
    public String getProductButtonText(String productName) {
        WebElement item = findItemByName(inventoryItems, productName);
        return item.findElement(By.tagName("button")).getText().trim();
    }

    /** Open the product details page by clicking the product name. */
    public void openProductDetails(String productName) {
        findItemByName(inventoryItems, productName)
                .findElement(inventoryItemName).click();
    }

    public boolean isCartBadgeDisplayed() {
        return isElementDisplayed(cartBadge);
    }

    public int getCartBadgeCount() {
        return Integer.parseInt(getText(cartBadge).trim());
    }

    public void openCart() {
        click(cartLink);
        // cart page shows either items or an empty state; wait for it to settle
        waitForElementToBeVisible(checkoutBtn);
    }

    public List<String> getCartItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : driver.findElements(cartItems)) {
            names.add(item.findElement(inventoryItemName).getText().trim());
        }
        return names;
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isCartEmpty() {
        return getCartItemsCount() == 0;
    }

    /** Sum of the item prices currently shown on the cart page. */
    public BigDecimal getCartPricesTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (WebElement item : driver.findElements(cartItems)) {
            total = total.add(parsePrice(item.findElement(inventoryItemPrice).getText()));
        }
        return total;
    }

    public void removeProductFromCart(String productName) {
        WebElement item = findItemByName(cartItems, productName);
        item.findElement(By.tagName("button")).click();
    }

    public void removeAllItemsFromCart() {
        for (String name : getCartItemNames()) {
            removeProductFromCart(name);
        }
    }

    public void clickContinueShopping() {
        click(continueShoppingBtn);
    }

    public boolean isContinueShoppingDisplayed() {
        return isElementDisplayed(continueShoppingBtn);
    }

    public boolean isCheckoutButtonVisible() {
        return isElementDisplayed(checkoutBtn);
    }

    private BigDecimal parsePrice(String text) {
        return new BigDecimal(text.replaceAll("[^0-9.]", ""));
    }
}
