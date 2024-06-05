package org.example;

public class Juego {

    private Laberinto laberinto;
    private Jugador jugador;
    public Juego(int size){
        laberinto = new Laberinto(size);
        // incializamos la posicion inicial del jguador en el tablero
        jugador = new Jugador(encontrarPosicionJugador());
    }

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

        // verificamos que el movimiento sea valido
        if(!noEsMovimientoValido(posicionFinal,laberinto.getSize())){
            return posicionFinal;
        }
        return null;
    }

    public boolean juegoTerminado(){
        if(jugador.getVidas()<0 || !existenTesoros()){
            return true;
        }
        return false;
    }


    public void actualizarEstado(String mov){
        int[] posicionActual = jugador.getPosicionActual();
        int[] posicionFinal = procesarComandos(mov);
        // actualizamos la celda
        laberinto.actualizarCelda(posicionActual,posicionFinal);
        actualizarUsuario(posicionFinal);
    }

    public void actualizarUsuario(int[] posicionFinal){
        String casilla = laberinto.getMatriz()[posicionFinal[0]][posicionFinal[1]];
        jugador.setPosicionActual(posicionFinal);
        if(casilla.equals("T")){
            jugador.setPuntaje(jugador.getPuntaje()+1);
        }
        else if(casilla.equals("X")){
            jugador.setVidas(jugador.getVidas()-1);
        }
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



    // Este metodo se usa para poder determinar la posicion del jugador una vez iniciado el tablero
    // ya que este es aleeatorio y le corresponde a la clase laberinto
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
