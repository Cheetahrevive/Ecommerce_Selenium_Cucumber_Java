package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hooks class for Before and After scenario execution
 * Manages WebDriver initialization and cleanup
 * Handles screenshot capture on test failure
 */
public class Hooks {
    
    /**
     * Before hook to initialize WebDriver before each scenario
     * @param scenario Current scenario object
     */
    @Before
    public void setUp(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        DriverManager.initializeDriver(browser);
        System.out.println("Starting Scenario: " + scenario.getName());
    }
    
    /**
     * After hook to cleanup WebDriver and capture screenshots on failure
     * @param scenario Current scenario object
     */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Capture screenshot on failure
            try {
                String screenshotName = scenario.getName().replaceAll(" ", "_") + "_" + 
                        new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
                
                // Save screenshot to file
                File screenshotFile = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshotFile, 
                        new File("target/screenshots/" + screenshotName + ".png"));
                
                System.out.println("Screenshot captured: " + screenshotName);
            } catch (IOException e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        
        System.out.println("Scenario Status: " + scenario.getStatus());
        DriverManager.quitDriver();
    }
}
