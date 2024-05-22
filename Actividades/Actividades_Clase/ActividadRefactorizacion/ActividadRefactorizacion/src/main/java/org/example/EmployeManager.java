package org.example;

import java.util.ArrayList;
import java.util.List;

public class EmployeManager {
    private List<Department> departmentList = new ArrayList<>();
    public EmployeManager(ArrayList<Department> departments){
        this.departmentList = departments;
    }
    public void addEmployee(Employe employe, Department department) {
        department.addEmploye(employe);
        System.out.println("Empleado añadido");
    }
    public void removeEmployee(Employe employe) {
        for(Department department:departmentList){
            if(department.employeExist(employe)){
                department.removeEmploye(employe);
            }
        }
        System.out.println("Empleado eliminado");
    }
    public void changeDepartment(Employe employe, Department department) {
        removeEmployee(employe);
        addEmployee(employe,department);
        System.out.println("Departamento cambiado");
    }
}

