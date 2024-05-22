package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReportDepartments {

    private List<Department> departmentList = new ArrayList<>();

    public ReportDepartments(List<Department> departments){
        departmentList = departments;
    }

    public void printDepartmentReport(Department department) {
        System.out.println("Informe del departamento " + department.getNameDepartment());
        System.out.println("Lista de empleados: ");
        for(Employe employe:department.getEmployeList()){
            System.out.println("- "+employe.getName());
        }
    }
    public void printAllDepartments() {
        System.out.println("Lista de todos los departamentos");
        for(Department department:departmentList){
            System.out.println("- "+department.getNameDepartment());
        }
    }
}
