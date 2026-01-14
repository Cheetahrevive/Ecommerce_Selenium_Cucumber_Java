# 🎯 Demo Site Configuration Guide

## Supported Demo E-Commerce Sites

This framework is configured to work with popular e-commerce demo sites for test automation practice.

---

## 🛒 Sauce Demo (Recommended)

**URL:** https://www.saucedemo.com

### Test Credentials

#### Valid Users:
- **Standard User:** `standard_user` / `secret_sauce`
- **Problem User:** `problem_user` / `secret_sauce`
- **Performance User:** `performance_glitch_user` / `secret_sauce`
- **Visual User:** `visual_user` / `secret_sauce`

#### Locked User (for negative testing):
- **Username:** `locked_out_user`
- **Password:** `secret_sauce`

### Configuration

Update `src/test/resources/test.properties`:

```properties
baseUrl=https://www.saucedemo.com
test.user.email=standard_user
test.user.password=secret_sauce
```

### Available Test Products:
1. Sauce Labs Backpack - $29.99
2. Sauce Labs Bike Light - $9.99
3. Sauce Labs Bolt T-Shirt - $15.99
4. Sauce Labs Fleece Jacket - $49.99
5. Sauce Labs Onesie - $7.99
6. Test.allTheThings() T-Shirt (Red) - $15.99

### Supported Test Scenarios:
✅ Login (positive & negative)
✅ Product browsing & sorting
✅ Shopping cart operations
✅ Checkout process
✅ Complete purchase flow
✅ Logout

---

## 🛍️ DemoQA Bookstore

**URL:** https://demoqa.com/books

### Configuration

```properties
baseUrl=https://demoqa.com
```

### Supported Features:
- User registration
- Book search and filtering
- Add to collection
- User profile management

---

## 🏪 Automation Practice

**URL:** http://www.automationpractice.pl

### Configuration

```properties
baseUrl=http://www.automationpractice.pl
```

### Supported Features:
- User registration & login
- Product search
- Cart management
- Complete checkout
- Order history

---

## 🚀 Quick Start for Sauce Demo

### 1. Update Configuration

Edit `src/test/resources/test.properties`:

```properties
baseUrl=https://www.saucedemo.com
test.user.email=standard_user
test.user.password=secret_sauce
browser=chrome
headless=false
```

### 2. Run Tests

**Run all tests:**
```bash
mvn clean test
```

**Run specific feature:**
```bash
mvn test -Dcucumber.options="--tags @Login"
```

**Run smoke tests:**
```bash
mvn test -Dcucumber.options="--tags @Smoke"
```

**Run in headless mode:**
```bash
mvn test -Dbrowser=headless-chrome
```

### 3. View Reports

After test execution:
- **ExtentReports:** `target/cucumber-reports/cucumber.html`
- **Cucumber JSON:** `target/cucumber-reports/cucumber.json`
- **Screenshots:** `target/screenshots/`

---

## 📋 Test Execution Tags

| Tag | Description |
|-----|-------------|
| `@Smoke` | Critical path smoke tests |
| `@Regression` | Full regression suite |
| `@Login` | Login functionality tests |
| `@ShoppingCart` | Cart management tests |
| `@Checkout` | Checkout process tests |
| `@Positive` | Positive test scenarios |
| `@Negative` | Negative test scenarios |
| `@Functional` | Functional test cases |
| `@Security` | Security-related tests |

---

## 🔧 Browser Configuration

```bash
# Chrome (default)
mvn test -Dbrowser=chrome

# Firefox
mvn test -Dbrowser=firefox

# Edge
mvn test -Dbrowser=edge

# Headless Chrome
mvn test -Dbrowser=headless-chrome
```

---

## 📊 Current Test Coverage

### Login Feature (8 scenarios)
- ✅ Successful login
- ✅ Invalid credentials
- ✅ Empty fields
- ✅ Locked user
- ✅ Multiple users
- ✅ Password masking
- ✅ Logout

### Shopping Cart Feature (10 scenarios)
- ✅ Add single/multiple products
- ✅ Remove products
- ✅ Cart persistence
- ✅ Total calculation
- ✅ Empty cart handling
- ✅ Badge visibility

### Complete E2E Purchase Feature (9 scenarios)
- ✅ Full purchase flow
- ✅ Guest checkout
- ✅ Order tracking
- ✅ Delivery confirmation

**Total: 27+ test scenarios** ready to run!

---

## 💡 Tips for Best Results

1. **Start with Sauce Demo** - Most stable and well-documented
2. **Run smoke tests first** - Validate basic functionality
3. **Check reports** - Review ExtentReports for detailed results
4. **Use tags** - Run specific test categories as needed
5. **Parallel execution** - Leverage TestNG parallel configuration

---

## 🐛 Troubleshooting

**Issue:** Browser not opening
- **Solution:** Check WebDriverManager dependency and internet connection

**Issue:** Test failures on Sauce Demo
- **Solution:** Verify credentials and site availability

**Issue:** Screenshot not captured
- **Solution:** Check `target/screenshots/` directory permissions

---

## 📞 Support

For issues or questions:
1. Check existing test scenarios in `src/test/resources/features/`
2. Review page objects in `src/test/java/pages/`
3. Verify configuration in `src/test/resources/test.properties`

---

**Framework is ready to test any demo e-commerce site!** 🚀
