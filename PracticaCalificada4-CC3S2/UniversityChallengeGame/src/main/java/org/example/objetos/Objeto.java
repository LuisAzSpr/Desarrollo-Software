package org.example.objetos;

public abstract class Objeto {
    private int tiempo;
    private int energia;
    protected Objeto(int tiempo, int energia){
        this.tiempo = tiempo;
        this.energia = energia;
    }

}
