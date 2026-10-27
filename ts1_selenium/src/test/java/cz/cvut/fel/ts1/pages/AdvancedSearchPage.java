package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdvancedSearchPage {
    WebDriver driver;
    private WebDriverWait wait;

    // ("Page"  OR "Object" OR  "Model") AND ("Sellenium" OR  "Testing")
//    @FindBy(css = "input[id='query']")
    @FindBy(id="query")
    private WebElement keywordsInput;

    @FindBy(xpath = "//*[normalize-space(.) = 'Accept all cookies']")
    private WebElement cookiesButton;

    @FindBy(css = "#search-form > fieldset > fieldset:nth-child(2) > div > div:nth-child(3) > div > label")
    private WebElement oneYearDate;

    @FindBy(css = "#search-submit")
    private WebElement searchButton;

    public AdvancedSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterKeywords(String keywords) {
        wait.until(ExpectedConditions.visibilityOf(keywordsInput)).clear();
        keywordsInput.sendKeys(keywords);
    }

    public void selectLast12Months() {
        oneYearDate.click();
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void acceptCookies() {
        cookiesButton.click();
    }

}