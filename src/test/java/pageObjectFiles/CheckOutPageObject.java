package pageObjectFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.SuperClass;

public class CheckOutPageObject extends SuperClass {
    private final By billContinueBtn = By.xpath("(//input[@title='Continue'])[1]");
    private final By shipContinueBtn = By.xpath("(//input[@title='Continue'])[2]");
    private final By shipMethContinueBtn = By.xpath("(//input[@value='Continue'])[3]");
    private final By payMethContinueBtn = By.xpath("(//input[@value='Continue'])[4]");
    private final By payInfoContinueBtn = By.xpath("(//input[@value='Continue'])[5]");
    private final By confirmOrder = By.xpath("//input[@value='Confirm']");
    private final By shipMethGround = By.xpath("//label[@for='shippingoption_0']");
    private final By shipMethNextAir = By.xpath("//label[@for='shippingoption_1']");
    private final By shipMeth2DAir = By.xpath("//label[@for='shippingoption_2']");
    private final By paymentMethCOD = By.xpath("(//label[@for='paymentmethod_0'])[2]");
    private final By paymentMethCheck = By.xpath("(//label[@for='paymentmethod_1'])[2]");
    private final By orderTotal = By.xpath("//span[@class='product-price order-total']/Strong");

    private final By orderSuccessfulMsg = By.xpath("//div/Strong");

    public CheckOutPageObject(WebDriver driver){
        this.driver = driver;
    }

    public double getShippingCost(String shippingMeth){
        if(shippingMeth.equalsIgnoreCase("ground"))
            return Double.parseDouble(getTextFromElement(shipMethGround).split(" ")[1].substring(1,6));
        else if(shippingMeth.equalsIgnoreCase("next day air"))
            return Double.parseDouble(getTextFromElement(shipMethNextAir).split(" ")[1].substring(1,6));
        else
            return Double.parseDouble(getTextFromElement(shipMeth2DAir).split(" ")[1].substring(1,6));
    }

    public double getPaymentCost(String paymentMeth){
        if(paymentMeth.equalsIgnoreCase("COD"))
            return Double.parseDouble(getTextFromElement(paymentMethCOD)
                    .substring(getTextFromElement(paymentMethCOD).length()-5
                            ,getTextFromElement(paymentMethCOD).length()-1));
        else if(paymentMeth.equalsIgnoreCase("check"))
            return Double.parseDouble(getTextFromElement(paymentMethCOD)
                    .substring(getTextFromElement(paymentMethCheck).length()-5
                    ,getTextFromElement(paymentMethCheck).length()-1));
        else
            return 0;
    }

    public void clickBillContinueBtn() throws InterruptedException {
        clickElement(billContinueBtn);
        shortWait(2);
    }

    public void clickShipContinueBtn() throws InterruptedException {
        clickElement(shipContinueBtn);
        shortWait(2);
    }

    public void clickShipMethContinueBtn() throws InterruptedException {
        clickElement(shipMethContinueBtn);
        shortWait(2);
    }

    public void clickPayMethContinueBtn() throws InterruptedException {
        clickElement(payMethContinueBtn);
        shortWait(2);
    }

    public void clickPayInfoContinueBtn() throws InterruptedException {
        clickElement(payInfoContinueBtn);
        shortWait(2);
    }

    public void clickConfirmOrderBtn() throws InterruptedException {
        clickElement(confirmOrder);
    }

    public double getOrderTotal(){
        return Double.parseDouble(getTextFromElement(orderTotal));
    }

    public String getSuccessfulMsg() throws InterruptedException {
        shortWait(2);
        return getTextFromElement(orderSuccessfulMsg);
    }
}
