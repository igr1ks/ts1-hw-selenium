package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(css = "#identity-account-widget a")
    private WebElement logInLink;

    @FindBy(linkText = "Advanced Search")
    private WebElement advancedSearchLink;

    @FindBy(xpath = "//*[normalize-space(.) = 'Accept all cookies']")
    private WebElement cookiesButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginLink() {
        logInLink.click();
    }

    public void clickAdvancedSearch() {
        advancedSearchLink.click();
    }
    public void clickCookiesButton() {
        if (cookiesButton.isDisplayed()){
            cookiesButton.click();
        }
    }






}
