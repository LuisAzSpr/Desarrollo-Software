package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Department> departmentArrayList = new ArrayList<>();
        ReportDepartments reportDepartments = new ReportDepartments(departmentArrayList);

        departmentArrayList.add(new Department("Recursos humanos"));
        departmentArrayList.add(new Department("TI"));
        departmentArrayList.add(new Department("Publicidad"));

        for(int i=0;i<departmentArrayList.size();i++){
            Department department = departmentArrayList.get(i);
            for(int j=1;j<=3;j++){
                String nameEmployer = "Empleado "+ j +" de "+department.getNameDepartment();
                department.addEmploye(new Employe(nameEmployer));
            }
        }

        for(Department department:departmentArrayList){
            reportDepartments.printDepartmentReport(department);
        }

    }
}