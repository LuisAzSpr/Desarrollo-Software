package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego(10);
        Salidas salidas = new Salidas(juego);
        Scanner scanner = new Scanner(System.in);
        while(!juego.juegoTerminado()) {
            salidas.mostrarLaberinto();
            System.out.println("Ingrese a donde quiere moverse: ");
            String movimiento = scanner.nextLine();
            juego.actualizarEstado(movimiento);
        }
    }
}