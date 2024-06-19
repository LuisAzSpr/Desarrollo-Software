package org.example;

import java.util.List;

public class Tower {
    private Map map;
    private int damage;
    private int range;
    private int fireRate; // turnos entre disparos

    // Agregamos una posicion para la torre
    private int[]position = new int[]{0,0}; // por defecto se coloca en 0,0

    // Constructores, getters y setters

    public Tower(int damage,int range,int fireRate){
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;
    }

    public void attack(List<Enemy> enemies,Map map){
        boolean enemigosSeEncuentran = false;
        // si los enemigos se encuentran en el rango
        for(int i=position[0]-range;i<=position[0]+range;i++){
            for(int j=position[1]-range;j<=position[1]+range;j++){
                if(map.getMapa()[i][j].equals("E")){
                    enemigosSeEncuentran = true;
                }
            }
        }
        // si los amigos se encuentran se el rango se les baja vida a todos.
        if(enemigosSeEncuentran){
            for(Enemy enemy:enemies){
                enemy.setHealth(enemy.getHealth()-damage);
            }
        }
    }



    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public int getDamage() {
        return damage;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getPosition() {
        return position;
    }

    public int getRange() {
        return range;
    }

    public int getFireRate() {
        return fireRate;
    }
}