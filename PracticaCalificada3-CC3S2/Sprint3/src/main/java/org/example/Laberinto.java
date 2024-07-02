package org.example;

import java.util.Random;
import java.util.Set;

public class Laberinto {
    private int size;
    private String [][] matriz;

    public Laberinto(int size){
        this.size = size;
        matriz = new String[size][size];
        // inicializamos con espacios vacios
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matriz[i][j] = ".";
            }
        }
        // Agregamos la posicion del jugador, que es aleatoria
        int[] posicionJugador = posicionAleatoria();
        matriz[posicionJugador[0]][posicionJugador[1]] = "P";

        colcarTesoros();
    }

    public void actualizarCelda(int[]posicionActual,int[]posicionFinal){
        //  la casilla actual entonces queda vacia
        matriz[posicionActual[0]][posicionActual[1]] = ".";
        // la casilla actual queda con el jugador
        matriz[posicionFinal[0]][posicionFinal[1]] = "P";
    }

    public void colcarTesoros(){
        // Ahora pondremos en el mapa 3 tesoros y 3 trampas de manera aleatoria
        int contador = 0;
        while(contador<4){ // creamos un while para que no se sobreescriban
            int[] posicionTesoros = posicionAleatoria(); // posicion aleatoria para los tesoros
            int[] posicionTrampas = posicionAleatoria(); // posicion aleatoria para las trampas
            if(matriz[posicionTesoros[0]][posicionTesoros[1]].equals(".")
                    && matriz[posicionTrampas[0]][posicionTrampas[1]].equals(".")){
                matriz[posicionTesoros[0]][posicionTesoros[1]] = "T";
                matriz[posicionTrampas[0]][posicionTrampas[1]] = "X";
                contador++;
            }
        }
    }

    private int[] posicionAleatoria(){
        int pos[] = new int[2];
        Random random = new Random();
        pos[0] = random.nextInt(size);
        pos[1] = random.nextInt(size);
        return pos;
    }

    public int getSize() {
        return size;
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(String[][] matriz) {
        this.matriz = matriz;
    }

}
