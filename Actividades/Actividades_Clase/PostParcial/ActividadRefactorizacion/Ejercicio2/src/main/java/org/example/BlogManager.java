package org.example;

import org.example.DBConn.DBConnection;
import org.example.DBConn.DBConnection1;

import java.util.ArrayList;
import java.util.List;

/* Con respecto al codigo base se han cambiado las lineas 20, 30, hemos agregado los metodo removeArticle y deleteArticle
para dar a la clase mayor tareas y justificar asi que rompre el principio SOLID (SRP) y a causa de eso hemos eliminado los metodos
que estan relacionados con el maenejo de la base de datos como saveArticleToDatabase y deleteArticleDatabase, esto lo hemos
hecho como solucion al SRP.
 */

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

/*
Código inicial
public class EmployeeManager {
 public void addEmployee(String name, String department) {
 // Añade un empleado al departamento
 System.out.println("Empleado añadido");
 }
 public void removeEmployee(String name) {
 // Elimina un empleado
 System.out.println("Empleado eliminado");
 }
 public void changeDepartment(String employeeName, String newDepartment) {
 // Cambia un empleado de departamento
 System.out.println("Departamento cambiado");
 }
 public void printDepartmentReport(String department) {
 // Imprime un informe del departamento
 System.out.println("Informe del departamento " + department);
 }
 public void printAllDepartments() {
 // Imprime todos los departamentos
 System.out.println("Lista de todos los departamentos");
 }
}

 */

