package org.example;

import java.util.Random;

public class NumerosGeneradosAleatoriamente implements NumerosAleatorios{
    Random random = new Random();
    @Override
    public int getNumero(int a, int b) {
        return random.nextInt(b)+a;
    }
}
