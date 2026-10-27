package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    private WebDriver driver;

    // brazhiho@cvut.cz
    @FindBy(id = "login-email")
    private WebElement loginEmail;

    @FindBy(id = "email-submit")
    private WebElement loginEmailSubmit;

    // 123123123123 (4x123)
    @FindBy(id = "login-password")
    private WebElement loginPassword;

    @FindBy(id = "password-submit")
    private WebElement loginPasswordSubmit;

    @FindBy(xpath = "//*[normalize-space(.) = 'Accept all cookies']")
    private WebElement cookiesButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        loginEmail.sendKeys(email);
    }
    public void enterPassword(String password) {
        loginPassword.sendKeys(password);
    }
    public void submitEmail() {
        loginEmailSubmit.click();
    }
    public void submitPassword() {
        loginPasswordSubmit.click();
    }


}
