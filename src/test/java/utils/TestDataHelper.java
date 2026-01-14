package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * TestDataHelper utility class for generating test data
 * Provides methods for creating realistic test data
 */
public class TestDataHelper {
    
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    
    /**
     * Generate random email address
     * @return Random email
     */
    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }
    
    /**
     * Generate random first name
     * @return Random first name
     */
    public static String generateFirstName() {
        return faker.name().firstName();
    }
    
    /**
     * Generate random last name
     * @return Random last name
     */
    public static String generateLastName() {
        return faker.name().lastName();
    }
    
    /**
     * Generate random phone number
     * @return Random phone number
     */
    public static String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    
    /**
     * Generate random street address
     * @return Random address
     */
    public static String generateAddress() {
        return faker.address().streetAddress();
    }
    
    /**
     * Generate random city
     * @return Random city
     */
    public static String generateCity() {
        return faker.address().city();
    }
    
    /**
     * Generate random state
     * @return Random state
     */
    public static String generateState() {
        return faker.address().state();
    }
    
    /**
     * Generate random ZIP code
     * @return Random ZIP code
     */
    public static String generateZipCode() {
        return faker.address().zipCode();
    }
    
    /**
     * Generate random credit card number
     * @return Random card number
     */
    public static String generateCardNumber() {
        return faker.business().creditCardNumber();
    }
    
    /**
     * Generate random card expiry date
     * @return Card expiry date (MM/YY format)
     */
    public static String generateCardExpiryDate() {
        int month = random.nextInt(12) + 1;
        int year = random.nextInt(5) + 25; // 2025-2029
        return String.format("%02d/%02d", month, year);
    }
    
    /**
     * Generate random CVV
     * @return 3-digit CVV
     */
    public static String generateCVV() {
        return String.format("%03d", random.nextInt(1000));
    }
    
    /**
     * Generate unique order number
     * @return Unique order number
     */
    public static String generateOrderNumber() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "ORD-" + timestamp + "-" + random.nextInt(1000);
    }
    
    /**
     * Generate random product name
     * @return Random product name
     */
    public static String generateProductName() {
        return faker.commerce().productName();
    }
    
    /**
     * Generate random price
     * @return Random price
     */
    public static String generatePrice() {
        return faker.commerce().price();
    }
    
    /**
     * Generate random tracking number
     * @return Tracking number
     */
    public static String generateTrackingNumber() {
        return "TRK" + System.currentTimeMillis() + random.nextInt(1000);
    }
    
    /**
     * Generate random coupon code
     * @return Coupon code
     */
    public static String generateCouponCode() {
        return "SAVE" + random.nextInt(100);
    }
}
