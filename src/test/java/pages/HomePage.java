package pages;

import org.openqa.selenium.By;

/**
 * HomePage class representing the e-commerce home page
 * Contains locators and methods for home page interactions
 */
public class HomePage extends BasePage {
    
    // Locators
    private final By searchBox = By.id("search");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By cartIcon = By.cssSelector(".cart-icon");
    private final By logo = By.cssSelector(".logo");
    private final By categoryMenu = By.cssSelector(".category-menu");
    private final By loginLink = By.linkText("Login");
    private final By signupLink = By.linkText("Sign Up");
    
    /**
     * Navigate to home page
     * @param url Application URL
     */
    public void navigateToHomePage(String url) {
        navigateToUrl(url);
    }
    
    /**
     * Search for a product
     * @param productName Product name to search
     */
    public void searchForProduct(String productName) {
        typeText(searchBox, productName);
        click(searchButton);
    }
    
    /**
     * Click on cart icon
     */
    public void clickCartIcon() {
        click(cartIcon);
    }
    
    /**
     * Verify home page is displayed
     * @return true if home page is displayed
     */
    public boolean isHomePageDisplayed() {
        return isElementDisplayed(logo);
    }
    
    /**
     * Get home page title
     * @return Page title
     */
    public String getHomePageTitle() {
        return getPageTitle();
    }
    
    /**
     * Click on login link
     */
    public void clickLogin() {
        click(loginLink);
    }
    
    /**
     * Click on signup link
     */
    public void clickSignup() {
        click(signupLink);
    }
}
