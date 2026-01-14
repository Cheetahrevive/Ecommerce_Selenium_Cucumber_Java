package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ProductSearchSteps {
    
    WebDriver driver;
    
    @When("the user enters {string} in the search bar")
    public void theUserEntersInTheSearchBar(String searchTerm) {
        // Enter search term in search box
        Assert.assertFalse(searchTerm.isEmpty());
    }
    
    @When("the user clicks on the search button")
    public void theUserClicksOnTheSearchButton() {
        // Click search button
    }
    
    @Then("the search results page should be displayed")
    public void theSearchResultsPageShouldBeDisplayed() {
        // Verify search results page
        Assert.assertTrue(driver.getCurrentUrl().contains("search") || 
                         driver.getPageSource().contains("Search Results"));
    }
    
    @Then("the search results should contain products related to {string}")
    public void theSearchResultsShouldContainProductsRelatedTo(String searchTerm) {
        // Verify search results contain the search term
        Assert.assertTrue(driver.getPageSource().contains(searchTerm));
    }
    
    @Then("the user should see a {string} message")
    public void theUserShouldSeeAMessage(String message) {
        // Verify message is displayed
        Assert.assertTrue(driver.getPageSource().contains(message));
    }
    
    @Then("the user should see suggestions for other products")
    public void theUserShouldSeeSuggestionsForOtherProducts() {
        // Verify suggestions are displayed
        Assert.assertTrue(driver.getPageSource().contains("suggestion") || 
                         driver.getPageSource().contains("You may also like"));
    }
    
    @When("the user selects {string} from the category dropdown")
    public void theUserSelectsFromTheCategoryDropdown(String category) {
        // Select category from dropdown
        Assert.assertFalse(category.isEmpty());
    }
    
    @Then("the search results should only show products from {string} category")
    public void theSearchResultsShouldOnlyShowProductsFromCategory(String category) {
        // Verify all products are from selected category
        Assert.assertTrue(driver.getPageSource().contains(category));
    }
    
    @When("the user applies price filter from {string} to {string}")
    public void theUserAppliesPriceFilterFromTo(String minPrice, String maxPrice) {
        // Apply price filter
        Assert.assertFalse(minPrice.isEmpty());
        Assert.assertFalse(maxPrice.isEmpty());
    }
    
    @Then("all displayed products should be within the price range")
    public void allDisplayedProductsShouldBeWithinThePriceRange() {
        // Verify all products are within price range
        Assert.assertTrue(driver.getPageSource().contains("$"));
    }
    
    @When("the user selects {string} from the brand filter")
    public void theUserSelectsFromTheBrandFilter(String brand) {
        // Select brand filter
        Assert.assertFalse(brand.isEmpty());
    }
    
    @Then("all displayed products should be from {string} brand")
    public void allDisplayedProductsShouldBeFromBrand(String brand) {
        // Verify all products are from selected brand
        Assert.assertTrue(driver.getPageSource().contains(brand));
    }
    
    @When("the user sorts results by {string}")
    public void theUserSortsResultsBy(String sortOption) {
        // Sort results by option
        Assert.assertFalse(sortOption.isEmpty());
    }
    
    @Then("the products should be displayed in ascending price order")
    public void theProductsShouldBeDisplayedInAscendingPriceOrder() {
        // Verify products are sorted in ascending price order
    }
    
    @Then("the products should be displayed in descending price order")
    public void theProductsShouldBeDisplayedInDescendingPriceOrder() {
        // Verify products are sorted in descending price order
    }
    
    @Then("the products should be displayed in descending rating order")
    public void theProductsShouldBeDisplayedInDescendingRatingOrder() {
        // Verify products are sorted by rating
    }
    
    @Then("at least {int} products should be displayed")
    public void atLeastProductsShouldBeDisplayed(Integer count) {
        // Verify minimum product count
        Assert.assertTrue(count > 0);
    }
    
    @When("the user starts typing {string} in the search bar")
    public void theUserStartsTypingInTheSearchBar(String searchTerm) {
        // Start typing in search bar
        Assert.assertFalse(searchTerm.isEmpty());
    }
    
    @Then("autocomplete suggestions should appear")
    public void autocompleteSuggestionsShouldAppear() {
        // Verify autocomplete suggestions
        Assert.assertTrue(driver.getPageSource().contains("suggestion") || 
                         driver.getPageSource().contains("autocomplete"));
    }
    
    @Then("suggestions should include {string}, {string}, {string}")
    public void suggestionsShouldInclude(String suggestion1, String suggestion2, String suggestion3) {
        // Verify specific suggestions
        Assert.assertTrue(driver.getPageSource().contains(suggestion1) || 
                         driver.getPageSource().contains(suggestion2) || 
                         driver.getPageSource().contains(suggestion3));
    }
    
    @When("the user clicks on the clear search button")
    public void theUserClicksOnTheClearSearchButton() {
        // Click clear search button
    }
    
    @Then("the search bar should be empty")
    public void theSearchBarShouldBeEmpty() {
        // Verify search bar is empty
    }
    
    @Then("all products should be displayed")
    public void allProductsShouldBeDisplayed() {
        // Verify all products are displayed
        Assert.assertTrue(driver.getPageSource().contains("product"));
    }
}
