package org.example;

public class Juego {

    private Laberinto laberinto;
    private Jugador jugador;

    public Juego(int size){
        laberinto = new Laberinto(size);
        jugador = new Jugador(encontrarPosicionJugador());
    }

    public void procesarComandos(String mov){
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

        // verificamos que el movimiento sea valido
        if(!noEsMovimientoValido(posicionFinal,laberinto.getSize())){
            String[][] matriz = laberinto.getMatriz();
            actualizarUsuario(matriz[posicionFinal[0]][posicionFinal[1]]);
            // la casilla anterior queda vacia
            matriz[posicionActual[0]][posicionActual[1]] = ".";
            // la casilla actual queda con el jugador
            matriz[posicionFinal[0]][posicionFinal[1]] = "P";
            jugador.setPosicionActual(posicionFinal);
            laberinto.setMatriz(matriz);
        }
    }

    public void actualizarUsuario(String casilla){
        if(casilla=="T"){
            jugador.setPuntaje(jugador.getPuntaje()+1);
        }
        else if(casilla=="X"){
            jugador.setVidas(jugador.getVidas()-1);
        }
    }


    // Este metodo se usa para poder determinar la posicion del jugador una vez iniciado el tablero
    private int[] encontrarPosicionJugador(){
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

    private boolean noEsMovimientoValido(int posFin[],int size){
        return posFin[0]<0 ||posFin[0]>=size ||
                posFin[1]<0 || posFin[1]>=size;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }
}
