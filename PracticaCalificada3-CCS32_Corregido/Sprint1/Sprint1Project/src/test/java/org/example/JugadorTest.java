package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JugadorTest {
    @Test
    void moverJugadorTest(){
        int[] posicionInicial = new int[]{2,3};
        int[] posicionFinal = new int[]{3,4};
        Jugador jugador = new Jugador(posicionInicial);
        jugador.moverJugador(new String[0][0],posicionFinal);
        assertThat(jugador.getPosicionActual()).isEqualTo(posicionFinal);
    }




}
