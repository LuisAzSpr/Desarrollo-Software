package org.example;

import org.example.enemy.BasicEnemy;
import org.example.enemy.BossEnemy;
import org.example.enemy.Enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Wave {

    // keys => enemigos , values => posiciones en el camino
    private HashMap<Enemy,Integer> enemies;
    private int waveNumber;
    private int[][] camino;
    private int numberOfEnemies;

    public Wave(int waveNumber,int[][]camino) {
        this.camino = camino;
        this.waveNumber = waveNumber;
        this.numberOfEnemies = 0;
        this.enemies = new HashMap<>(); // el enemigo 0
        actualizar();
    }

    public HashMap<Enemy, Integer> generateEnemie(){
        if(waveNumber%5 == 0){ // por cada 5 oleadas saca a un boss
            enemies.put(new BossEnemy((camino[0])),0);
            return enemies;
        }
        if(numberOfEnemies<5){ // añade 5 enemigos si no es la oleada 5
            enemies.put(new BasicEnemy(camino[0]),0);
            numberOfEnemies++;
        }
        return enemies;//retorna a los enemigos
    }


    // Métodos para manejar el progreso de la oleada
    public void actualizar(){
        actualizarPosicion();
        generateEnemie();
    }

    public void actualizarPosicion(){
        for(Enemy enemy:enemies.keySet()){
            int nuevaPos = enemies.get(enemy) + enemy.getSpeed();
            enemies.put(enemy,nuevaPos);
            if(nuevaPos<camino.length){
                enemy.setPosition(camino[nuevaPos]);
            }
            else{
                enemy.setPosition(camino[camino.length-1]);
            }
        }
    }

    public void eliminarEnemigos() {
        Iterator<Map.Entry<Enemy, Integer>> iterator = enemies.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Enemy, Integer> entry = iterator.next();
            Enemy enemy = entry.getKey();
            if (enemy.getHealth() <= 0) {
                iterator.remove();
            }
        }
    }


    public boolean existenEnemigos(){
        return !enemies.isEmpty();
    }

    public List<Enemy> getEnemies() {
        return enemies.keySet().stream().toList();
    }

    public void setEnemies(HashMap<Enemy, Integer> enemies) {
        this.enemies = enemies;
    }

    public void setWaveNumber(int waveNumber) {
        this.waveNumber = waveNumber;
    }

    public void mostrarEnemigos(){
        int c = 0;
        for(Enemy enemy:enemies.keySet()){
            c ++;
            System.out.println("Enemigo "+c+" : "+ enemy.getPosition()[0]+ " | "+enemy.getPosition()[1]+ " , Health :"+enemy.getHealth());
        }
    }




}