package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdvancedSearchResultPage {
    WebDriver driver;
    private WebDriverWait wait;


//    @FindBy(css = "#main > div > div > div > div:nth-child(2) > div.app-search__filter-sort > div.app-search__filter-btn > div > button")
//    private WebElement filtersButton;

    @FindBy(css = "#list-content-type-filter > li:nth-child(1) > div > label")
    private WebElement checkBoxArticle;

    @FindBy(css = "#popup-filters > div.app-filter-buttons > button.eds-c-button.eds-c-button--primary")
    private WebElement updateResultsButton;

    ////*[@id="main"]/div/div/div/div[2]/div[3]/ol/li[1]
    @FindBy(xpath = "//*[@id=\"main\"]/div/div/div/div[2]/div[3]/ol")
    private List<WebElement> articleLinks;


    public AdvancedSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void updateFilters() {
//        filtersButton.click();
        checkBoxArticle.click();
        updateResultsButton.click();
    }

    public List<String> getFirstArticleUrls(int count) {
        List<String> urls = new ArrayList<>();
        for (int n = 1; n <= count; n++) {
            String xpath = "//*[@id='main']/div/div/div/div[2]/div[3]/ol/li[" + n + "]//h3/a";
            WebElement link = driver.findElement(By.xpath(xpath));
            urls.add(link.getAttribute("href"));
        }
        return urls;
    }

}
