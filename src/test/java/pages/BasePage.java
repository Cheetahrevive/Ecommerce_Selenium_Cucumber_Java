package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;

/**
 * BasePage class containing common methods for all page objects
 * Implements reusable methods for web element interactions
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    
    // Wait Methods
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    protected void waitForElementToBeInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    // Click Methods
    protected void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }
    
    protected void clickWithJS(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    // Input Methods
    protected void typeText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    protected void clearAndType(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    // Get Methods
    protected String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }
    
    protected String getAttribute(By locator, String attribute) {
        return waitForElementToBeVisible(locator).getAttribute(attribute);
    }
    
    protected List<WebElement> getElements(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }
    
    // Verification Methods
    protected boolean isElementDisplayed(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    protected boolean isElementEnabled(By locator) {
        return waitForElementToBeVisible(locator).isEnabled();
    }
    
    // Scroll Methods
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    protected void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    // Page Methods
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    protected String getPageTitle() {
        return driver.getTitle();
    }
    
    protected void navigateToUrl(String url) {
        driver.get(url);
    }
    
    // Screenshot support
    protected void takeScreenshot(String screenshotName) {
        // Screenshot logic will be implemented in test hooks
    }
}
