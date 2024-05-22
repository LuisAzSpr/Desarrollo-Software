package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String nameDepartment;
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
