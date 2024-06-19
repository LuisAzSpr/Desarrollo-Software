package org.example;

public class Map {
    private String[][] mapa;
    private int size;

    private int[][] camino = new int[7][2]; // Camino hacia la base

    public Map(int size){
        this.size = size;
        mapa = new String[size][size];
        inicializarMapaVacio(size);
        mapaPorDefecto();
    }


    // inicializamos el mapa por defecto y el camino a recorrer por los enemigos
    private void mapaPorDefecto(){
        mapa[0][2] = "C"; camino[0][0] = 0; camino[0][1] = 2;
        mapa[1][1] = "C"; camino[1][0] = 1; camino[1][1] = 1;
        mapa[2][0] = "C"; camino[2][0] = 2; camino[2][1] = 0;
        mapa[3][1] = "C"; camino[3][0] = 3; camino[3][1] = 1;
        mapa[3][2] = "C"; camino[4][0] = 3; camino[4][1] = 2;
        mapa[2][3] = "C"; camino[5][0] = 2; camino[5][1] = 3;
        mapa[2][4] = "B"; camino[6][0] = 2; camino[6][1] = 4;
    }

    public int[][] getCamino() {
        return camino;
    }

    private void inicializarMapaVacio(int size) {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                mapa[i][j] = " ";
            }
        }
    }

    public boolean isNotValid(int i,int j){
        return i<0 || i>=size || j<0 || j>=size;
    }

    // Este metodo nos ayudara a actualizar las celdas...
    public void actualizarCelda(int i,int j,String valor){
        mapa[i][j] = valor;
    }

    public boolean isValidPosition(int x,int y){
        return mapa[x][y].equals(" ");
    }

    public void colocarCamino(int i,int j){
        actualizarCelda(i,j,"C");
    }

    public void colocarBase(int i,int j){
        actualizarCelda(i,j,"B");
    }

    public void colocarTorre(int i,int j){
        actualizarCelda(i,j,"T");
    }

    public boolean verificarTorre(int i,int j){
        return mapa[i][j].equals("T");
    }

    public boolean verificarCamino(int i,int j){
        return mapa[i][j].equals("C");
    }

    public String[][] getMapa() {
        return mapa;
    }

    public void setMapa(String[][] mapa) {
        this.mapa = mapa;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void mostrarMapa(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }


    public void mostrarCamino(){
        for(int i=0;i<7;i++){
            for(int j=0;j<2;j++){
                System.out.print(camino[i][j]);
            }
            System.out.println();
        }
    }
}
