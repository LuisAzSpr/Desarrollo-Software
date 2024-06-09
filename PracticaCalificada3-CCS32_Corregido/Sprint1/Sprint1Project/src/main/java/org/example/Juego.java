package org.example;

public class Juego {
    private Laberinto laberinto;
    private Jugador jugador;

    public Juego(Laberinto laberinto,Jugador jugador){
        this.laberinto = laberinto;
        this.jugador = jugador;
    }

    public Juego(int size){
        this.laberinto = new Laberinto(size);
        this.jugador = new Jugador(encontrarPosicionJugador());
    }

    // procesa los comandos, apartir de un movimiento (nos devuelve la posicion final
    // a la que se debe mover el jugar)
    public int[] procesarComandos(String mov){
        int posicionActual[] = jugador.getPosicionActual();
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
            return posicionFinal;
        }
        return posicionActual;
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
                if(laberinto.getMatriz()[i][j].equals("P")){
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        posicion[0] = x;
        posicion[1] = y;
        return posicion;
    }

    // verifica si el estado del juego es terminado o no
    public boolean verificarEstado(){
        return true;
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
