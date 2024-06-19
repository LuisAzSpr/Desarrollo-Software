package org.example;

public class LanzamientoDados {
    private NumerosAleatorios numerosAleatorios;
    public LanzamientoDados(NumerosAleatorios numAleat){
        this.numerosAleatorios = numAleat;
    }
    public int lanzarDado(){
        return numerosAleatorios.getNumero(1,6);
    }
}
