package pages;

import org.openqa.selenium.By;

/**
 * ProductPage class representing product details page
 * Contains locators and methods for product page interactions
 */
public class ProductPage extends BasePage {
    
    // Locators
    private final By productTitle = By.cssSelector(".product-title");
    private final By productPrice = By.cssSelector(".product-price");
    private final By productDescription = By.cssSelector(".product-description");
    private final By addToCartButton = By.id("add-to-cart");
    private final By buyNowButton = By.id("buy-now");
    private final By quantityInput = By.id("quantity");
    private final By increaseQuantityBtn = By.cssSelector(".quantity-increase");
    private final By decreaseQuantityBtn = By.cssSelector(".quantity-decrease");
    private final By productImage = By.cssSelector(".product-image");
    private final By addToWishlistBtn = By.cssSelector(".add-to-wishlist");
    private final By availabilityStatus = By.cssSelector(".availability-status");
    
    /**
     * Get product title
     * @return Product title
     */
    public String getProductTitle() {
        return getText(productTitle);
    }
    
    /**
     * Get product price
     * @return Product price
     */
    public String getProductPrice() {
        return getText(productPrice);
    }
    
    /**
     * Get product description
     * @return Product description
     */
    public String getProductDescription() {
        return getText(productDescription);
    }
    
    /**
     * Click add to cart button
     */
    public void clickAddToCart() {
        click(addToCartButton);
    }
    
    /**
     * Click buy now button
     */
    public void clickBuyNow() {
        click(buyNowButton);
    }
    
    /**
     * Set product quantity
     * @param quantity Quantity to set
     */
    public void setQuantity(String quantity) {
        clearAndType(quantityInput, quantity);
    }
    
    /**
     * Increase product quantity
     */
    public void increaseQuantity() {
        click(increaseQuantityBtn);
    }
    
    /**
     * Verify product is displayed
     * @return true if product is displayed
     */
    public boolean isProductDisplayed() {
        return isElementDisplayed(productTitle) && isElementDisplayed(productPrice);
    }
    
    /**
     * Add product to wishlist
     */
    public void addToWishlist() {
        click(addToWishlistBtn);
    }
    
    /**
     * Get availability status
     * @return Availability status text
     */
    public String getAvailabilityStatus() {
        return getText(availabilityStatus);
    }
}
