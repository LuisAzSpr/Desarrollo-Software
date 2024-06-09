package org.example;

import java.util.Objects;
import java.util.Random;

public class Laberinto {
    private String[][] matriz;
    private int size;

    public Laberinto(int size){
        this.matriz = new String[size][size];
        this.size = size;
        inicializarLaberinto();
        colcarTesorosYTrampas(size/2);
    }

    public void inicializarLaberinto(){
        int[] posicionJugador = crearArrayAleatorio();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matriz[i][j] = ".";
            }
        }
        matriz[posicionJugador[0]][posicionJugador[1]] = "P";
    }

    public void colcarTesorosYTrampas(int n){
        int contador = 0;
        while(contador<n){ // creamos un while para que no se sobreescriban
            int[] posicionTesoros = crearArrayAleatorio(); // posicion aleatoria para los tesoros
            int[] posicionTrampas = crearArrayAleatorio(); // posicion aleatoria para las trampas
            if(matriz[posicionTesoros[0]][posicionTesoros[1]].equals(".")
                    && matriz[posicionTrampas[0]][posicionTrampas[1]].equals(".")){
                matriz[posicionTesoros[0]][posicionTesoros[1]] = "T";
                matriz[posicionTrampas[0]][posicionTrampas[1]] = "X";
                contador++;
            }
        }
    }

    private int[] crearArrayAleatorio(){
        Random random = new Random();
        int i = random.nextInt(size);
        int j = random.nextInt(size);
        return new int[]{i,j};
    }

    public boolean noEsCasillaValida(int[]casilla){
        return casilla[0]<0 ||casilla[0]>=size ||
                casilla[1]<0 || casilla[1]>=size;
    }

    public int numeroDeTesoros(){
        int contador = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(matriz[i][j].equals("T")){
                    contador++;
                }
            }
        }
        return contador;
    }

    // Este metodo se usa para poder determinar la posicion del jugador una vez iniciado el tablero
    public int[] encontrarPosicionJugador(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                // si el jugador se encuentra en la posicion [i,j] entonces lo guardamos en [x,y]
                if(Objects.equals(matriz[i][j], "P")){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }


    public void actualizarCelda(int[]posicion,String celda){
        matriz[posicion[0]][posicion[1]] = celda;
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public int getSize() {
        return size;
    }

    public void setMatriz(String[][] matriz) {
        this.matriz = matriz;
    }

    public void setSize(int size) {
        this.size = size;
    }
}