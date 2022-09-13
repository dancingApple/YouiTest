package keywordFiles;

import dataFiles.ProjectDataClass;
import pageObjectFiles.BooksPageObject;
import pageObjectFiles.CartPageObject;
import pageObjectFiles.CheckOutPageObject;
import pageObjectFiles.CommonMenuPageObject;
import util.CommonMeth;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderKeywords {
    private final ProjectDataClass dataProvider;
    private final BooksPageObject booksPageObject;
    private final CommonMenuPageObject commonMenuPageObject;
    private final CartPageObject cartPageObject;
    private final CheckOutPageObject checkOutPageObject;
    private ArrayList<String> expectedOrderItems = new ArrayList<>();

    private double actualSubTotal = 0;


    public OrderKeywords(ProjectDataClass dataProvider){
        this.dataProvider = dataProvider;
        this.booksPageObject = new BooksPageObject(dataProvider.getDriver());
        this.commonMenuPageObject = new CommonMenuPageObject(dataProvider.getDriver());
        this. cartPageObject = new CartPageObject(dataProvider.getDriver());
        this.checkOutPageObject = new CheckOutPageObject(dataProvider.getDriver());
    }

    public void orderItems(String category,String items) throws Exception {
        if(category.equalsIgnoreCase("books"))
            commonMenuPageObject.selectTopMenuBooks();
        expectedOrderItems.add(items.split(",")[0]);
        expectedOrderItems.add(items.split(",")[1]);
        booksPageObject.addToCart(expectedOrderItems.get(0));
        booksPageObject.checkFeedbackMsgDisplayed();
        booksPageObject.addToCart(expectedOrderItems.get(1));
        booksPageObject.checkFeedbackMsgDisplayed();
        dataProvider.getScreenshot("Add Item to cart");
    }

    public void checkCart() throws Exception {

        commonMenuPageObject.goToCart();
        assertEquals("The ordered products are not correct",expectedOrderItems,cartPageObject.getItemsInCart());

        for (int i=0;i<cartPageObject.getItemUnitPriceInCart().size();i++) {
            assertEquals("Item total is not correct",
                    Double.parseDouble(cartPageObject.getItemUnitPriceInCart().get(i))
                            * Integer.parseInt(cartPageObject.getItemQtysInCart().get(i))
                    ,Double.parseDouble(cartPageObject.getItemTotalInCart().get(i)),0.001);
            actualSubTotal = Double.parseDouble(cartPageObject.getItemTotalInCart().get(i))+actualSubTotal;
        }

        assertEquals("The subTotal value is calculated wrong",
                Double.parseDouble(cartPageObject.subTotal()),actualSubTotal,0.001);
        dataProvider.getScreenshot("Check Cart calculation");
    }

    public void checkOut() throws InterruptedException {
        cartPageObject.checkTncBox();
        cartPageObject.clickCheckOutBtn();
    }

    public void finishBillInfoNCheckOut() throws Exception {
        double shippingCost = 0;
        double paymentCost = 0;

        dataProvider.getScreenshot("Bill address info");
        checkOutPageObject.clickBillContinueBtn();

        dataProvider.getScreenshot("shipping address info");
        checkOutPageObject.clickShipContinueBtn();
        shippingCost = checkOutPageObject.getShippingCost("ground");

        dataProvider.getScreenshot("shipping method info");
        checkOutPageObject.clickShipMethContinueBtn();
        paymentCost = checkOutPageObject.getPaymentCost("COD");

        dataProvider.getScreenshot("Payment method info");
        checkOutPageObject.clickPayMethContinueBtn();

        dataProvider.getScreenshot("Payment info page");
        checkOutPageObject.clickPayInfoContinueBtn();

        dataProvider.getScreenshot("Confirm order page");
        assertEquals("The order total cost is not correct",actualSubTotal+shippingCost+paymentCost,checkOutPageObject.getOrderTotal(),0.001);
        checkOutPageObject.clickConfirmOrderBtn();

        dataProvider.getScreenshot("Order successful page");
       assertEquals("The order successful msg is not correctly displayed"
               ,"Your order has been successfully processed!",checkOutPageObject.getSuccessfulMsg());
    }
}
