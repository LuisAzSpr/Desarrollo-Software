package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;


public class LaberintoTest {

    /*
        Como el laberinto se crea de manera aleatoria no podemos saber exactamente
        como sera la matriz, pero podemos decir cuantos elementos de cada tipo tendra.
        debe haber 1 jugador -> un "P"
        debe haber n-1 espacios en blanco -> n-1 "."
     */
    @Test
    void inicializarLaberinto(){
        int n = 3;
        Laberinto laberinto = new Laberinto(n);

        laberinto.inicializarLaberinto();
        String[][] matriz = laberinto.getMatriz();

        assertThat(countElements(matriz,"P")).isEqualTo(1);
        assertThat(countElements(matriz,".")).isEqualTo(n*n-1);
    }

    /*
        Colocamos los tesoros y trampas en el laberinto, de la misma forma, no sabemos
        en que posiciones se colocaran por lo que solo verificamos que el numero sea
        de tesoros y trampas sea el adecuado.
     */
    @Test
    void colcarTesorosYTrampas(){
        int n = 3,tt = 2;
        Laberinto laberinto = new Laberinto(3);

        laberinto.inicializarLaberinto(); // incializamos el laberinto vacio con el jugador "P"
        laberinto.colcarTesorosYTrampas(tt); // ponemos 2 tesoros y 2 trampas
        String[][] matriz = laberinto.getMatriz();

        assertThat(countElements(matriz,"P")).isEqualTo(1);
        assertThat(countElements(matriz,"X")).isEqualTo(tt);
        assertThat(countElements(matriz,"T")).isEqualTo(tt);
        assertThat(countElements(matriz,".")).isEqualTo(n*n-1-2*tt);
    }

    /*
        Test para verificar que la actualizacion de una celda sea correcta
     */

    @Test
    void actualizarCelda(){
        Laberinto laberinto = new Laberinto(3);
        String matriz[][] = new String[][]{{"P","."},{".","."}};
        laberinto.setMatriz(matriz);
        int[]posicion = new int[]{0,1};
        String tesoro = "T";
        laberinto.actualizarCelda(posicion,tesoro);
        assertThat(laberinto.getMatriz()[posicion[0]][posicion[1]]).isEqualTo(tesoro);
    }


    /*
        Este metodo cuenta el numero de cadenas de un tipo existen en la matriz, con
        esto nos apoyamos para poder testear como se crea el laberinto de manera aleatoria.
     */
    private int countElements(String[][] matriz,String valor){
        int contador = 0;
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                if(matriz[i][j].equals(valor)){
                    contador++;
                }
            }
        }
        return contador;
    }

}
