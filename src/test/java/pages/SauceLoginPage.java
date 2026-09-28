package pages;

import org.openqa.selenium.By;

/**
 * Page object for the SauceDemo login page (https://www.saucedemo.com).
 * This is the demo site the CI-gated Login.feature suite runs against.
 */
public class SauceLoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public void open(String url) {
        navigateToUrl(url);
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginButton);
    }

    public void enterUsername(String username) {
        typeText(usernameField, username);
    }

    public void enterPassword(String password) {
        typeText(passwordField, password);
    }

    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void clickLogin() {
        click(loginButton);
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isLoggedIn() {
        return getCurrentUrl().contains("inventory.html");
    }

    public String getPasswordFieldType() {
        return getAttribute(passwordField, "type");
    }

    public void openMenu() {
        click(menuButton);
    }

    public void clickLogout() {
        click(logoutLink);
    }
}
