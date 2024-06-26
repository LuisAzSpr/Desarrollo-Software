package org.example;


import org.example.enemy.Enemy;

import java.util.List;

public class Map {
    private String[][] mapa;
    private int size;
    private int[][] camino;

    public Map(int size){
        this.size = size;
        inicializarMapaVacio();
    }

    public void inicializarMapaVacio(){
        mapa = new String[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                mapa[i][j] = " ";
            }
        }
    }

    public void inicializarPorDefecto(){
        mapa = new String[size][size];
        inicializarMapaVacio();
        mapa[0][2] = "C";
        mapa[1][1] = "C";
        mapa[2][0] = "C";
        mapa[3][1] = "C";
        mapa[3][2] = "C";
        mapa[2][3] = "C";
        mapa[2][4] = "B";
        hallarCamino();
    }


    private void hallarCamino() {
        Path path = new Path(mapa);
        if(path.encontrarCamino()){
            camino = path.getCamino();
        }
    }

    public void actualizar(List<Enemy> enemies){
        // borramos los enemigos anteriores del mapa
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(mapa[i][j].equals("E")){
                    mapa[i][j] = "C";
                }
            }
        }
        // mapeamos los nuevos enemigos en el mapa
        for(Enemy enemy:enemies){
            int[] posicion = enemy.getPosition();
            if(!mapa[posicion[0]][posicion[1]].equals("B")){
                mapa[posicion[0]][posicion[1]] = "E";
            }
        }

    }


    public void mostrarMapa(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }

    public int[] buscarBase(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(mapa[i][j].equals("B")){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public String[][] getMapa() {
        return mapa;
    }

    public int[][] getCamino() {
        return camino;
    }

    public boolean isValid(int i,int j){
        return mapa[i][j].equals(" ");
    }

    public void colocarTorre(int i,int j){
        if(isValid(i,j)){
            actualizarCelda(i,j,"T");
        }
    }

    public void actualizarCelda(int i,int j,String valor){
        mapa[i][j] = valor;
    }

    public boolean isValidPosition(int x,int y){
        return mapa[x][y].equals(" ");
    }

    public boolean verificar(int i,int j,String elemento){
        return mapa[i][j].equals(elemento);
    }

    public boolean verificarCamino(int i,int j){
        return verificar(i,j,"C");
    }

    public boolean verificarBase(int i,int j){
        return verificar(i,j,"B");
    }

    public boolean verificarTorre(int i,int j){
        return verificar(i,j,"T");
    }

    public void colocarCamino(int i,int j){
        mapa[i][j] = "C";
    }

}
