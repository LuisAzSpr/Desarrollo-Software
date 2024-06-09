package org.example;

public class Salidas {

    private Juego juego;

    public Salidas(Juego juego){
        this.juego = juego;
    }

    public String[][] mostrarLaberinto(){
        String[][] matriz = juego.getLaberinto().getMatriz();
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                System.out.print(matriz[i][j]+"\t");
            }
            System.out.println();
        }
        return matriz;
    }

}
