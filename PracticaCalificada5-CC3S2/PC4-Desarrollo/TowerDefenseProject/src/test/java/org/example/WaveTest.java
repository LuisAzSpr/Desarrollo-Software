package org.example;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.example.enemy.BasicEnemy;
import org.example.enemy.BossEnemy;
import org.example.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WaveTest {

    Map map;
    Wave wave;

    @BeforeEach
    void setUp(){
        map = new Map(5);
        map.inicializarPorDefecto();
        wave = new Wave(1,map.getCamino());
        Wave wave1 = mock(Wave.class);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new BasicEnemy(map.getCamino()[0]));
        enemies.add(new BasicEnemy(map.getCamino()[1]));
        enemies.add(new BasicEnemy(map.getCamino()[2]));
        when(wave1.getEnemies()).thenReturn(enemies);
    }

    @Test
    void generateBasicEnemyTest(){
        wave.generateEnemie();
        List<Enemy> enemyList = wave.getEnemies();
        Enemy enemy = enemyList.get(1);
        Enemy expectedEnemy = new BasicEnemy(map.getCamino()[0]);
        assertThat(enemy.getClass()).isEqualTo(expectedEnemy.getClass());
    }



    @Test
    void actualizarPosicionTest(){
        HashMap<Enemy,Integer> enemies = new HashMap<>();
        int i = 0;

        Enemy enemy = new BasicEnemy(map.getCamino()[i]);
        Enemy enemy2 = new BasicEnemy(map.getCamino()[i+1]);

        enemies.put(enemy,i);
        enemies.put(enemy2,i+1);

        wave.setEnemies(enemies);
        wave.actualizarPosicion();

        assertThat(enemy.getPosition()).isEqualTo(map.getCamino()[i+1]);
        assertThat(enemy2.getPosition()).isEqualTo(map.getCamino()[i+2]);
    }

    @Test
    void existenEnemigosTest(){
        wave.generateEnemie();
        wave.mostrarEnemigos();
        assertThat(wave.existenEnemigos()).isTrue();
    }

    @Test
    void generateBossEnemyTest(){
        wave.setWaveNumber(5);
        wave.generateEnemie();
        List<Enemy> enemyList = wave.getEnemies();
        Enemy bossEnemy = enemyList.get(1);
        Enemy expectedEnemy = new BossEnemy(map.getCamino()[0]);
        assertThat(bossEnemy.getClass()).isEqualTo(expectedEnemy.getClass());
    }

}


/*


 */
