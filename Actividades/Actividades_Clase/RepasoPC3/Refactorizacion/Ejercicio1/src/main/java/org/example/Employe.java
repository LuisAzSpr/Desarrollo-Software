package org.example;


/*
    La clase Employe tiene un atributo nombre que identifica al empleado
 */

public class Employe {
    private String name;
    public Employe(String name){
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
