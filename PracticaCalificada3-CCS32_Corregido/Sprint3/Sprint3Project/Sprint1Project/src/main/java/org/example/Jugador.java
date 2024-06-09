package org.example;

public class Jugador {
    private int[] posicionActual;
    private int puntaje = 0;
    private int vida = 3;

    public Jugador(int[] posicionActual) {
        this.posicionActual = posicionActual;
    }

    // implica cambiar las vidas, puntaje y posicion del jugador
    public void moverJugador(String[][] matriz,int[] posicionFinal){
        String casillaFin = matriz[posicionFinal[0]][posicionFinal[1]];
        caerEnTrampa(casillaFin);
        recogerTesoro(casillaFin);
        actualizarPosicion(posicionFinal);
    }

    public void caerEnTrampa(String casillaFinal){
        if(casillaFinal.equals("X")){
            vida--;
        }
    }

    public void recogerTesoro(String casillaFinal){
        if(casillaFinal.equals("T")){
            puntaje++;
        }
    }

    // implica cambiar solo la posicion del jugador (es un setter)
    public void actualizarPosicion(int[]posicionFinal){
        setPosicionActual(posicionFinal);
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getVida() {
        return vida;
    }

    public int[] getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int[] posicionActual) {
        this.posicionActual = posicionActual;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}

