package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class LaberintoTest {

    /*
        Como el laberinto se crea de manera aleatoria no podemos saber exactamente
        como sera la matriz, pero podemos decir cuantos elementos de cada tipo tendra.
        debe haber 1 jugador -> un "P"
        debe haber n-1 espacios en blanco -> n-1 "."
     */
    @Test
    public void inicializarLaberinto(){
        int n = 3;
        Laberinto laberinto = new Laberinto(n);

        laberinto.inicializarLaberinto();
        String[][] matriz = laberinto.getMatriz();

        assertThat(countElements(matriz,"P")).isEqualTo(1);
        assertThat(countElements(matriz,".")).isEqualTo(n*n-1);
    }



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
