package org.example;

import org.example.DBConn.DBConnection;
import org.example.DBConn.DBConnection1;

import java.util.ArrayList;
import java.util.List;

public class BlogManager {
    private List<Article> articles = new ArrayList<>();
    private DBConnection dbConnection;

    public BlogManager(){
        dbConnection = new DBConnection1();
    }
    public void addArticle(Article article) {
        if (article != null) {
            articles.add(article);
            System.out.println("Artículo añadido: " + article);
            dbConnection.saveArticleToDatabase(article);
        }
    }
    public void deleteArticle(Article article){
        if(article!=null){
            articles.remove(article);
            System.out.println("Artículo eliminado: " + article);
            dbConnection.deleteArticleToDatabase(article);
        }
    }
}

