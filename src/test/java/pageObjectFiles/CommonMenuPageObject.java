package pageObjectFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.SuperClass;

public class CommonMenuPageObject extends SuperClass {
    private final By salesPage = By.xpath("(//a[@href='/account/merchant/dashboard'])[2]");
    private final By payoutsPage = By.xpath("(//a[@href='/account/merchant/payouts'])[2]");
    private final By reportsPage = By.xpath("(//a[@href='/account/reports/dashboard'])[2]");
    private final By accountPage = By.xpath("(//a[@href='/account/profile'])[2]");
    private final By logout = By.xpath("(//a[@href='/logout'])[2]");
    private final By scalaPayLogo = By.xpath("(//img[@src='/images/scalapay-logo.svg'])[2]");

    public CommonMenuPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean verifyMenuExists() throws InterruptedException {
        return isElementDisplayed(salesPage) &&
                isElementDisplayed(payoutsPage) &&
                isElementDisplayed(reportsPage) &&
                isElementDisplayed(accountPage) &&
                isElementDisplayed(logout) &&
                isElementDisplayed(scalaPayLogo);
    }
}
