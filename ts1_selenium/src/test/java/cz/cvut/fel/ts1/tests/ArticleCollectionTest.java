package cz.cvut.fel.ts1.tests;

import cz.cvut.fel.ts1.pages.AdvancedSearchPage;
import cz.cvut.fel.ts1.pages.AdvancedSearchResultPage;
import cz.cvut.fel.ts1.pages.ArticlePage;
import cz.cvut.fel.ts1.pages.HomePage;
import cz.cvut.fel.ts1.utils.ArticleData;
import cz.cvut.fel.ts1.utils.JsonHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ArticleCollectionTest extends BaseTest{

    private String extractDoiFromUrl(String url) {
        if (url.contains("/article/")) {
            String[] parts = url.split("/article/");
            if (parts.length > 1) {
                return parts[1];
            }
        }
        return null;
    }

    @Test
    void collectAndSaveFirstFourArticles() throws Exception {


        driver.get("https://link.springer.com/advanced-search");
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);

        advancedSearchPage.enterKeywords("(\"Page\" AND \"Object\" AND \"Model\" AND (\"Selenium\" OR  \"Testing\")");
        advancedSearchPage.selectLast12Months();
        advancedSearchPage.clickSearch();



        AdvancedSearchResultPage advancedSearchResultPage = new AdvancedSearchResultPage(driver);
        advancedSearchResultPage.updateFilters();

        List<String> urls = advancedSearchResultPage.getFirstArticleUrls(4);
        System.out.println("Nalezeno URL článků: " + urls.size());

        List<ArticleData> articles = new ArrayList<>();
        for (String url : urls) {
            System.out.println("Zpracovávám URL: " + url);
            driver.get(url);

            ArticlePage articlePage = new ArticlePage(driver);

            String doi = extractDoiFromUrl(url);
            if (doi == null && articlePage.articleDOI != null) {
                doi = articlePage.articleDOI.getText();
            }

            articles.add(new ArticleData(
                    articlePage.articleTitle.getText(),
                    doi,
                    articlePage.articleTimePublishing.getText()
            ));
        }
        JsonHelper.writeArticlesToJson(articles, "src/main/resources/articles.json");
    }
}
