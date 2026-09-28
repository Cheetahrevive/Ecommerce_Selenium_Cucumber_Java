package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    /**
     * Waits for the post-login redirect instead of reading the URL immediately:
     * SauceDemo's login response time varies, and an immediate read raced it,
     * flaking the cart scenarios' Background login step.
     */
    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getPasswordFieldType() {
        return getAttribute(passwordField, "type");
    }

    /**
     * Open the burger menu. Uses a JavaScript click: in headless Chrome the
     * regular WebDriver click on this toggle is unreliable (the menu never
     * opens), while the app's handler fires fine on a dispatched click event.
     */
    public void openMenu() {
        clickWithJS(menuButton);
    }

    public void clickLogout() {
        waitForElementToBeVisible(logoutLink);
        clickWithJS(logoutLink);
    }
}
