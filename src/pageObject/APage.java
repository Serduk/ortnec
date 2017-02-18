package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by serdyuk on 2/18/17.
 */
public abstract class APage {

    /**
     * @param driver WebDriver
     */
    public WebDriver driver;

    public APage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "html")
    public static WebElement html;

    @FindBy(tagName = "head")
    public static WebElement head;

    @FindBy(tagName = "body")
    public static WebElement body;


    /**
     * Override webDriver.get()
     *
     * @param url
     */
    public void get(String url) {
        this.driver.get(url);
        waitForPageLoad();
    }

    public void open(String site) {
        driver.get(site);
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public boolean waitForElementPresent(WebElement element, int timeInSeconds) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Advanced WebDriver webElement.click() action,
     * is verify, is element is present, and if true, click on it. Or throw exception.
     *
     * @param element WebElement what need to be clicked
     * @throws WebDriverException
     */
    public void click(WebElement element) {
        if (isElementPresent(element)) {
            element.click();
        } else {
            throw new WebDriverException(element.getTagName() + "\t" + element.getAttribute("class") + "is not clickable");
        }
    }

    /**
     * Method Check if is element present on page
     *
     * @param element WebElement
     * @return true or false
     * NOT TROW ANY EXCEPTION! BE CAREFUL. HARD TO DEBUG WHIT THIS SH*T.
     */
    public boolean isElementPresent(WebElement element) {
        boolean isPresent = false;
        try {
            if (element.isDisplayed()) {
                isPresent = true;
            }
        } catch (NoSuchElementException e) {
            isPresent = false;
        }
        return isPresent;
    }

}
