package keywordFiles;

import dataFiles.ProjectDataClass;
import pageObjectFiles.CommonMenuPageObject;
import pageObjectFiles.RegisterPageObject;
import util.CommonMeth;

import static org.junit.Assert.assertEquals;

public class RegisterKeyword extends CommonMeth {
    private final ProjectDataClass dataProvider;
    private final RegisterPageObject registerPageObject;

    private final CommonMenuPageObject commonMenuPageObject;

    public RegisterKeyword(ProjectDataClass dataProvider){
        this.dataProvider = dataProvider;
        this.registerPageObject = new RegisterPageObject(dataProvider.getDriver());
        this.commonMenuPageObject = new CommonMenuPageObject(dataProvider.getDriver());
    }

    public void goToRegisterPage() throws Exception {
        commonMenuPageObject.clickRegister();
        assertEquals("Register page title is not properly displayed",
                "Register",commonMenuPageObject.getRegisterTitle());
        dataProvider.getScreenshot("Register Page");
    }

    public void finishRegister() throws Exception {
        registerPageObject.setGender("Male");
        registerPageObject.setFirstName(getRandomName());
        registerPageObject.setLastName(getRandomName());
        registerPageObject.setEmail(getRandomName()+"@gmail.com");
        registerPageObject.setPwd("Tester99");
        registerPageObject.confirmPwd("Tester99");
        dataProvider.getScreenshot("Filled the register form");
        registerPageObject.clickRegisterBtn();
    }

    public void checkRegister() throws Exception {
        assertEquals("The register result msg is not displayed correctly",
                "Your registration completed",registerPageObject.getResultMsg());
        registerPageObject.continueToShopping();
        assertEquals("The register result msg is not displayed correctly",
                "Welcome to our store",registerPageObject.getWelcomeMsg());
        dataProvider.getScreenshot("User logged in");
    }
}
