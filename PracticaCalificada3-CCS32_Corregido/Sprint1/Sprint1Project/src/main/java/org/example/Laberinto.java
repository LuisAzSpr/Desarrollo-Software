package org.example;

import java.util.Random;

public class Laberinto {
    private String[][] matriz;
    private int size;

    public Laberinto(int size){
        this.matriz = new String[size][size];
        this.size = size;
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