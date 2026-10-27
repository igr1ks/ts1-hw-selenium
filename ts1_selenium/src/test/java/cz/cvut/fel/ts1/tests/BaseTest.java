package cz.cvut.fel.ts1.tests;

import cz.cvut.fel.ts1.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.manage().window().maximize();
        driver.get("https://link.springer.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickCookiesButton();

    }

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
