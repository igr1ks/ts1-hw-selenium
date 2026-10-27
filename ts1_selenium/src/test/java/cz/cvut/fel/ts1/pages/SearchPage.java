package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(css = "#search-springerlink")
    private WebElement searchInput;

    @FindBy(css = "#search-submit")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div/div/div[2]/div[2]/ol/li[1]/div[1]/h3/a")
    private WebElement searchTitle;



    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    public void enterSearchText(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(searchText);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }





    public String getSearchTitleLink(){
        System.out.println(searchTitle.getText());
        return searchTitle.getDomProperty("href");
    }
}
