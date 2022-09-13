package pageObjectFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.SuperClass;

public class BooksPageObject extends SuperClass {
    private final By addToCartFeedbackMsg = By.xpath("//p[text()='The product has been added to your ']");

    public BooksPageObject(WebDriver driver){
        this.driver = driver;
    }
    public void addToCart(String bookName) throws InterruptedException {
        By targetAddToCart = By.xpath("//a[text()='"+bookName+"']/ancestor:: div[@class='details'] //input");
        clickElement(targetAddToCart);
    }

    public boolean checkFeedbackMsgDisplayed() throws InterruptedException {
        return isElementDisplayed(addToCartFeedbackMsg);
    }
}
