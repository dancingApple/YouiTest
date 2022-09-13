package pageObjectFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.SuperClass;

public class CommonMenuPageObject extends SuperClass {
    private final By registerLink = By.xpath("//a[text()='Register']");
    private final By registerFormTitle = By.xpath("//h1[text()='Register']");
    private final By topMenuBooks = By.xpath("(//div[@class='header-menu']  //a[@href='/books'])[1]");
    private final By loginLink = By.xpath("//a[text()='Log in']");
    private final By cartLink = By.xpath("//a[@href='/cart']/span[1]");

    public CommonMenuPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void clickRegister() throws InterruptedException {
        clickElement(registerLink);
        shortWait(2);
    }

    public void clickLogin() throws InterruptedException {
        clickElement(loginLink);
        shortWait(2);
    }

    public String getRegisterTitle(){
        return getTextFromElement(registerFormTitle);
    }

    public void selectTopMenuBooks() throws InterruptedException {
        shortWait(2);
        clickElement(topMenuBooks);
    }

    public void goToCart() throws InterruptedException {
        clickElement(cartLink);
    }
}
