package org.example;

public class Article {
    private String name;
    private String tipo;
    public Article(String name,String tipo){
        this.name = name;
        this.tipo = tipo;
    }
    public String getName() {
        return name;
    }
    public String getTipo() {
        return tipo;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
