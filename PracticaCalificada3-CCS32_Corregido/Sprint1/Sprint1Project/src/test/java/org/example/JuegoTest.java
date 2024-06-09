package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JuegoTest {

    private Juego juego;
    private Laberinto laberinto;
    private Jugador jugador;
    @BeforeEach
    void setUp(){
        String[][] matriz = new String[][]{{".",".","."},
                                            {".","P","."},
                                            {".",".","."}};
        laberinto = new Laberinto(3);
        jugador = new Jugador(new int[]{1,1});
        laberinto.setMatriz(matriz);
        juego = new Juego(laberinto,jugador);
    }

    @Test
    void noEsMovimientoValidoTest(){
        int size = laberinto.getSize();
        assertThat(juego.noEsMovimientoValido(new int[]{size+1,0},size)).isTrue();
        assertThat(juego.noEsMovimientoValido(new int[]{-1,0},size)).isTrue();
        assertThat(juego.noEsMovimientoValido(new int[]{0,size+1},size)).isTrue();
        assertThat(juego.noEsMovimientoValido(new int[]{0,-1},size)).isTrue();
    }

    @Test
    void procesarComandosAlNorte(){
        int[] posicionFinal = juego.procesarComandos("N");
        assertThat(posicionFinal).isEqualTo(new int[]{0,1});
    }
    @Test
    void procesarComandosAlSur(){
        int[] posicionFinal = juego.procesarComandos("S");
        assertThat(posicionFinal).isEqualTo(new int[]{2,1});
    }
    @Test
    void procesarComandosAlEste(){
        int[] posicionFinal = juego.procesarComandos("E");
        assertThat(posicionFinal).isEqualTo(new int[]{1,2});
    }
    @Test
    void procesarComandosAlOEste(){
        int[] posicionFinal = juego.procesarComandos("O");
        assertThat(posicionFinal).isEqualTo(new int[]{1,0});
    }

    @Test
    void encontrarPosicionDelJugador(){
        int[] posicionActual = juego.encontrarPosicionJugador();
        assertThat(posicionActual).isEqualTo(new int[]{1,1});
    }

}
