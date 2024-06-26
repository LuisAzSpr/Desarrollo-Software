package org.example;

import org.example.enemy.BasicEnemy;
import org.example.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class MapTest {

    Map map;

    @BeforeEach
    void setUp(){
        map = new Map(5);
        map.inicializarPorDefecto();
        map.mostrarMapa();
    }

    // Testeamos el camino que seguiran los enemigos, este debe tener
    // "C" excepto la ultima casilla que debe ser "B"
    @Test
    void hallarCaminoTest(){
        int n = map.getCamino().length;
        for(int i=0;i<n;i++){
            int[]position =  map.getCamino()[i];
            String elemento = map.getMapa()[position[0]][position[1]];
            // si es la ultima celda, se debe verificar que es la base "B"
            String elementoExpected = i==n-1?"B":"C";
            assertThat(elemento).isEqualTo(elementoExpected);
        }
    }


    // Testeamos el metodo que nos ayuda a encontrar la posicion de la base
    @Test
    void buscarBaseTest(){
        int[] posicion = map.buscarBase();
        String elemento = map.getMapa()[posicion[0]][posicion[1]];
        assertThat(elemento).isEqualTo("B");
    }

    // La actualizacion del mapa es simplemente los enemigos cambiando su posicion
    // por el camino, ya que la torre y la base se mantienen quietas.
    @Test
    void actualizarTest(){
        // Inicializamos los enemigos
        List<Enemy> enemies = new ArrayList<>();
        int[][] camino = map.getCamino();
        // Creamos 3 enemigos en una posicion concreta del camino
        enemies.add(new BasicEnemy(camino[0]));
        enemies.add(new BasicEnemy(camino[1]));
        enemies.add(new BasicEnemy(camino[2]));

        // actualizamos
        map.actualizar(enemies);

        // Ahora se debe cumplir que los enemigos se enucentran en las nuevas posiciones
        for(Enemy enemy:enemies){
            int[] position = enemy.getPosition();
            String elemento = map.getMapa()[position[0]][position[1]];
            assertThat(elemento).isEqualTo("E");
        }
    }

    @Test
    void colocarTorreTest(){
        map.colocarTorre(0,1);
        assertThat(map.getMapa()[0][1]).isEqualTo("T");
    }

}