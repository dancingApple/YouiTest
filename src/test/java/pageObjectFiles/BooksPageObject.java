package pageObjectFiles;

import org.openqa.selenium.By;

public class Books {
    private final By computerNInternet= By.xpath("/a[text()='Computing and Internet']/ancestor:: div[@class='details'] //input");
    private final By healthBoo = By.xpath("//a[text()='Health Book']/ancestor:: div[@class='details'] //input");
}
