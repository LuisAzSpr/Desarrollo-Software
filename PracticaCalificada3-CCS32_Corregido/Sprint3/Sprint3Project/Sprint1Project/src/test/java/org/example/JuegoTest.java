package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class JuegoTest {

    private Juego juego;
    private Laberinto laberinto;
    private Jugador jugador;

    /*
        Este es elcontexto que le damos a nuestro juego inicial
     */
    @BeforeEach
    void setUp(){
        String[][] matriz = new String[][]{{".","X","."}, //agregamos trampas y tesoros
                                            {"X","P","."},
                                            {".","T","T"}};
        laberinto = new Laberinto(3);
        jugador = new Jugador(new int[]{1,1});
        jugador.setVida(1); // seteamos en una vida al jugador
        laberinto.setMatriz(matriz);
        juego = new Juego(laberinto,jugador);
    }

    /*
        El siguiente test se encarga de encontrar la posicion del jguador en el laberinto
        esto es especialmente importante al inicio, cuando la matriz se genera de manera
        aleatoria que no se puede determinar en donde cae el jugador.
     */
    @Test
    void encontrarPosicionDelJugador(){
        int[] posicionActual = laberinto.encontrarPosicionJugador();
        assertThat(posicionActual).isEqualTo(new int[]{1,1});
    }

    @Test
    void actualizarEstadoAlMoverseAlVacio(){
        int[] posicionActual = laberinto.encontrarPosicionJugador();
        int vidasIniciales = jugador.getVida();
        int puntajeInicial = jugador.getPuntaje();

        juego.actualizarEstado("E"); // al ESTE se encuentra un "."

        // la posicion inicial queda vacia
        assertThat(laberinto.getMatriz()[posicionActual[0]][posicionActual[1]]).isEqualTo(".");
        // en la posicion final debe encontrarse el jugador
        assertThat(laberinto.getMatriz()[1][2]).isEqualTo("P");
        // El puntaje no cambia
        assertThat(jugador.getVida()).isEqualTo(vidasIniciales);
        // Las vidas siguen siendo las mismas
        assertThat(jugador.getPuntaje()).isEqualTo(puntajeInicial);
    }

    @Test
    void actualizarEstadoAlMoverseAunaTrampa(){
        int[] posicionActual = laberinto.encontrarPosicionJugador();
        int vidasIniciales = jugador.getVida();
        int puntajeInicial = jugador.getPuntaje();

        juego.actualizarEstado("N"); // al NORTE se encuentra un "X"

        // la posicion inicial queda vacia
        assertThat(laberinto.getMatriz()[posicionActual[0]][posicionActual[1]]).isEqualTo(".");
        // en la posicion final debe encontrarse el jugador
        assertThat(laberinto.getMatriz()[0][1]).isEqualTo("P");
        // El puntaje no cambia
        assertThat(jugador.getVida()).isEqualTo(vidasIniciales-1);
        // Las vidas disminyen en 1
        assertThat(jugador.getPuntaje()).isEqualTo(puntajeInicial);
    }

    @Test
    void actualizarEstadoAlMoverseAunTesoro(){
        int[] posicionActual = laberinto.encontrarPosicionJugador();
        int vidasIniciales = jugador.getVida();
        int puntajeInicial = jugador.getPuntaje();

        juego.actualizarEstado("S"); // al SUR se encuentra un "T"

        // la posicion inicial queda vacia
        assertThat(laberinto.getMatriz()[posicionActual[0]][posicionActual[1]]).isEqualTo(".");
        // en la posicion final debe encontrarse el jugador
        assertThat(laberinto.getMatriz()[2][1]).isEqualTo("P");
        // El puntaje aumenta en 1
        assertThat(jugador.getVida()).isEqualTo(vidasIniciales);
        // Las vidas no cambian
        assertThat(jugador.getPuntaje()).isEqualTo(puntajeInicial+1);
    }

    // verifica que el juego siga en curso
    @Test
    void verificarEstadoEnCursoDelJuego(){
        int estado = juego.verificarEstado();
        assertThat(estado).isZero();
    }

    // Verifica si el jugador perdio la partida
    @Test
    void verificarEstadoTerminadoDelJuego_Pierde(){

        // Nos movemos a las direcciones de las trampas para perder todas las vidas
        juego.actualizarEstado("N");
        juego.actualizarEstado("E");
        juego.actualizarEstado("S");

        int estado = juego.verificarEstado();

        assertThat(estado).isEqualTo(-1);
    }

    // Verifica si el jugador gano la partida
    @Test
    void verificarEstadoTerminadoDelJuego_Gana(){
        // Nos movemos a las direcciones de todos los tesoros para ganar la partida
        juego.actualizarEstado("S");
        juego.actualizarEstado("E");

        int estado = juego.verificarEstado();

        assertThat(estado).isEqualTo(1);
    }

    /*
        Los 4 tests siguientes se encargan de verificar los movimientos del
        jugador en cada direccion.
     */
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

}
