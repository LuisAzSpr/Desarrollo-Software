package example;

import org.example.Juego;
import org.example.Jugador;
import org.example.Laberinto;
import org.example.Salidas;
import org.junit.jupiter.api.Test;

public class JuegoTest {

    @Test
    public void actualizarJugadorEnUnMovimiento(){
        Juego juego = new Juego(10);
        String[][] campo = new String[][]{{"P", ".", "."}, {"X",".","X"},{"T",".","."}};
        Laberinto laberinto = juego.getLaberinto();
        Jugador jugador = juego.getJugador();
    }

}
