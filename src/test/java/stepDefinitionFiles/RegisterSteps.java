package stepDefinitionFiles;

import dataFiles.ProjectDataClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import keywordFiles.RegisterKeyword;

public class RegisterSteps {

    private final RegisterKeyword registerKeyword;

    private final ProjectDataClass dataProvider;

    public RegisterSteps(ProjectDataClass dataProvider) {
        this.dataProvider = dataProvider;
        this.registerKeyword = new RegisterKeyword(dataProvider);
    }

    @When("^user fill in all the required fields of the register form and click on the register btn$")
    public void user_fill_in_all_the_required_fields_of_the_register_form_and_click_on_the_register_btn() throws Throwable {
        registerKeyword.finishRegister();
    }

    @Then("^user successfully register as a new user$")
    public void user_successfully_register_as_a_new_user() throws Throwable {
        registerKeyword.checkRegister();;
    }
}
