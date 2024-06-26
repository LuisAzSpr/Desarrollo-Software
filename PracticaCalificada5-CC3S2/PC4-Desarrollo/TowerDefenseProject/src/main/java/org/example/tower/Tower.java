package org.example.tower;

import org.example.Map;
import org.example.Wave;
import org.example.enemy.Enemy;
import java.util.List;


public class Tower {
    private int damage; // daño de la torre al atacar a un enemigo
    private int range; // radio del daño
    private int fireRate; // cada cuantos ciclos dispara
    private int[]position; // posicion de la torre en el mapa

    public Tower(int damage, int range, int fireRate,int[]position) {
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;
        this.position = position;
    }

    public void attack(Wave wave){
        for(Enemy enemy:wave.getEnemies()){
            int[]enemyPosition = enemy.getPosition();
            if(Math.abs(enemyPosition[0]-position[0])<=range &&
                Math.abs(enemyPosition[1]-position[1])<=range){
                enemy.setHealth(enemy.getHealth()-damage);
            }
        }
    }

    public int[] getPosition() {
        return position;
    }
}
