package org.example;

import java.util.ArrayList;
import java.util.List;

/*
    A diferencia del codigo inicial, en este caso estamos creando una clase para los departamentos (Department)
    y para los empleados (Employe) en lugar de representarlos unicamente con strings, esto nos ayudara a interpretar
    mejor la cohesion y el acoplamiento.

    En este caso aqui es donde se relacionan los empleados con sus respectivos departamentos, se
    puede agregar, eliminar o cambiar de departamento a un empleado, por lo que esta clase necesita conocer
    a todos los departamentos existentes, es por esto que se crea una lista de departamentos.

    Ademas la responsabilidad de imprimir los departamentos mediante el metodo printAllDepartments()
    fue delegado a otra clase, esto lo hicimos para que la clase actual tenga una unica responsabilidad

 */

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


/*
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
