package org.example;

import java.util.ArrayList;
import java.util.List;

/*
    La clase Department necesita tener un nombre de departamento y necesita tambien concer a sus empleados,
    es decir, tener una lista de Employe con todos los empleados que pertenecen a este departamento, ademas
    debe ser responsable de administrar los empleados, es decir, agregar un empleado, remover un empleado o
    verificar si un empleado se encuentra o no.

    Cabe mencionar que si usamos una metrica como LCOM, este puede darnos un valor de 2, haciendo referencia
    a que la clase tiene una baja cohesion, es decir, de que existen un grupo de metodos que usan un atributo
    (nameDepartment) y otro grupo de metodos que utiliza otro atributo (employeList), sin embargo cabe resaltar
    que nameDepartment es un atributo que identifica a la clase mas no se hacen operaciones con este, por lo que
    podemos pasar por alto esto.

 */

public class Department {
    private String nameDepartment; // este atributo es un identificador
    private List<Employe> employeList = new ArrayList<>();
    public Department(String nameDepartment){
        this.nameDepartment = nameDepartment;
    }
    public void addEmploye(Employe employe){
        employeList.add(employe);
    }
    public void removeEmploye(Employe employe){
        employeList.remove(employe);
    }
    public boolean employeExist(Employe employe){
        return employeList.contains(employe);
    }
    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
    public String getNameDepartment() {
        return nameDepartment;
    }
    public List<Employe> getEmployeList() {
        return employeList;
    }
}
