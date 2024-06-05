package org.example;

public class Calculator {
    private int numeroA;
    private int numeroB;
    private int numeroC;
    public Calculator(int numeroA,int numeroB,int numeroC){
        this.numeroA = numeroA;
        this.numeroB = numeroB;
        this.numeroC = numeroC;
    }

    public int suma(){
        System.out.println("retorna una suma");
        return numeroA+numeroB;
    }

    public int resta(){
        System.out.println("retorna una resta");
        return numeroA-numeroB;
    }

    public int producto(){
        System.out.println("retorna un producto");
        return numeroA*numeroB;
    }

    public boolean esPar(){
        System.out.println("Verifica si es par");
        return numeroC%2==0;
    }

    public int getNumeroA() {
        return numeroA;
    }

    public int getNumeroB() {
        return numeroB;
    }

    public int getNumeroC() {
        return numeroC;
    }
}
