package org.example;


import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        int c = 0;
        int numTorres = 1;
        game.getMapa().mostrarMapa();
        while(c<numTorres){
            System.out.print("Tipo de torre: ");
            String tipoDeTorre = scanner.nextLine();
            int[] posicion = insertarTorre(scanner);
            game.colocarTorre(tipoDeTorre,posicion);
            c++;
        }
        System.out.println("========================");
        game.getMapa().mostrarMapa();
        game.oleadaGame();
    }

    private static int[] insertarTorre(Scanner scanner){
        System.out.print("coordenada x: ");
        String x = scanner.nextLine();
        System.out.print("coordenada y: ");
        String y = scanner.nextLine();
        int x_ = Integer.parseInt(x);
        int y_ = Integer.parseInt(y);
        return new int[]{x_,y_};
    }
}