package stepDefinitionFiles;

import dataFiles.ProjectDataClass;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import keywordFiles.LoginKeyword;

public class CommonSteps {
    private LoginKeyword loginKeyword;
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
    public void the_Environment_is_set_as(String testEnvironment) {
        dataProvider.setTestEnvironment(testEnvironment);
    }

    @Given("^(.+) user login to scalaPay test env web portal$")
    public void user_login_to_scalapay_test_env_web_portal(String user) throws Throwable {
        loginKeyword.loginToApplication(user);
    }

    @And("^The application is launched in \"([^\\\"]*)\"$")
    public void launchApplication(String browserName) {
        dataProvider.launchApplication(browserName);
        this.loginKeyword = new LoginKeyword(dataProvider);
    }
}