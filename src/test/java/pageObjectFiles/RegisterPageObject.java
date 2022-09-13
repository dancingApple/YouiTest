package pageObjectFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.SuperClass;

public class RegisterPageObject extends SuperClass {
    private final By genderMale = By.id("gender-male");
    private final By genderFemale = By.id("gender-female");
    private final By firstName = By.id("FirstName");
    private final By lastName = By.id("LastName");
    private final By email = By.id("Email");
    private final By password = By.id("Password");
    private final By confirmPassword = By.id("ConfirmPassword");
    private final By registerBtn = By.id("register-button");
    private final By resultMsg = By.xpath("//div[@class='result']");
    private final By continueBtn = By.xpath("//input[@value='Continue']");
    private final By welcomeMsgMainPage = By.xpath("//h2[contains(text(),' Welcome to our store')]");
    public RegisterPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void setGender(String gender) throws InterruptedException {
        shortWait(2);
        if (gender.equalsIgnoreCase("male"))
            clickElement(genderMale);
        else
            clickElement(genderFemale);
    }

    public void setFirstName(String firstname) throws InterruptedException {
        inputValueInElement(firstName,firstname);
    }

    public void setLastName(String lastname) throws InterruptedException {
        inputValueInElement(lastName,lastname);
    }

    public void setEmail(String value) throws InterruptedException {
        inputValueInElement(email,value);
    }

    public void setPwd(String pwd) throws InterruptedException {
        inputValueInElement(password,pwd);
    }

    public void confirmPwd(String pwd) throws InterruptedException {
        inputValueInElement(confirmPassword,pwd);
    }

    public void clickRegisterBtn() throws InterruptedException {
        clickElement(registerBtn);
    }

    public String getResultMsg() throws InterruptedException {
        shortWait(2);
        return getTextFromElement(resultMsg);
    }

    public void continueToShopping() throws InterruptedException {
        clickElement(continueBtn);
        shortWait(2);
    }

    public String getWelcomeMsg(){
        return getTextFromElement(welcomeMsgMainPage);
    }
}
