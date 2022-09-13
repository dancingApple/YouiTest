package keywordFiles;

import dataFiles.ProjectDataClass;
import pageObjectFiles.RegisterPageObject;

import static org.junit.Assert.assertEquals;

public class RegisterKeyword {
    private final ProjectDataClass dataProvider;
    private final RegisterPageObject registerPageObject;

    public RegisterKeyword(ProjectDataClass dataProvider){
        this.dataProvider = dataProvider;
        this.registerPageObject = new RegisterPageObject(dataProvider.getDriver());
    }

    public void finishRegister() throws InterruptedException {
        registerPageObject.setGender("Male");
        registerPageObject.setFirstName("Siren");
        registerPageObject.setLastName("Nomi");
        registerPageObject.setEmail("super-sonic-master@hotmail.com");
        registerPageObject.setPwd("Tester99");
        registerPageObject.confirmPwd("Tester99");
        registerPageObject.clickRegisterBtn();
        assertEquals("The register result msg is not displayed correctly",
                "Your registration completed",registerPageObject.getResultMsg());
        registerPageObject.continueToShopping();
        assertEquals("The register result msg is not displayed correctly",
                "Your registration completed",registerPageObject.getResultMsg());
    }
}
