package org.example.DBConn;

import org.example.Article;
import org.example.DBConn.DBConnection;

public class DBConnection2 implements DBConnection {
    public void saveArticleToDatabase(Article article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article+" conexion 2");
    }
    public void deleteArticleToDatabase(Article article){
        // simulacion de eliminar en la base de datos
        System.out.println("Eliminado de la base de datos a "+article+"conexion 2");
    }
}

