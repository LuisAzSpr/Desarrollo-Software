package org.example;

import org.example.enemy.Enemy;
import org.example.tower.CannonTower;
import org.example.tower.SniperTower;
import org.example.tower.Tower;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Map mapa;
    private int baseHealth = 100;
    private List<Tower> towers;
    private int[] posicionBase;

    public Game(){
        towers = new ArrayList<>();
        mapa = new Map(5);
        mapa.inicializarPorDefecto();
        posicionBase = mapa.buscarBase();
        mapa.mostrarMapa();
    }

    public void oleadaGame(){
        int[][] camino = mapa.getCamino();
        Wave wave = new Wave(1,camino);
        while(wave.existenEnemigos() && baseHealth>=0){
            actualizarEstado(wave);
            atacarBase(wave.getEnemies());
            System.out.println("****** Vida de la base: "+baseHealth+" *******");
        }
    }

    public void actualizarEstado(Wave wave){
        wave.actualizar();
        wave.mostrarEnemigos();
        for(Tower tower:towers){
            tower.attack(wave);
        }
        System.out.println("ATAQUE DE TORRE -------- ");
        wave.mostrarEnemigos();
        mapa.actualizar(wave.getEnemies());
        mapa.mostrarMapa();
        wave.eliminarEnemigos();
    }

    // Coloca una torre en el mapa
    public void colocarTorre(String name,int[]posicion){
        Tower tower;
        int i = posicion[0];
        int j = posicion[1];
        if(!mapa.isValidPosition(i,j)){ // si la posicion no es valida para colocar una torre
            return; // retornamos
        }
        mapa.colocarTorre(i,j);
        switch(name){
            case "Cannon": tower = new CannonTower(new int[]{i,j});break; // insertamos una torre Cannon
            case "Sniper": tower = new SniperTower(new int[]{i,j});break; // insertamos una torre Sniper
            default : throw new IllegalArgumentException("Error, tipo de torre no existente!"); // lanzamos una exception
        }
        towers.add(tower);
        System.out.println("Torre colocada en ("+i+" , "+j+")");
    }

    // Metodo que baja la vida a la base cuando es atacada por un enemigo
    public void atacarBase(List<Enemy> enemies){
        for(Enemy enemy:enemies){
            int[] position = enemy.getPosition();
            if(position[0]==posicionBase[0] && position[1]==posicionBase[1]){ // vemos si su posicion es la misma que la base
                baseHealth -= enemy.getDamage(); // un enemigo ataca a l abase
                enemy.setHealth(0); // los enemigos que atacan, mueren
            }
        }
    }

    public void placeTower(Tower tower){
        int[]position = tower.getPosition();
        mapa.colocarTorre(position[0],position[1]);
    }


    public List<Tower> getTowers() {
        return towers;
    }

    public Map getMapa() {
        return mapa;
    }

    public int getBaseHealth() {
        return baseHealth;
    }
}

