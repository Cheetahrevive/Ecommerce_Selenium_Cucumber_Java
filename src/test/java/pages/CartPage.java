package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * CartPage class representing shopping cart page
 * Contains locators and methods for cart operations
 */
public class CartPage extends BasePage {
    
    // Locators
    private final By cartItems = By.cssSelector(".cart-item");
    private final By cartItemTitle = By.cssSelector(".cart-item-title");
    private final By cartItemPrice = By.cssSelector(".cart-item-price");
    private final By cartItemQuantity = By.cssSelector(".cart-item-quantity");
    private final By removeItemBtn = By.cssSelector(".remove-item");
    private final By updateQuantityBtn = By.cssSelector(".update-quantity");
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By proceedToCheckoutBtn = By.id("proceed-checkout");
    private final By cartSubtotal = By.cssSelector(".cart-subtotal");
    private final By cartTotal = By.cssSelector(".cart-total");
    private final By emptyCartMessage = By.cssSelector(".empty-cart-message");
    private final By applyCouponBtn = By.id("apply-coupon");
    private final By couponInput = By.id("coupon-code");
    
    /**
     * Get cart items count
     * @return Number of items in cart
     */
    public int getCartItemsCount() {
        return getElements(cartItems).size();
    }
    
    /**
     * Get cart item titles
     * @return List of cart item titles
     */
    public List<String> getCartItemTitles() {
        return getElements(cartItemTitle).stream()
                .map(WebElement::getText)
                .toList();
    }
    
    /**
     * Remove item from cart
     * @param itemIndex Index of item to remove
     */
    public void removeItemFromCart(int itemIndex) {
        List<WebElement> removeButtons = getElements(removeItemBtn);
        if (itemIndex < removeButtons.size()) {
            removeButtons.get(itemIndex).click();
        }
    }
    
    /**
     * Get cart subtotal
     * @return Cart subtotal text
     */
    public String getCartSubtotal() {
        return getText(cartSubtotal);
    }
    
    /**
     * Get cart total
     * @return Cart total text
     */
    public String getCartTotal() {
        return getText(cartTotal);
    }
    
    /**
     * Proceed to checkout
     */
    public void proceedToCheckout() {
        click(proceedToCheckoutBtn);
    }
    
    /**
     * Continue shopping
     */
    public void continueShopping() {
        click(continueShoppingBtn);
    }
    
    /**
     * Check if cart is empty
     * @return true if cart is empty
     */
    public boolean isCartEmpty() {
        return isElementDisplayed(emptyCartMessage);
    }
    
    /**
     * Apply coupon code
     * @param couponCode Coupon code to apply
     */
    public void applyCoupon(String couponCode) {
        typeText(couponInput, couponCode);
        click(applyCouponBtn);
    }
    
    /**
     * Verify cart page is displayed
     * @return true if cart page is displayed
     */
    public boolean isCartPageDisplayed() {
        return isElementDisplayed(proceedToCheckoutBtn) || isElementDisplayed(emptyCartMessage);
    }
}
