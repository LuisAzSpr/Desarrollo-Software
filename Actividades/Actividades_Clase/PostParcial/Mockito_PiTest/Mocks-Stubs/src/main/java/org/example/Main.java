package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LanzamientoDados lanzamientoDados = new LanzamientoDados(new NumerosGeneradosAleatoriamente());
        for(int i=1;i<=10;i++){
            System.out.println("lanzamiento "+i+" : "+lanzamientoDados.lanzarDado());
        }
    }
}