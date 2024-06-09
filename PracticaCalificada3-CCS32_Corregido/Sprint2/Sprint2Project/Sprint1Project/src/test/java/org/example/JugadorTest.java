package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JugadorTest {

    private Jugador jugador;
    private String mapa[][];

    @BeforeEach
    void setUp(){
        int posicionInicial[] = new int[]{0,1};
        jugador = new Jugador(posicionInicial);
        mapa = new String[][]{{"P","X"},{"T","."}};
    }

    @Test
    void moverJugadorTest(){
        int[] posicionFinal = new int[]{1,0};
        jugador.moverJugador(mapa,posicionFinal);
        assertThat(jugador.getPosicionActual()).isEqualTo(posicionFinal);
    }

    @Test
    void moverJugadorHaciaUnaTrampa(){
        int[] posicionFinal = new int[]{0,1};
        jugador.moverJugador(mapa,posicionFinal);
        assertThat(jugador.getPosicionActual()).isEqualTo(posicionFinal);
        assertThat(jugador.getVida()).isEqualTo(2);
    }

    @Test
    void moverJugadorHaciaUnTesoro(){
        int[] posicionFinal = new int[]{1,0};
        jugador.moverJugador(mapa,posicionFinal);
        assertThat(jugador.getPosicionActual()).isEqualTo(posicionFinal);
        assertThat(jugador.getPuntaje()).isEqualTo(1);
    }

}
