package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {
    private WebDriver driver;

    public ScreenshotHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String takeScreenshot(String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String screenshotPath = ConfigReader.getScreenshotPath() + fileName;

        try {
            // Create screenshot directory if it doesn't exist
            File screenshotDir = new File(ConfigReader.getScreenshotPath());
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Take screenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath);

            // Copy screenshot to destination
            FileUtils.copyFile(sourceFile, destinationFile);

            System.out.println("Screenshot saved: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }

    public byte[] takeScreenshotAsBytes() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }

    public String takeScreenshotOnFailure(String scenarioName) {
        return takeScreenshot("FAILED_" + scenarioName.replaceAll(" ", "_"));
    }
}
