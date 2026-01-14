# 🚀 Project Implementation Guide

## Quick Start for Developers

This guide will help you implement the complete framework and start writing tests.

## 📋 Step-by-Step Implementation

### Step 1: Clone and Setup

```bash
git clone https://github.com/Cheetahrevive/Ecommerce_Selenium_Cucumber_Java.git
cd Ecommerce_Selenium_Cucumber_Java
mvn clean install
```

### Step 2: Create Project Structure

Create the following directories:

```bash
mkdir -p src/main/java/com/ecommerce/{pages,utils,base}
mkdir -p src/test/java/com/ecommerce/{stepdefinitions,runners,hooks}
mkdir -p src/test/resources/{config,testdata}
mkdir -p screenshots reports
```

### Step 3: Create Configuration Files

#### config.properties
**Location:** `src/test/resources/config/config.properties`

```properties
# Browser Configuration
browser=chrome
headless=false
implicitWait=10
explicitWait=20
pageLoadTimeout=30

# Application URL
baseUrl=https://www.saucedemo.com

# Test Credentials (for demo site)
testUsername=standard_user
testPassword=secret_sauce

# Test Data
testDataPath=src/test/resources/testdata/testdata.xlsx

# Screenshots
screenshotOnFailure=true
screenshotPath=screenshots/

# Reports
reportPath=reports/
reportTitle=E-Commerce Automation Test Report
reportName=Test Execution Report
```

#### extent.properties
**Location:** `src/test/resources/extent.properties`

```properties
extent.reporter.spark.start=true
extent.reporter.spark.out=reports/ExtentReport.html
extent.reporter.spark.config=src/test/resources/extent-config.xml

screenshot.dir=screenshots/
screenshot.rel.path=../screenshots/

extent.reporter.pdf.start=false
extent.reporter.json.start=false
```

### Step 4: Implement Base Classes

#### BasePage.java
**Location:** `src/main/java/com/ecommerce/base/BasePage.java`

```java
package com.ecommerce.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ecommerce.utils.WaitHelper;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WaitHelper waitHelper;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    protected void click(WebElement element) {
        waitHelper.waitForElementToBeClickable(element);
        element.click();
    }
    
    protected void sendKeys(WebElement element, String text) {
        waitHelper.waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }
    
    protected String getText(WebElement element) {
        waitHelper.waitForElementToBeVisible(element);
        return element.getText();
    }
    
    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
```

### Step 5: Create Utility Classes

#### DriverManager.java
**Location:** `src/main/java/com/ecommerce/utils/DriverManager.java`

```java
package com.ecommerce.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ConfigReader configReader = new ConfigReader();
    
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }
    
    private static WebDriver createDriver() {
        WebDriver webDriver = null;
        String browser = configReader.getBrowser();
        boolean headless = configReader.isHeadless();
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                webDriver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
                
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        
        webDriver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(configReader.getImplicitWait()));
        webDriver.manage().timeouts().pageLoadTimeout(
            Duration.ofSeconds(configReader.getPageLoadTimeout()));
        webDriver.manage().window().maximize();
        
        return webDriver;
    }
    
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
```

### Step 6: Create Test Runner

#### TestRunner.java
**Location:** `src/test/java/com/ecommerce/runners/TestRunner.java`

```java
package com.ecommerce.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.ecommerce.stepdefinitions", "com.ecommerce.hooks"},
    tags = "@e2e or @smoke",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true,
    dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
```

### Step 7: Create Hooks

#### Hooks.java
**Location:** `src/test/java/com/ecommerce/hooks/Hooks.java`

```java
package com.ecommerce.hooks;

import com.ecommerce.utils.DriverManager;
import com.ecommerce.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private WebDriver driver;
    
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        driver = DriverManager.getDriver();
    }
    
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtil.captureScreenshot(driver);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        System.out.println("Scenario " + scenario.getName() + 
                         " finished with status: " + scenario.getStatus());
        DriverManager.quitDriver();
    }
}
```

## 🎯 Running Your First Test

### Option 1: Maven Command Line

```bash
# Run all tests
mvn clean test

# Run specific tags
mvn test -Dcucumber.filter.tags="@smoke"
mvn test -Dcucumber.filter.tags="@e2e"

# Run with specific browser
mvn test -Dbrowser=firefox

# Run in headless mode
mvn test -Dheadless=true
```

### Option 2: TestNG XML

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Option 3: IDE (IntelliJ IDEA / Eclipse)

1. Right-click on `TestRunner.java`
2. Select "Run 'TestRunner'"
3. Or right-click on `testng.xml` and select "Run"

## 📊 Viewing Reports

After test execution:

1. **Extent Report**: Open `reports/ExtentReport.html` in browser
2. **Cucumber Report**: Open `target/cucumber-reports/cucumber.html`
3. **TestNG Report**: Open `test-output/index.html`

## 🔧 Troubleshooting

### Issue: WebDriver not found
**Solution**: WebDriverManager will auto-download. Ensure internet connection.

### Issue: Tests failing
**Solution**: 
- Check if baseUrl in config.properties is accessible
- Verify browser is installed
- Check element locators in page objects

### Issue: Extent Reports not generating
**Solution**: 
- Verify extent.properties file exists
- Check reports directory permissions
- Ensure ExtentCucumberAdapter dependency in pom.xml

## 📚 Next Steps

1. ✅ Implement remaining Page Objects
2. ✅ Create Step Definitions for all scenarios
3. ✅ Add more feature files for different flows
4. ✅ Integrate with CI/CD (Jenkins/GitHub Actions)
5. ✅ Add API testing layer
6. ✅ Add database validation

## 💡 Tips for Success

- Keep Page Objects focused and single-responsibility
- Use meaningful locator strategies (ID > CSS > XPath)
- Implement explicit waits over implicit waits
- Use data-driven approach for test data
- Follow naming conventions consistently
- Write descriptive Gherkin scenarios
- Keep step definitions reusable
- Use tags effectively for test organization
- Regular code reviews and refactoring

## 🤝 Need Help?

Refer to:
- Main [README.md](README.md) for complete documentation
- Feature file examples in `src/test/resources/features/`
- Cucumber documentation: https://cucumber.io/docs
- Selenium documentation: https://www.selenium.dev/documentation/
- TestNG documentation: https://testng.org/doc/documentation-main.html

---

**Happy Testing! 🚀**
