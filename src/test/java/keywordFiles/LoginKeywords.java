package keywordFiles;

import dataFiles.ProjectDataClass;
import pageObjectFiles.CommonMenuPageObject;
import pageObjectFiles.LoginPageObject;

import java.io.InputStream;
import java.util.Properties;

public class LoginKeywords {

    private final ProjectDataClass dataProvider;
    private final LoginPageObject loginPageObject;
    private final CommonMenuPageObject commonMenuPageObject;

    public LoginKeywords(ProjectDataClass dataProvider){
        this.dataProvider = dataProvider;
        this.loginPageObject = new LoginPageObject(dataProvider.getDriver());
        this.commonMenuPageObject = new CommonMenuPageObject(dataProvider.getDriver());
    }

    public void loginToApplication(String loginType) throws Exception {
        String resourceName="testData/userLoginCredentials.properties";
        String feature = dataProvider.getFeatureName(dataProvider.getScenarioName());

        System.out.println("*** Using userLoginData sheet for " + feature + " feature execution***");

        //Load the required data sheet where uid and pw stored
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        InputStream resourceStream = null;
        try {
            System.out.println("Resource path: " + resourceName);
            resourceStream = loader.getResourceAsStream(resourceName);
            props.load(resourceStream);
        } catch (Exception e) {
            System.out.println("Exception opening the properties file: " + e);
        }

        dataProvider.setLoginUser(loginType);
        dataProvider.setLoginEmail(props.getProperty(loginType + "." + "uid"));
        dataProvider.setLoginPw(props.getProperty(loginType + "." + "pw"));

        dataProvider.getScreenshot("openBrowserLandedOnLoginPage");
        commonMenuPageObject.clickLogin();
        loginPageObject.inputEmail(dataProvider.getLoginEmail());
        loginPageObject.inputPwd(dataProvider.getLoginPw());
        dataProvider.getScreenshot("inputLoginDetails");
        loginPageObject.clickLoginBtn();

        dataProvider.getScreenshot("Login_Successful");

        if (null != resourceStream) {
            try {
                resourceStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
