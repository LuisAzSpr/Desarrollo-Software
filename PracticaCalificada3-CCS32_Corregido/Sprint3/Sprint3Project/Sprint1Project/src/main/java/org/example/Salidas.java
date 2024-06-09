package org.example;

public class Salidas implements Graficas{
    private Juego juego;
    public Salidas(Juego juego){
        this.juego = juego;
    }

    // se encarga de imprimir el laberinto
    @Override
    public void mostrarLaberinto(){
        String[][] matriz = juego.getLaberinto().getMatriz();
        Jugador jugador = juego.getJugador();
        System.out.println("Vidas del jugador: "+jugador.getVida());
        System.out.println("Puntaje del jugador: "+jugador.getPuntaje());
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                System.out.print(matriz[i][j]+"\t");
            }
            System.out.println();
        }
    }

    // se encarga de mostrar el fin del juego
    @Override
    public void mostrarFinDelJuego(){
        System.out.println("===============================");
        int estado = juego.verificarEstado();
        Jugador jugador = juego.getJugador();
        if(estado==1){ // si el estado = 1 -> el jugador gano
            System.out.println("Ganaste!!! :)");
        }
        else if(estado==-1){ // si el estado = -1 -> el jugador perdio la partida
            System.out.println("Perdiste :( , suerte a la proxima ... ");
        }
        System.out.println("Puntaje: "+jugador.getPuntaje());
        System.out.println("Numero de vidas: "+jugador.getVida());
        System.out.println("===============================");
    }
}
