package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import hooks.Hooks;
import pages.HomePage;

public class LoginSteps {
    private WebDriver driver;
    private HomePage homePage;
    private String loginErrorMessage;

    public LoginSteps() {
        this.driver = Hooks.getDriver();
        this.homePage = new HomePage(driver);
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        homePage.clickLoginButton();
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        // Already navigated via Hooks
        Assert.assertTrue(driver.getTitle().contains("Swag Labs"));
    }

    @When("the user enters valid username {string} and password {string}")
    public void theUserEntersValidUsernameAndPassword(String username, String password) {
        homePage.login(username, password);
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String username, String password) {
        homePage.login(username, password);
    }

    @When("the user enters locked username {string} and password {string}")
    public void theUserEntersLockedUsernameAndPassword(String username, String password) {
        homePage.login(username, password);
    }

    @When("the user leaves the username field empty and enters password {string}")
    public void theUserLeavesUsernameEmptyAndEntersPassword(String password) {
        homePage.login("", password);
    }

    @When("the user enters username {string} and leaves the password field empty")
    public void theUserEntersUsernameAndLeavesPasswordEmpty(String username) {
        homePage.login(username, "");
    }

    @When("the user leaves both username and password fields empty")
    public void theUserLeavesBothFieldsEmpty() {
        homePage.clickLoginButtonOnly();
    }

    @Then("the user should be redirected to the products page")
    public void theUserShouldBeRedirectedToProductsPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("the user should see an error message {string}")
    public void theUserShouldSeeErrorMessage(String expectedMessage) {
        String actualMessage = homePage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Then("the user should see the locked out error message")
    public void theUserShouldSeeLockedOutErrorMessage() {
        String errorMessage = homePage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("locked out"));
    }

    @Then("the user should remain on the login page")
    public void theUserShouldRemainOnLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/"));
    }
}
