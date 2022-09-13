package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {

    private final Map<String, Supplier<WebDriver>> driverMap = new HashMap<>();

    public DriverFactory() {
        // add all the drivers into a map
        driverMap.put("Chrome", chromeDriverSupplier);
        driverMap.put("RemoteChrome", remoteChromeDriverSupplier);
    }

    /**
     * Chrome driver supplier for local execution
     */
    public final Supplier<WebDriver> chromeDriverSupplier = () -> {
        WebDriverManager.chromedriver().arch64().setup();
        return new ChromeDriver();
    };

    public final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        WebDriverManager.firefoxdriver().arch64().setup();
        return new FirefoxDriver();
    };

    /**
     * Chrome driver supplier in remote CI/CD tool
     * Set up the automation run in headless mode
     */
    public final Supplier<WebDriver> remoteChromeDriverSupplier = () -> {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("window-size=1920,1080");
        return new ChromeDriver(options);
    };


    /**
     * This method returns the desired driver type
     */
    public final WebDriver getDriver(String desiredDriver) {
        return driverMap.get(desiredDriver).get();
    }
}