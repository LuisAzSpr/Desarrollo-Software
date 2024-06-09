package org.example;

public class Juego {
    private Laberinto laberinto;
    private Jugador jugador;

    /*
        Constructor para incializar un juego -> genera el laberinto aleatorio
     */
    public Juego(int size){
        this.laberinto = new Laberinto(size);
        this.jugador = new Jugador(laberinto.encontrarPosicionJugador());
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
        int []posicionActual = jugador.getPosicionActual();
        // Creamos una copia del arreglo inicial
        int []posicionFinal = crearCopia(posicionActual);
        // logica de los movimientos
        switch(mov){
            case "N": posicionFinal[0]-=1;break;
            case "S": posicionFinal[0]+=1 ;break;
            case "E":posicionFinal[1]+=1;break;
            case "O":posicionFinal[1]-=1;break;
            default : throw new IllegalArgumentException("Entrada no valida");
        }
        if(!laberinto.noEsCasillaValida(posicionFinal)){
            return posicionFinal; // si el movimiento es valida retornamos el final
        }
        return posicionActual; // si el movimiento no es valido entonces retornamos el inicial
    }

    private int[] crearCopia(int[] arreglo){
        return new int[]{arreglo[0],arreglo[1]};
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
        else if(laberinto.numeroDeTesoros()==0){
            return 1; // indica que el jugador gano la partida
        }
        else{
            return 0; // indica que el juego esta en curso
        }
    }


    public Jugador getJugador() {
        return jugador;
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }


}
