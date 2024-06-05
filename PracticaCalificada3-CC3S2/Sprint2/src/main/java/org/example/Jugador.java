package org.example;

import java.lang.management.ClassLoadingMXBean;

public class Jugador {
    private int[] posicionActual;
    private int puntaje = 0;
    private int vidas = 3;

    public Jugador(int[]posicionActual){
        this.posicionActual = new int[2];
        this.posicionActual[0] = posicionActual[0];
        this.posicionActual[1] = posicionActual[1];

    }

    public void moverJugador(int[]posicionFinal,int size){
        if(existenColisiones(posicionFinal,size)){ // verifica si sale del laberinto
            actualizarPosicion(posicionFinal); // sino entonces actualiza la posicion
        }
    }

    public boolean recogerTesoro(String[][]matriz,int[]posFinal){
        return matriz[posFinal[0]][posFinal[1]].equals("T"); // verfica si la posicionfinal es o no un tesoro
    }

    public boolean pierdeVida(String[][]matriz,int []posFinal){
        return matriz[posFinal[0]][posFinal[1]].equals("X"); // verifica si la posicionfinal es o no una X
    }

    public void actualizarPosicion(int[]posicionFinal){
        setPosicionActual(posicionFinal);
    }

    public boolean existenColisiones(int[]posicionFinal,int size){
        return posicionFinal[0]<0 ||posicionFinal[0]>=size ||
                posicionFinal[1]<0 || posicionFinal[1]>=size;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getVidas() {
        return vidas;
    }

    public int[] getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int[] posicionActual) {
        this.posicionActual = posicionActual;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}
