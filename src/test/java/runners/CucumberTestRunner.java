package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/cucumber.html",
                "json:test-output/cucumber-reports/cucumber.json",
                "junit:test-output/cucumber-reports/cucumber.xml"
        },
        monochrome = true,
        dryRun = false,
        tags = "@smoke or @regression"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
