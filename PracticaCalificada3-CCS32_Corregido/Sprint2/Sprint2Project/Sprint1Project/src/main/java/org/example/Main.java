package org.example;

import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int estado = 0;
        Juego juego = new Juego(10);
        Salidas salidas = new Salidas(juego);
        Scanner scanner = new Scanner(System.in);
        while(estado==0) {
            estado = juego.verificarEstado();
            salidas.mostrarLaberinto();
            System.out.println("DIRECCION : ");
            String movimiento = scanner.nextLine();
            juego.actualizarEstado(movimiento);
        }

        salidas.mostrarFinDelJuego();

    }
}