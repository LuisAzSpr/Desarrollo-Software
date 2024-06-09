package org.example;

import java.util.Objects;

public class Juego {
    private Laberinto laberinto;
    private Jugador jugador;

    /*
        Constructor para incializar un juego -> genera el laberinto aleatorio
     */
    public Juego(int size){
        this.laberinto = new Laberinto(size);
        this.jugador = new Jugador(encontrarPosicionJugador());
    }

    /*
        Constructor para testear -> inicializa el Laberinto y jugador
     */
    public Juego(Laberinto laberinto,Jugador jugador){
        this.laberinto = laberinto;
        this.jugador = jugador;
    }

    // procesa los comandos, apartir de un movimiento (nos devuelve la posicion final
    // a la que se debe mover el jugar)
    public int[] procesarComandos(String mov){
        int posicionActual[] = jugador.getPosicionActual();
        // copiamos el arreglo inicial en el final
        int posicionFinal[] = new int[2];
        posicionFinal[0] = posicionActual[0];
        posicionFinal[1] = posicionActual[1];
        // logica de los movimientos
        switch(mov){
            case "N": posicionFinal[0]-=1;break;
            case "S": posicionFinal[0]+=1 ;break;
            case "E":posicionFinal[1]+=1;break;
            case "O":posicionFinal[1]-=1;break;
        }
        if(!noEsMovimientoValido(posicionFinal,laberinto.getSize())){
            return posicionFinal; // si el movimiento es valida retornamos el final
        }
        return posicionActual; // si el movimiento no es valido entonces retornamos el inicial
    }

    public void actualizarEstado(String mov){
        // Tomamos la posicion inicial y final
        int[] posicionActual = jugador.getPosicionActual();
        int[] posicionFinal = procesarComandos(mov);

        // actualizamos las vidas, puntaje y posicion del jugador (actualizacion del jugador)
        jugador.moverJugador(laberinto.getMatriz(), posicionFinal);

        // actualizamos la celda inicial y final (actualizacion de laberinto)
        laberinto.actualizarCelda(posicionActual,".");
        laberinto.actualizarCelda(posicionFinal,"P");
    }


    // verifica victoria, derrota o juego en curso
    public int verificarEstado(){
        if(jugador.getVida()==0){
            return -1; // indica que el jugador perdio la partida
        }
        else if(!existenTesoros()){
            return 1; // indica que el jugador gano la partida
        }
        else{
            return 0; // indica que el juego esta en curso
        }
    }

    public boolean noEsMovimientoValido(int posFin[],int size){
        return posFin[0]<0 ||posFin[0]>=size ||
                posFin[1]<0 || posFin[1]>=size;
    }

    // Este metodo se usa para poder determinar la posicion del jugador una vez iniciado el tablero
    public int[] encontrarPosicionJugador(){
        int[] posicion = new int[2];
        int x = 0,y=0;
        for(int i=0;i<laberinto.getSize();i++){
            for(int j=0;j<laberinto.getSize();j++){
                // si el jugador se encuentra en la posicion [i,j] entonces lo guardamos en [x,y]
                if(Objects.equals(laberinto.getMatriz()[i][j], "P")){
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        // devolvemos un arreglo con las posiciones del jugador [x,y]
        posicion[0] = x;
        posicion[1] = y;
        return posicion;
    }

    private boolean existenTesoros(){
        for(int i=0;i<laberinto.getSize();i++){
            for(int j=0;j<laberinto.getSize();j++){
                if(laberinto.getMatriz()[i][j].equals("T")){
                    return true;
                }
            }
        }
        return false;
    }


    public Jugador getJugador() {
        return jugador;
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setLaberinto(Laberinto laberinto) {
        this.laberinto = laberinto;
    }
}
