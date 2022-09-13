package stepDefinitionFiles;

import dataFiles.ProjectDataClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import keywordFiles.OrderKeywords;

public class OrderSteps {

    private final OrderKeywords orderKeywords;

    private final ProjectDataClass dataProvider;

    public OrderSteps(ProjectDataClass dataProvider) {
        this.dataProvider = dataProvider;
        this.orderKeywords = new OrderKeywords(dataProvider);
    }
    @When("^user go to the (.+) page and add (.+) into the Cart$")
    public void user_go_to_the_page_and_add_into_the_cart(String category, String items) throws Throwable {
        orderKeywords.orderItems(category,items);
    }

    @Then("^user fill in the shipping address as (.+),(.+) with post code (.+) to complete the order successfully$")
    public void user_fill_in_the_shipping_address_as_with_post_code_to_complete_the_order_successfully(String state, String country, String postcode) throws Throwable {
        orderKeywords.finishBillInfoNCheckOut();
    }

    @And("^user go to the cart to check if the orders are correct$")
    public void user_go_to_the_cart_to_check_if_the_orders_are_correct() throws Throwable {
        orderKeywords.checkCart();
        orderKeywords.checkOut();
    }
}
