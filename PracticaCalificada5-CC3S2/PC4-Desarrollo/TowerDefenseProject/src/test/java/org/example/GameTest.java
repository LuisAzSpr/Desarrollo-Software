package org.example;

import io.cucumber.java.an.E;
import org.example.enemy.BasicEnemy;
import org.example.enemy.Enemy;
import org.example.tower.CannonTower;
import org.example.tower.SniperTower;
import org.example.tower.Tower;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GameTest {
    Game game;

    @BeforeEach
    void SetUp(){
        game = new Game();
    }

    @Test
    void colocarTorreCannonTest(){
        // Probemos para una torre Cannon
        game.colocarTorre("Cannon",new int[]{1,0});
        // La clase de la torre debe ser CannonTower
        assertThat(game.getTowers().get(1).getClass()).isEqualTo(CannonTower.class);
        // Verifica la posicion de Tower
        assertThat(game.getMapa().getMapa()[1][0]).isEqualTo("T");
    }

    @Test
    void colocarTorreSniperTest(){
        // Probemos para una torre sniper
        game.colocarTorre("Sniper",new int[]{1,4});
        // La clase de la torre debe ser SniperTower
        assertThat(game.getTowers().get(1).getClass()).isEqualTo(SniperTower.class);
        // Verifica la posicion de la torre.
        assertThat(game.getMapa().getMapa()[1][4]).isEqualTo("T");
    }

    @Test
    void colocarTorreNoExistenteTest(){
        // debe lanzar una exception cuando el nombre de la torre no es el correcto
        assertThrows(IllegalArgumentException.class,
                () -> game.colocarTorre("NoExistenteTower", new int[]{1, 4}));
    }

    @Test
    void atacarBaseTest(){
        List<Enemy> enemies = new ArrayList<>();
        int[] posicionBase = game.getMapa().buscarBase();
        // Agregamos 2 enemigos que se encuentran en la base
        enemies.add(new BasicEnemy(posicionBase));
        enemies.add(new BasicEnemy(posicionBase));
        // Ahora veamos que la vida de la base es 100 en un momento inical
        assertThat(game.getBaseHealth()).isEqualTo(100);
        // Ejecutamos el ataque
        game.atacarBase(enemies);
        // Ahora veamos que la vida de la base ha disminuido en 5 dado que
        assertThat(game.getBaseHealth()).isEqualTo(90);
    }

    @Test
    void oleadaGameTest(){

        Game game = new Game();
        int[][]camino = game.getMapa().getCamino();
        Wave wave = new Wave(1,camino);

        List<Enemy> enemies = wave.getEnemies();
        game.oleadaGame();

        // Verifiquemos los movimientos de los enemigos
        assertThat(enemies.get(0).getPosition()).isEqualTo(camino[0]);
        // La torre ha sido dañada por un enemigo
        assertThat(game.getBaseHealth()).isEqualTo(95);

    }

}