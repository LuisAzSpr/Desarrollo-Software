package org.example.DBConn;

import org.example.Article;

public interface DBConnection {
    public void saveArticleToDatabase(Article article);
    public void deleteArticleToDatabase(Article article);
}
