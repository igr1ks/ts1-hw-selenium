package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArticlePage {
    WebDriver driver;

    @FindBy(css="#main > section > div > div > div.app-article-masthead__info > h1")
    public WebElement articleTitle;

    @FindBy(css = "#main .app-article-masthead__info time")
    public WebElement articleTimePublishing;

//    @FindBy(css = "#main > section > div > div > div.app-article-masthead__info > ul.c-article-identifiers.c-article-identifiers--cite-list > li:nth-child(1) > span:nth-child(2)")
    public WebElement articleDOI;

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getArticleTitleText() {
        return articleTitle.getText();
    }
    public String getArticleTimePublishingText() {
        return articleTimePublishing.getText();
    }
    public String getArticleDOIText() {
        String url = driver.getCurrentUrl();
        final String marker = "/article/";
        assert url != null;
        int idx = url.indexOf(marker);
        if (idx != -1) {
            return url.substring(idx + marker.length());
        }
        return null;
    }

}
