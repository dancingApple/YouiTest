package pageObjectFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.SuperClass;

import java.util.ArrayList;

public class CartPageObject extends SuperClass {
    private final By itemsInCart = By.xpath("//tr[@class='cart-item-row']//a");
    private final By itemUnitPrice = By.xpath("//tr[@class='cart-item-row']//span[@class='product-unit-price']");
    private final By itemQtys = By.xpath("//tr[@class='cart-item-row']//span[text()='Qty.:']/following-sibling::input");
    private final By itemTotal = By.xpath("//tr[@class='cart-item-row']//span[text()='Total:']/following-sibling::span");
    private final By subtotal = By.xpath("(//span[@class='product-price'])[1]");
    private final By tncTickBox = By.id("termsofservice");
    private final By checkOutBtn = By.id("checkout");
    public CartPageObject(WebDriver driver){
        this.driver = driver;
    }

    public ArrayList<String> getItemsInCart(){
        return getListOfItems(itemsInCart,"text");
    }
    public ArrayList<String> getItemUnitPriceInCart(){
        return getListOfItems(itemUnitPrice,"text");
    }
    public ArrayList<String> getItemQtysInCart(){
        return getListOfItems(itemQtys,"value");
    }
    public ArrayList<String> getItemTotalInCart(){
        return getListOfItems(itemTotal,"text");
    }

    public String subTotal(){
        return getTextFromElement(subtotal);
    }
    public void checkTncBox() throws InterruptedException {
        clickElement(tncTickBox);
    }
    public void clickCheckOutBtn() throws InterruptedException {
        clickElement(checkOutBtn);
    }
}
