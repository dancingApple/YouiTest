package dataFiles;

import io.cucumber.core.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

import java.io.File;


/**
 * This class will hold and feed data for test execution
 * It has getter and setter function.
 */

public class ProjectDataClass {

    private ProjectDataClass dataInstance = null;
    private String username = "";
    private String password = "";
    private String user = "";

    private WebDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();
    private String platform = System.getProperty("env.USER");
    private String url;
    private Scenario scenarioName;

    /**
     * This Method is to get the feature name under tested.
     * Feed for later use by other function (e.g. Screenshot).
     */
    public String getFeatureName(Scenario scenario) {
        String[] rawFeatureName = scenario.getId().split(";")[0].
                replace("-"," ").split("/");
        return rawFeatureName[rawFeatureName.length-1].split("\\.")[0];
    }

    /**
     * This Method is to set the test env.
     * In case Scala Pay has multiple test env, this function enable to switch btw env.
     * Only one env provided for Technical test, so I just include a dummy env url.
     */
    public void setTestEnvironment(String testRegion) {
        switch (testRegion.toUpperCase()) {
            case "SCALAPAYTESTENV":
                url ="https://staging.partner.scalapay.com/login";
                System.out.println("*** ScalaPay test env is being used ***");
                break;
            case "OTHERENV":
                url ="https://otherEnv.com/";
                break;
            default:
                url ="https://staging.partner.scalapay.com/login";
                System.out.println("*** Default to use ScalaPay test env ***");
                break;
        }
    }
    /**
     * Method to launch URL
     */
    public void launchURL() {
        driver.get(url);
    }

    /**
     * Method to launch application
     */
    public void launchApplication(String browserName) {
        launchBrowser(browserName);
        launchURL();
        System.out.println("Application launched successfully.");
    }
    /**
     * Method to take screenshots
     */
    public void getScreenshot(String fileName) throws Exception {
        Thread.sleep(2000);
        File scrFile;
        File destFile;

        new File("target/cucumber-reports/advanced-reports/screenshot/" + scenarioName.getName());
        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        destFile = new File("target/cucumber-reports/advanced-reports/screenshot/"
                + scenarioName.getName() + "/" + fileName + ".png");
        if (!destFile.exists()) {
            FileUtils.copyFile(scrFile, destFile);
        }
        Thread.sleep(2000);
    }

    public void tearDown() {
        driver.quit();
        driver = null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void launchBrowser(String browserName) {
        if(platform==null) {
            platform = "local";
        }
        String combo = platform.toUpperCase() + browserName.toUpperCase();

        switch (combo) {
            case "REMOTECHROME":
                driver = driverFactory.getDriver("RemoteChrome");
                break;
            case "FIREFOX":
                driver = driverFactory.getDriver("FireFox");

            default:
            case "LOCALCHROME":
                driver = driverFactory.getDriver("Chrome");
                break;
        }
        driver.manage().window().maximize();
    }

    public void setScenarioName(Scenario scenarioName) { this.scenarioName = scenarioName; }
    public Scenario getScenarioName() { return scenarioName; }
    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }
    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }
    public void setUser(String user) { this.user = user; }

    public String getUser() {return user;}
}