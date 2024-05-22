package org.example;

import java.util.List;

public class ArticleReports {
    public void printAllArticles(List<Article> articles) {
        for (Article article : articles) {
            System.out.println("Artículo: " + article);
        }
    }
}
