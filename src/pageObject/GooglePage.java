package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Factory:
 * Describe Elements on google page
 *
 * Created by serdyuk on 2/18/17.
 */
public class GooglePage extends APage {
    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@name = 'q']")
    private WebElement searchForm;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement searchBtn;

    @FindBy(xpath = "//*[@class = 'g']")
    public List<WebElement> searchResults;

//    Method take Searchable String => insert in textField, and then click on Search btn
    public void search(String searchableText) {
        waitForElementPresent(searchForm, 15);
        searchForm.sendKeys(searchableText);
        waitForElementPresent(searchBtn, 15);
        searchBtn.click();
    }

    public WebElement checkDataInResultsPosition(int position) {
        return searchResults.get(position);
    }

    public void clickOnHeaderInResultPosition(int position) {
        click(searchResults.get(position).findElement(By.cssSelector(".r>a")));
        waitForPageLoad();
    }

    public String getLinksInSearchResult(int position) {
        return searchResults.get(position).findElement(By.cssSelector(".r>a")).getAttribute("href");
    }
}
