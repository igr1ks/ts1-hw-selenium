package cz.cvut.fel.ts1.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.ts1.pages.HomePage;
import cz.cvut.fel.ts1.pages.LoginPage;
import cz.cvut.fel.ts1.utils.ArticleData;
import cz.cvut.fel.ts1.pages.SearchPage;
import cz.cvut.fel.ts1.pages.ArticlePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArticleVerificationTest{
    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.manage().window().maximize();

        driver.get("https://link.springer.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickCookiesButton();

        driver.get("https://link.springer.com/signup-login");
        LoginPage login = new LoginPage(driver);
        login.enterEmail("brazhiho@cvut.cz");
        login.submitEmail();
        login.enterPassword("123123123123");
        login.submitPassword();
    }
    @AfterAll
    static void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "{index} → {0}")
    @MethodSource("articleProvider")
    void verifySavedArticle(ArticleData expected) {
        String expectedTitle        = expected.getTitle();
        String expectedDoi          = expected.getDoi();
        String expectedPublishDate  = expected.getPublishDate();

        driver.get("https://link.springer.com/search");
        SearchPage search = new SearchPage(driver);
        search.enterSearchText(expectedTitle);
        search.clickSearchButton();

        driver.get(search.getSearchTitleLink());
        ArticlePage article = new ArticlePage(driver);
        assertEquals(expectedDoi, article.getArticleDOIText());
        assertEquals(expectedPublishDate, article.getArticleTimePublishingText());
    }

    static Stream<Arguments> articleProvider() throws IOException {
        Path path = Paths.get("src", "main", "resources", "articles.json");
        ObjectMapper mapper = new ObjectMapper();
        List<ArticleData> list = mapper.readValue(
                path.toFile(),
                new TypeReference<List<ArticleData>>() {}
        );
        return list.stream().map(Arguments::of);
    }


}
