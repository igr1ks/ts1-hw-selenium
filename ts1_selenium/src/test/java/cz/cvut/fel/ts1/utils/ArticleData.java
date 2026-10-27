package cz.cvut.fel.ts1.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleData {
    private final String title;
    private final String doi;
    private final String publishDate;

    @JsonCreator
    public ArticleData(
            @JsonProperty("title") String title,
            @JsonProperty("doi") String doi,
            @JsonProperty("publishDate") String publishDate
    ) {
        this.title = title;
        this.doi = doi;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDoi() {
        return doi;
    }

    public String getPublishDate() {
        return publishDate;
    }

    @Override
    public String toString() {
        return title;
    }
}
