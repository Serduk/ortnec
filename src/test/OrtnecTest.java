package test;

import core.browser.ChromeUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.GooglePage;

/**
 * Test
 * run test:
 * install mvn
 * in rerminal open project folder
 * insert next: mvn clean -Dtest.xml=OrtnecTest.xml test
 * <p>
 * Created by serdyuk on 2/18/17.
 */
public class OrtnecTest {
    private WebDriver driver;

    private GooglePage gPage;

//    User before test. Init all
    @BeforeTest
    public void browserInit() {
//        Init Browser (Chrome WEB)
        ChromeUtils chromeBrowser = new ChromeUtils("USA", 10);
        this.driver = chromeBrowser.getWebBrowser();

//        init page google
        gPage = new GooglePage(driver);
    }

    @Test
    public void checkGoogleSearchResult() {
//        Get start page google
        gPage.get("https://www.google.com.ua/");
//        user method from gPage (sendKeys ortnec, and click on Search BTN)
        gPage.search("ortnec");

//        Display search result size
        System.out.println(gPage.searchResults.size());

//        Assert => Check is 1st link in list (ortnec or no) => 4.a. Condition in test
        Assert.assertTrue(gPage.getLinksInSearchResult(0).toLowerCase().contains("http://ortnec.com"), "Wrong First Ling");
    }

    @Test(dependsOnMethods = "checkGoogleSearchResult")
    public void checkAllResultsContainsName() {
//        get all URLs in search results
        for (int i = 0; i < gPage.searchResults.size(); i++) {
            System.out.println(gPage.getLinksInSearchResult(i));
//            click on Search Results links
            gPage.clickOnHeaderInResultPosition(i);
//            on opened page check "ortnec" text is present?
            Assert.assertTrue(gPage.body.getText().toLowerCase().contains("ortnec"), "Page Does't have text 'Ortnec'");

//            go back to search results
            driver.navigate().back();

//            JS execute. Wait for page load
            gPage.waitForPageLoad();
        }
    }

    @AfterTest
    public void cleanUp() {
        driver.quit();
    }
}
