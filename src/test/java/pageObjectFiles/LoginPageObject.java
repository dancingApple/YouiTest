package pageObjectFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.SuperClass;

public class LoginPageObject extends SuperClass {

    private final By emailField = By.id("Email");
    private final By pwdField = By.id("Password");
    private final By loginBtn = By.xpath("//input[@value='Log in']");

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void inputEmail(String email) throws InterruptedException {
        shortWait(2);
        inputValueInElement(emailField,email);
    }

    public void inputPwd(String pwd) throws InterruptedException {
        inputValueInElement(pwdField,pwd);
    }

    public void clickLoginBtn() throws InterruptedException {
        clickElement(loginBtn);
    }
}
