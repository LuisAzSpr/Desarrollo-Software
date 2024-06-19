package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Map map = new Map(5); // creamos el mapa por defecto
    private int baseHealth = 100; // vida de la base
    private int puntuacion = 0; // puntuacion al destruir enemigos
    private List<Tower> towers = new ArrayList<>(); // torres que poseemos
    private Wave wave = new Wave(3); // tendremos 3 enemigos
    private int oleada = 1;

    public Game(){

    }

    public void oleadaGame(){
        int contador = 0;
        System.out.println("Oleada "+oleada+" iniciada");
        System.out.println("Enemigos en camino...");
        List<Enemy>enemies = wave.generateEnemies(3);
        int positionEmemies = 0; // posicion de los enemigos (celda del camino en la que se enceuntran)
        while(!enemies.isEmpty() && baseHealth>=0 && contador<4){
            contador++;
            actualizarEstado(positionEmemies);
            mostrarEstado();
            positionEmemies++;
        }
        oleada++;
    }

    public void actualizarEstado(int positionEnemies){
        actualizarPosicionDeEnemigos(positionEnemies);

    }

    public void actualizarPosicionDeEnemigos(int positionEnemies){
        int[][] camino = map.getCamino();
        int i = camino[positionEnemies][0];
        int j = camino[positionEnemies][1];
        if(positionEnemies!=0){
            int i0 = camino[positionEnemies-1][0];
            int j0 = camino[positionEnemies-1][1];
            map.actualizarCelda(i0,j0,"C");
            return;
        }
        map.actualizarCelda(i,j,"E");
    }

    public void mostrarEstado(){
        System.out.println("-----------------------------------");
        map.mostrarMapa();
        System.out.println("Puntacion: "+puntuacion);
        System.out.println("Vida de la base: "+baseHealth);
        System.out.println("-----------------------------------");
    }


    public void colocarTorre(String name,int[]posicion){
        Tower tower;
        int i = posicion[0];
        int j = posicion[1];
        if(!map.isValidPosition(i,j)){
            return;
        }
        map.colocarTorre(i,j);
        switch(name){
            case "Cannon": tower = new CannonTower();break;
            case "Sniper": tower = new SniperTower();break;
            default : throw new IllegalArgumentException("Error, torre no existente!");
        }
        tower.setPosition(new int[]{i,j});
        towers.add(tower);
        System.out.println("Torre colocada en ("+i+" , "+j+")");
    }

    public Map getMap() {
        return map;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getOleada() {
        return oleada;
    }
}
