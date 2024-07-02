package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int contador = 0;
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        while(true){
            System.out.println(contador);
            contador++;
            try {
                // 2.5 segundos
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                // Manejo de la excepción InterruptedException
                e.printStackTrace();
            }
        }
    }
}