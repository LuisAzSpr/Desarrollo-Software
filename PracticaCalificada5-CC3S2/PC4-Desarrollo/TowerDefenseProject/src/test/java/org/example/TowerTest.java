package org.example;

import org.example.enemy.Enemy;
import org.example.tower.CannonTower;
import org.example.tower.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class TowerTest {
    private Tower tower;
    private Map map;

    @BeforeEach
    void setUp(){
        tower = new Tower(10,2,1,new int[]{0,0});
        map = new Map(5);
        map.inicializarPorDefecto();
    }

    @Test
    void towerAttackTest(){
        // declaramos los mocks
        Enemy enemy1 = mock(Enemy.class);
        Enemy enemy2 = mock(Enemy.class);

        // definimos su comportamiento (posicion)
        when(enemy1.getPosition()).thenReturn(new int[]{2,0}); // es alcanzado por la torre
        when(enemy2.getPosition()).thenReturn(new int[]{3,2}); // no es alcanzado por la torre

        // definimos su vida inicial
        when(enemy1.getHealth()).thenReturn(100);
        when(enemy2.getHealth()).thenReturn(100);

        Wave wave = new Wave(1,map.getCamino());

        HashMap<Enemy,Integer> enemies = new HashMap<>();
        enemies.put(enemy1,0);
        enemies.put(enemy2,1);

        wave.setEnemies(enemies);

        // Ejecutamos el ataque
        tower.attack(wave);

        // debe ser invoocado restandole 10 puntos por el ataque de la torre
        verify(enemy1).setHealth(90);
        // no debe ser invocado ya que se encuentra fuera del rango de la torre
        verify(enemy2,never()).setHealth(anyInt());
    }


}

