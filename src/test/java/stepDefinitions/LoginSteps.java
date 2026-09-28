package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.SauceLoginPage;
import utils.ConfigReader;

/**
 * Step definitions for Login.feature, targeting the SauceDemo demo site
 * (https://www.saucedemo.com). Step phrases match the feature file exactly.
 */
public class LoginSteps {

    private static final String VALID_USER = "standard_user";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String LOCKED_USER = "locked_out_user";

    private SauceLoginPage loginPage;

    /**
     * Lazily create the page object so the WebDriver (started in Hooks @Before)
     * is available before first use.
     */
    private SauceLoginPage page() {
        if (loginPage == null) {
            loginPage = new SauceLoginPage();
        }
        return loginPage;
    }

    @Given("I am on the e-commerce homepage")
    public void iAmOnTheEcommerceHomepage() {
        page().open(ConfigReader.getProperty("baseUrl"));
        Assert.assertTrue(page().isLoginPageDisplayed(), "Login page was not displayed");
    }

    @When("I enter valid username and password")
    public void iEnterValidUsernameAndPassword() {
        page().enterUsername(VALID_USER);
        page().enterPassword(VALID_PASSWORD);
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        page().clickLogin();
    }

    @Then("I should be successfully logged in")
    public void iShouldBeSuccessfullyLoggedIn() {
        Assert.assertTrue(page().isLoggedIn(), "User was not logged in");
    }

    @Then("I should see the products page")
    public void iShouldSeeTheProductsPage() {
        Assert.assertTrue(page().isLoggedIn(), "Products page was not displayed");
    }

    @When("I enter invalid username {string} and valid password")
    public void iEnterInvalidUsernameAndValidPassword(String username) {
        page().enterUsername(username);
        page().enterPassword(VALID_PASSWORD);
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
        Assert.assertTrue(page().isErrorDisplayed(), "Expected an error message but none was shown");
        Assert.assertTrue(page().getErrorMessage().contains(expectedMessage),
                "Error message mismatch. Actual: " + page().getErrorMessage());
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        Assert.assertFalse(page().isLoggedIn(), "User was unexpectedly logged in");
        Assert.assertTrue(page().isLoginPageDisplayed(), "Not on the login page");
    }

    @When("I enter valid username and invalid password {string}")
    public void iEnterValidUsernameAndInvalidPassword(String password) {
        page().enterUsername(VALID_USER);
        page().enterPassword(password);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue(page().isErrorDisplayed(), "Expected an error message but none was shown");
    }

    @When("I leave username and password fields empty")
    public void iLeaveUsernameAndPasswordFieldsEmpty() {
        page().enterUsername("");
        page().enterPassword("");
    }

    @When("I enter locked user credentials")
    public void iEnterLockedUserCredentials() {
        page().enterUsername(LOCKED_USER);
        page().enterPassword(VALID_PASSWORD);
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        page().enterUsername(username);
        page().enterPassword(password);
    }

    @When("I enter password in the password field")
    public void iEnterPasswordInThePasswordField() {
        page().enterPassword(VALID_PASSWORD);
    }

    @Then("the password should be displayed as masked characters")
    public void thePasswordShouldBeDisplayedAsMaskedCharacters() {
        Assert.assertEquals(page().getPasswordFieldType(), "password",
                "Password field is not masked");
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        page().loginAs(VALID_USER, VALID_PASSWORD);
        Assert.assertTrue(page().isLoggedIn(), "Login with valid credentials failed");
    }

    @When("I click on the menu button")
    public void iClickOnTheMenuButton() {
        page().openMenu();
    }

    @When("I click on logout")
    public void iClickOnLogout() {
        page().clickLogout();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        Assert.assertTrue(page().isLoginPageDisplayed(), "Not redirected to the login page");
    }
}
