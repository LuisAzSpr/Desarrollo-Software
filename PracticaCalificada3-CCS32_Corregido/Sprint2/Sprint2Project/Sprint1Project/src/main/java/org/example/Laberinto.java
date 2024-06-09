package org.example;

import javax.print.attribute.standard.RequestingUserName;
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
        Random random = new Random();
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matriz[i][j] = ".";
            }
        }
        matriz[x][y] = "P";
    }

    public void colcarTesorosYTrampas(int n){
        Random random = new Random();
        int contador = 0;
        while(contador<n){ // creamos un while para que no se sobreescriban
            int[] posicionTesoros = new int[]{random.nextInt(size),random.nextInt(size)}; // posicion aleatoria para los tesoros
            int[] posicionTrampas = new int[]{random.nextInt(size),random.nextInt(size)}; // posicion aleatoria para las trampas
            if(matriz[posicionTesoros[0]][posicionTesoros[1]].equals(".")
                    && matriz[posicionTrampas[0]][posicionTrampas[1]].equals(".")){
                matriz[posicionTesoros[0]][posicionTesoros[1]] = "T";
                matriz[posicionTrampas[0]][posicionTrampas[1]] = "X";
                contador++;
            }
        }
    }

    public void actualizarCelda(int[]posicion,String celda){
        matriz[posicion[0]][posicion[1]] = celda;
    }

    public void mostrarLaberinto(){

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