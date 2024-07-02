package org.example;

public class Salidas {

    private Juego juego;

    public Salidas(Juego juego){
        this.juego = juego;
    }

    public void mostrarLaberinto(){
        Laberinto laberinto = juego.getLaberinto();
        Jugador jugador = juego.getJugador();
        System.out.println("Vidas :"+jugador.getVidas());
        System.out.println("Puntaje :"+jugador.getPuntaje());
        for(int i=0;i< laberinto.getSize();i++){
            for(int j=0;j<laberinto.getSize();j++){
                System.out.print(laberinto.getMatriz()[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
}
