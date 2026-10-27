package cz.cvut.fel.ts1.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void writeArticlesToJson(List<ArticleData> articles, String filePath) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), articles);
    }
    public static List<ArticleData> readArticlesFromJson(String filePath) throws IOException {
        return mapper.readValue(
                new File(filePath),
                new TypeReference<List<ArticleData>>() {}
        );
    }
}
