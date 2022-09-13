package stepDefinitionFiles;

import dataFiles.ProjectDataClass;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import keywordFiles.LoginKeywords;
import keywordFiles.RegisterKeyword;

public class CommonSteps {
    private RegisterKeyword registerKeyword;
    private LoginKeywords loginKeywords;
    private final ProjectDataClass dataProvider;

    public CommonSteps(ProjectDataClass dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Before
    public void BeforeHook(Scenario scenario) {
        dataProvider.setScenarioName(scenario);
        System.out.println("Scenario Name : " + dataProvider.getScenarioName().getName());
    }

    @After
    public void teardown() throws Exception {
        System.out.println("Test finished! Closing the test case");
        dataProvider.getScreenshot("Closing " + dataProvider.getScenarioName().getName());
        dataProvider.tearDown();
    }

    @Given("^The Environment is set as \"([^\"]*)\"$")
    public void the_environment_is_set_as_something(String testEnvironment){
        dataProvider.setTestEnvironment(testEnvironment);
    }

    @And("^The application is launched in \"([^\\\"]*)\"$")
    public void launchApplication(String browserName) {
        dataProvider.launchApplication(browserName);
        this.registerKeyword = new RegisterKeyword(dataProvider);
        this.loginKeywords = new LoginKeywords(dataProvider);
    }

    @Given("user {string} to the demo web shop")
    public void userToTheDemoWebShop(String link) throws Exception {
        if(link.equalsIgnoreCase("try to register"))
            registerKeyword.goToRegisterPage();
        else
            loginKeywords.loginToApplication("Siren");
    }
}