# 🛒 E-Commerce Selenium Cucumber Java Automation Framework

![Java](https://img.shields.io/badge/Java-11-orange.svg)
![Selenium](https://img.shields.io/badge/Selenium-4.17.0-brightgreen.svg)
![Cucumber](https://img.shields.io/badge/Cucumber-7.15.0-brightgreen.svg)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red.svg)
![Maven](https://img.shields.io/badge/Maven-Project-blue.svg)
![Status](https://img.shields.io/badge/status-production%20ready-success.svg)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Scenarios](#test-scenarios)
- [Reports](#reports)
- [Page Object Model](#page-object-model)
- [Best Practices](#best-practices)
- [Contributing](#contributing)

## 🎯 Overview

This is a comprehensive End-to-End automation framework for e-commerce website testing built with:
- **Selenium WebDriver** for browser automation
- **Cucumber BDD** for behavior-driven development
- **Page Object Model (POM)** design pattern
- **TestNG** for test execution and management
- **Extent Reports** for detailed test reporting

## ✨ Features

- ✅ Complete E2E e-commerce user flows
- ✅ Product search and filtering
- ✅ Shopping cart operations
- ✅ Checkout and payment processing
- ✅ Order tracking until delivery
- ✅ Page Object Model architecture
- ✅ Cucumber BDD with Gherkin syntax
- ✅ Cross-browser testing support
- ✅ Parallel test execution
- ✅ Detailed HTML reports
- ✅ Screenshot on failure
- ✅ Data-driven testing
- ✅ Reusable utility classes

## 🏗️ Architecture

```
Framework follows Page Object Model (POM) + Cucumber BDD

┌─────────────┐
│  Features   │  ← Cucumber feature files (Gherkin)
└──────┬──────┘
       │
┌──────▼──────┐
│Step Definitions│  ← Glue code connecting features to pages
└──────┬──────┘
       │
┌──────▼──────┐
│ Page Objects│  ← Page classes with locators & methods
└──────┬──────┘
       │
┌──────▼──────┐
│   Utilities │  ← Helper classes & configurations
└─────────────┘
```

## 📁 Project Structure

```
Ecommerce_Selenium_Cucumber_Java/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/ecommerce/
│   │           ├── pages/              # Page Object Model classes
│   │           │   ├── HomePage.java
│   │           │   ├── SearchPage.java
│   │           │   ├── ProductPage.java
│   │           │   ├── CartPage.java
│   │           │   ├── CheckoutPage.java
│   │           │   ├── PaymentPage.java
│   │           │   └── OrderTrackingPage.java
│   │           ├── utils/              # Utility classes
│   │           │   ├── DriverManager.java
│   │           │   ├── ConfigReader.java
│   │           │   ├── ScreenshotUtil.java
│   │           │   ├── WaitHelper.java
│   │           │   └── TestDataReader.java
│   │           └── base/               # Base classes
│   │               └── BasePage.java
│   └── test/
│       ├── java/
│       │   └── com/ecommerce/
│       │       ├── stepdefinitions/    # Cucumber step definitions
│       │       │   ├── SearchSteps.java
│       │       │   ├── CartSteps.java
│       │       │   ├── CheckoutSteps.java
│       │       │   ├── PaymentSteps.java
│       │       │   └── TrackingSteps.java
│       │       ├── runners/            # Test runners
│       │       │   └── TestRunner.java
│       │       └── hooks/              # Cucumber hooks
│       │           └── Hooks.java
│       └── resources/
│           ├── features/                # Cucumber feature files
│           │   ├── ProductSearch.feature
│           │   ├── AddToCart.feature
│           │   ├── Checkout.feature
│           │   ├── Payment.feature
│           │   └── OrderTracking.feature
│           ├── config/                  # Configuration files
│           │   └── config.properties
│           ├── testdata/                # Test data
│           │   └── testdata.xlsx
│           └── extent.properties        # Extent report config
├── test-output/                         # TestNG reports
├── reports/                             # Extent reports
├── screenshots/                         # Failure screenshots
├── pom.xml                             # Maven dependencies
├── testng.xml                          # TestNG suite configuration
└── README.md                           # This file
```

## 📦 Prerequisites

- Java JDK 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser
- IDE (IntelliJ IDEA / Eclipse)

## 🚀 Installation

1. **Clone the repository:**
```bash
git clone https://github.com/Cheetahrevive/Ecommerce_Selenium_Cucumber_Java.git
cd Ecommerce_Selenium_Cucumber_Java
```

2. **Install dependencies:**
```bash
mvn clean install
```

3. **Configure settings:**
- Update `src/test/resources/config/config.properties` with your test environment details

## ⚙️ Configuration

### config.properties
```properties
# Browser Configuration
browser=chrome
headless=false
implicitWait=10
explicitWait=20

# Application URL
baseUrl=https://www.example-ecommerce.com

# Test Data
testDataPath=src/test/resources/testdata/testdata.xlsx

# Screenshots
screenshotOnFailure=true
screenshotPath=screenshots/

# Reports
reportPath=reports/
```

## 🏃 Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific feature:
```bash
mvn test -Dcucumber.filter.tags="@search"
```

### Run with specific browser:
```bash
mvn test -Dbrowser=firefox
```

### Run in headless mode:
```bash
mvn test -Dheadless=true
```

### Run with TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## 🧪 Test Scenarios

### Product Search Flow
```gherkin
Feature: Product Search
  
  @search @smoke
  Scenario: Search for a product successfully
    Given User is on the homepage
    When User searches for "laptop"
    Then Search results should be displayed
    And Results should contain "laptop"
```

### Complete E2E Flow
```gherkin
Feature: Complete Purchase Flow
  
  @e2e @regression
  Scenario: Complete purchase from search to delivery tracking
    Given User is on the homepage
    When User searches for "Samsung Galaxy S23"
    And User selects the first product
    And User adds product to cart
    And User proceeds to checkout
    And User enters shipping information
      | Name          | John Doe           |
      | Address       | 123 Main St        |
      | City          | New York           |
      | ZipCode       | 10001              |
    And User selects payment method "Credit Card"
    And User enters payment details
      | CardNumber    | 4111111111111111   |
      | CVV           | 123                |
      | ExpiryDate    | 12/25              |
    And User confirms the order
    Then Order confirmation should be displayed
    And Order tracking number should be generated
    When User tracks the order
    Then Order status should show "Processing"
```

## 📊 Reports

### Extent Reports
Automatically generated HTML reports with:
- Test execution summary
- Pass/Fail statistics
- Screenshots for failed tests
- Execution timeline
- Browser and environment details

**Location:** `reports/ExtentReport.html`

### TestNG Reports
Default TestNG HTML reports

**Location:** `test-output/index.html`

## 📦 Page Object Model

### Example Page Object Class
```java
public class ProductPage extends BasePage {
    
    // Locators
    @FindBy(id = "product-title")
    private WebElement productTitle;
    
    @FindBy(css = ".add-to-cart-btn")
    private WebElement addToCartButton;
    
    @FindBy(xpath = "//div[@class='price']")
    private WebElement price;
    
    // Constructor
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    // Page Actions
    public String getProductTitle() {
        return productTitle.getText();
    }
    
    public void clickAddToCart() {
        waitHelper.waitForElementToBeClickable(addToCartButton);
        addToCartButton.click();
    }
    
    public String getPrice() {
        return price.getText();
    }
}
```

## 🛠️ Best Practices

1. **Page Object Model**: All page elements and actions encapsulated in page classes
2. **Wait Strategies**: Explicit waits instead of Thread.sleep()
3. **Reusable Utilities**: Common functions in utility classes
4. **Data-Driven**: Test data separated from test scripts
5. **Logging**: Comprehensive logging for debugging
6. **Screenshots**: Automatic screenshot capture on test failure
7. **Clean Code**: Follow SOLID principles and coding standards
8. **Version Control**: .gitignore configured for Maven/Java projects

## 📄 Key Files

### TestNG Configuration (testng.xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="E-Commerce Test Suite" parallel="tests" thread-count="3">
    <test name="Chrome Tests">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="com.ecommerce.runners.TestRunner"/>
        </classes>
    </test>
</suite>
```

### Feature File Example
```gherkin
@e2e @checkout
Feature: Checkout Process
  As a customer
  I want to complete the checkout process
  So that I can purchase products

  Background:
    Given User has items in the cart
    And User is on the checkout page

  Scenario: Successful checkout with credit card
    When User enters shipping details
    And User selects "Credit Card" as payment method
    And User enters valid card details
    And User clicks "Place Order"
    Then Order should be placed successfully
    And User should receive order confirmation number

  Scenario Outline: Checkout with different payment methods
    When User enters shipping details
    And User selects "<PaymentMethod>" as payment method
    And User enters payment details for "<PaymentMethod>"
    And User clicks "Place Order"
    Then Order should be placed successfully
    
    Examples:
      | PaymentMethod |
      | Credit Card   |
      | Debit Card    |
      | PayPal        |
```

## 📈 Test Coverage

- **Search Functionality**: Product search, filters, sorting
- **Product Details**: View product details, specifications, reviews
- **Shopping Cart**: Add/Remove items, update quantities
- **Checkout Process**: Guest checkout, registered user checkout
- **Payment Processing**: Multiple payment methods
- **Order Management**: Order confirmation, order history
- **Order Tracking**: Track order status until delivery

## 👥 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License

## 📧 Contact

**Author**: Cheetahrevive  
**GitHub**: [@Cheetahrevive](https://github.com/Cheetahrevive)  
**Repository**: [Ecommerce_Selenium_Cucumber_Java](https://github.com/Cheetahrevive/Ecommerce_Selenium_Cucumber_Java)

---

**⭐ If you find this project helpful, please give it a star!**

**Built with** ❤️ **using Selenium, Cucumber, TestNG & Java**
