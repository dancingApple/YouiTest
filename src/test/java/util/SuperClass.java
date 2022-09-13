package util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Function;

public abstract class SuperClass {
    private final long shortWait = 1, longWait = 30;
    public WebDriver driver = null;

    /**
     * Method to close the browser after the test execution
     */
    /**
     * Fluent wait for the element
     */
    public void waitForElement(By element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(longWait))
                .pollingEvery(Duration.ofSeconds(shortWait))
                .ignoring(Exception.class).ignoring(NoSuchElementException.class).ignoring(ElementNotInteractableException.class);

        Function<WebDriver, WebElement> function =
                webDriver -> driver.findElement(element);
        wait.until(function);
    }

    public void waitForConfirmElement(By element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(600))
                .pollingEvery(Duration.ofSeconds(shortWait))
                .ignoring(Exception.class).ignoring(NoSuchElementException.class).ignoring(ElementNotInteractableException.class);

        Function<WebDriver, WebElement> function =
                webDriver -> driver.findElement(element);
        wait.until(function);
    }

    /**
     * Method to wait for an element for short fixed duration
     */
    public void shortWait() throws InterruptedException {
        Thread.sleep(shortWait);
    }

    /**
     * Method to wait for an element for short fixed duration
     */
    public void shortWait(float timeToWait) throws InterruptedException {
        Thread.sleep((long) timeToWait * 1000);
    }

    /**
     * Method to explicitly wait for an element
     */
    public void explicitWaitforElement(By element) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longWait, 1000);
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    /**
     * Method to explicitly wait for element to invisible
     */
    public void waitForElementNotVisible(By element) throws InterruptedException {
        WebDriverWait explicitWait = new WebDriverWait(driver, longWait * 2, 1000);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
    public void waitForElementVisible(By element) throws InterruptedException {
        WebDriverWait explicitWait = new WebDriverWait(driver, longWait * 2, 1000);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /**
     * Method to wait for Element to be click-able
     */
    public void waitforElementClickable(By element) {
        WebDriverWait wait = new WebDriverWait(driver, longWait, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Method to input values in Text fields
     */
    public void inputValueInElement(By element, String value) throws InterruptedException {
        driver.findElement(element).sendKeys(value);
        shortWait();
    }

    /**
     * Input arrow keys
     */
    public void inputKeys(By element, String value) throws InterruptedException {
        shortWait(2);
        switch (value.toUpperCase()) {
            case "ARROW_DOWN":
                driver.findElement(element).sendKeys(Keys.ARROW_DOWN);
                shortWait(2);
                break;
            case "TAB":
                driver.findElement(element).sendKeys(Keys.TAB);
                shortWait(2);
                break;
            case "ENTER":
                driver.findElement(element).sendKeys(Keys.ENTER);
                shortWait(2);
                break;
            default:
                break;
        }
    }

    /**
     * Method to clear values in Text fields
     */
    public void clearValueInElement(By element) throws InterruptedException {
        driver.findElement(element).clear();
        shortWait();
    }

    public void delValInElement(By element, String os) throws InterruptedException {
        if(os.toLowerCase().contains("mac"))
            driver.findElement(element).sendKeys(Keys.chord(Keys.COMMAND,"a", Keys.DELETE));
        else
            driver.findElement(element).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        shortWait();
    }

    /**
     * Method to select visible text from drop down
     */
    public void selectVisibleTextInList(By element, String value) throws InterruptedException {
        Select selectFromDropDown = new Select(driver.findElement(element));
        selectFromDropDown.selectByVisibleText(value);
        shortWait();
    }

    /**
     * Method to select values from drop down
     */
    public void selectByValueTextInList(By element, String value) throws InterruptedException {
        Select selectFromDropDown = new Select(driver.findElement(element));
        selectFromDropDown.selectByValue(value);
        shortWait();
    }

    public void selectByVisibleTextInList(By element, String value) throws InterruptedException {
        Select selectFromDropDown = new Select(driver.findElement(element));
        selectFromDropDown.selectByVisibleText(value);
        shortWait();
    }

    /**
     * Method to get options from drop down
     */
    public List<WebElement> getOptionsFrmDropDown(By element) {

        Select selectFromDropDown = new Select(driver.findElement(element));
        return selectFromDropDown.getOptions();
    }

    /**
     * Method to select index from drop down
     */
    public void selectByIndexInList(By element, int value) throws InterruptedException {
        Select selectFromDropDown = new Select(driver.findElement(element));
        selectFromDropDown.selectByIndex(value);
        shortWait();
    }

    public String getSelectedOptionTxt(By element) {
        Select selectFromDropDown = new Select(driver.findElement(element));
        return selectFromDropDown.getFirstSelectedOption().getText();
    }

    /**
     * Method to click on a button
     */
    public void clickElement(By element) throws InterruptedException {
        //shortWait 2s Added for temp fix, hopefully could remove in future
        shortWait(2);
        driver.findElement(element).click();
        shortWait();
    }

    public void clickElementWithOverlay(By element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",driver.findElement(element));
    }

    /**
     * Method to get text from an element
     */
    public String getTextFromElement(By element) {
        return driver.findElement(element).getText().trim();
    }

    public String getUrlFromElement(By element) {
        return driver.findElement(element).getAttribute("href").trim();
    }
    /**
     * Method to get value from an element
     */
    public String getValueFromElement(By element) {
        return driver.findElement(element).getAttribute("value").trim();
    }

    /**
     * Method to get text from an element
     */
    public String getFirstSelectedValue(By element) {
        return new Select(driver.findElement(element)).getFirstSelectedOption().getText().trim();
    }

    /**
     * Method to get data from table
     */
    public List<String> getTableDetails(By rowElement) {
        List<String> tableData = new ArrayList<>();
        int size = driver.findElements(rowElement).size();

        for (int i = 1; i <= size; i++) {
            String value = driver.findElement(By.xpath(rowElement + "[" + i + "]")).getText();
            tableData.add(value);
        }
        return tableData;
    }

    /**
     * Method to get row index from the table
     */
    public String getRowInTable(By rowElement, String Option) {
        String index = "";
        int size = driver.findElements(rowElement).size();

        for (int i = 1; i <= size; i++) {
            String value = driver.findElement(By.xpath(rowElement + "[" + i + "]")).getText();

            if (value.toLowerCase().contains(Option.toLowerCase())) {
                index = String.valueOf(i);
                break;
            }
        }
        return index;
    }

    /**
     * Method to refresh the web page
     */
    public void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
        shortWait();
    }

    /**
     * Method to get the current window handle
     */
    public ArrayList<String> getCurrentWindowHandle() {
        return new ArrayList<>(driver.getWindowHandles());
    }

    /**
     * Method to switch to current window
     */
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    /**
     * Switch to iFrame
     */
    public void switchToIframe(String iframeHandle){
        driver.switchTo().frame(iframeHandle);
    }
    public void switchToIframe(int iframeHandle){
        driver.switchTo().frame(iframeHandle);
    }

    public void switchOutIframe(){
        driver.switchTo().defaultContent();
    }

    /**
     * Method to verify if the given element is displayed
     */
    public boolean isElementDisplayed(By locator) throws InterruptedException {
        shortWait();
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to verify if the given element is selected
     */
    public boolean isElementSelected(By locator) throws InterruptedException {
        shortWait();
        try {
            return driver.findElement(locator).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to verify if the given element is selected
     */
    public boolean isElementEnabled(By locator) throws InterruptedException {
        shortWait();
        try {
            return driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to scroll web Element
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Click using Javascript
     */
    public void clickUsingJavascript(By Option) {
        WebElement element = driver.findElement(Option);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[1].click();", element);
    }

    public void moveToElement(By element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element));
        actions.perform();
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollDown(By Element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(Element));
    }

    public void selectRandomPage() throws InterruptedException {
        // get number of pages
        shortWait(2);
        List<WebElement> pages = getOptionsFrmDropDown(By.id("cmbnumpages"));
        int options = pages.size() - 1;
        System.out.println(options);

        // if multiple pages, navigate to random page
        if (options > 1) {
            try {
                scrollDown(By.id("cmbnumpages"));
                selectByIndexInList(By.id("cmbnumpages"), new Random().nextInt(options - 1) + 1);
                shortWait(2);
            } catch (StaleElementReferenceException e) {
                shortWait(2);
                scrollDown(By.id("cmbnumpages"));
                shortWait();
                selectByIndexInList(By.id("cmbnumpages"), 0);
                shortWait(2);
            }
        }
    }

    public void scrollMaxRight() {
        int flakeCounter = 0;
        int flakeTry = 0;
        if (!driver.findElements(By.className("scroll-arrow-right-bar")).isEmpty()) {
            do {
                By scrollButton = By.xpath("//button[@class='btn-scroll btn-scroll-to-right']");
                scrollToBottom();
                driver.findElement(scrollButton).click();
                if (flakeCounter > 10) {
                    scrollToBottom();
                    driver.findElement(By.xpath("//button[@class='btn-scroll btn-scroll-to-left']")).click();
                    flakeCounter = 0;
                    flakeTry++;
                }
                flakeCounter++;
            }
            while (driver.findElement(By.xpath("//button[@class='btn-scroll btn-scroll-to-right']")).isDisplayed()
                    && flakeTry < 5);
        }
    }

    public void scrollMaxLeft() {
        int flakeCounter = 0;
        if (!driver.findElements(By.className("scroll-arrow-left-bar")).isEmpty()) {
            do {
                By scrollButton = By.xpath("//button[@class='btn-scroll btn-scroll-to-left']");
                scrollToBottom();
                driver.findElement(scrollButton).click();
                if (flakeCounter > 10) {
                    scrollToBottom();
                    driver.findElement(By.xpath("//button[@class='btn-scroll btn-scroll-to-right']")).click();
                    flakeCounter = 0;
                }
                flakeCounter++;
            }
            while (driver.findElement(By.xpath("//button[@class='btn-scroll btn-scroll-to-left']")).isDisplayed());
        }
    }

    public void slowInput(String value, By element, float speed) throws InterruptedException {
        String[] portfolioString = value.split("");
        for (String s : portfolioString) {
            inputValueInElement(element, s);
            shortWait(speed);
        }
    }

    public boolean checkboxesEnabled(Boolean enabled) {
        for (WebElement element: driver.findElements(By.xpath("//input[@type='checkbox']"))) {
            if (element.isEnabled() != enabled) {
                return false;
            }
        }
        return true;
    }

    public boolean inputsEnabled(Boolean enabled) {
        for (WebElement element: driver.findElements(By.xpath("//input[@type='text']"))) {
            if (element.isEnabled() != enabled &&
                    !element.getAttribute("id").equals("MainAccountSearch") &&
                    !element.getAttribute("id").equals("externalCustomerId")) {
                return false;
            }
        }
        return true;
    }

    public boolean dropdownsEnabled(Boolean enabled) {
        for (WebElement element: driver.findElements(By.xpath("//div[@class='controls-select']/select"))) {
            if (element.isEnabled() != enabled &&
                    !element.getAttribute("id").equals("advcode") &&
                    !element.getAttribute("id").equals("platformProvider")) {
                return false;
            }
        }
        return true;
    }

    public boolean elementExist(By locator) throws InterruptedException {
        shortWait(2);
        return driver.findElements(locator).size()>0;
    }
}