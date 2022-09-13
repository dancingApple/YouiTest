package util;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featureFiles"},
        glue = {"stepDefinitionFiles"},
        monochrome = true,
        tags = {"@test"},
        plugin = {"pretty", "html:target/cucumber-html-report",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "util.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class TestRunner {
}