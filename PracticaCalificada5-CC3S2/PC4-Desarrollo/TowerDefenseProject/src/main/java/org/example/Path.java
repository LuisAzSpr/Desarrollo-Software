package org.example;

import org.example.Exceptions.PathIsntCorrect;


/*
Esta clase nos ayuda a calcular el camino que seguiran los enemigos hacia
nuestra base, es decir, maneja toda la logica para encontrar el camino
o retornar una exception en caso no cumpla con ciertas reglas como:
- Las celdas no son contiguas
- El camino no llega a la base
 */
public class Path {

    private int[][] camino;
    private String[][] mapa;
    private int size;

    public Path(String[][] mapa){
        this.mapa = mapa;
        // encuentra todas las celdas del camino
        this.size = contarCamino();
        // la ultima celda del camino es la de la base
        this.camino = new int[size+1][2];
    }

    public boolean encontrarCamino(){

        boolean exito = false;

        // Encontrando la primera celda (la primera celda debe encontrarse en la primera fila)
        for(int j=0;j<mapa.length;j++){
            if(mapa[0][j].equals("C")){
                camino[0][0] = 0;
                camino[0][1] = j;
                exito = true;
            }
        }

        // si no se encontro la primera celda retornamos una exception
        if(!exito){
            throw new PathIsntCorrect("El camino no es correcto");
        }

        // ahora vamos armando el camino (tienen que ser celdas vecinas)
        for(int i=0;i<size;i++){
            int[] celda = siguienteCeldaDelCamino(camino[i][0],camino[i][1],"C");
            if(i==size-1){
                break;
            }
            if(celda[0]==-1){
                throw new PathIsntCorrect("El camino contiene saltos ... \n "+
                        "Los enemigos no pueden llegar a nuestra base!!");
            }
            camino[i+1][0] = celda[0];
            camino[i+1][1] = celda[1];
        }

        // Ahora tenemos que asegurarnos que la ultima celda del camino es adyacente a la base
        int[] celda = siguienteCeldaDelCamino(camino[size-1][0],camino[size-1][1],"B");
        camino[size][0] = celda[0];
        camino[size][1] = celda[1];
        return true;
    }

    /* Nos ayuda a calcular cual es la siguiente celda del camino
    apartir de la celda actual */
    private int[] siguienteCeldaDelCamino(int x,int y,String elemento){

        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                if((!isNotValid(i,j) &&
                        mapa[i][j].equals(elemento)
                        && noEstaEnCamino(i,j)) && (i!=x || y!=j)){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public boolean isNotValid(int i,int j){
        return i<0 || i>=mapa.length || j<0 || j>=mapa.length;
    }

    // verificamos que la celda no este ya en el camino
    private boolean noEstaEnCamino(int x,int y){
        for(int i=0;i<size;i++){
            if(x==camino[i][0] &&
                    y==camino[i][1]){
                return false;
            }
        }
        return true;
    }


    // contamos el tamanio del camino
    private int contarCamino(){
        int count = 0;
        for(int i=0;i<mapa.length;i++){
            for(int j=0;j<mapa[i].length;j++){
                if(mapa[i][j].equals("C")){
                    count++;
                }
            }
        }
        return count;
    }

    public int[][] getCamino() {
        return camino;
    }

}
