package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Map map = new Map(5); // creamos el mapa por defecto
    private int baseHealth = 100; // vida de la base
    private int puntuacion = 0; // puntuacion al destruir enemigos
    private List<Tower> towers = new ArrayList<>(); // torres que poseemos
    private Wave wave = new Wave(3); // tendremos 3 enemigos
    private List<Enemy>enemies = new ArrayList<>(); // Lista de enemigos
    private int oleada = 1;

    public Game(){

    }

    public void oleadaGame(){
        int contador = 0;
        System.out.println("Oleada "+oleada+" iniciada");
        System.out.println("Enemigos en camino...");
        enemies = wave.generateEnemies(3);
        int times = 0; // posicion de los enemigos (celda del camino en la que se enceuntran)
        while(!enemies.isEmpty() && baseHealth>=0 && contador<4){
            contador++;
            actualizarEstado(times);
            mostrarEstado();
            times++;
        }
        oleada++;
    }

    // actualizamos el estado del juego
    public void actualizarEstado(int times){
        actualizarPosicionDeEnemigos(times);
        atacarConTorre(times);
        baseAtacada(times);
    }

    // nuestra base es atacada (el enemigo ha llegado a nuestra base)
    public void baseAtacada(int times){
        int[][]camino = map.getCamino(); // tomamos el camino con las posiciones del mapa
        if(camino.length-1==times){ // indica que los enemigos han llegado a la base :0
            for(Enemy enemy:enemies){
                baseHealth -= enemy.getDamage();
            }
        }
    }

    // atacamos con las torres que tenemos a nuestros enemigos
    public void atacarConTorre(int times){
        for(Tower tower:towers){ // para cada torre
            if(times%tower.getFireRate()==0){ // verificamos si la tasa de disparo coincide
                tower.attack(enemies,map); // en ese caso atacamos a los enemigos que se encuentren cerca
            }
        }
    }

    // actualizamos la posicion de los enemigos en el mapa
    public void actualizarPosicionDeEnemigos(int positionEnemies){
        int[][] camino = map.getCamino();
        int i = camino[positionEnemies][0];
        int j = camino[positionEnemies][1];
        if(positionEnemies!=0){
            int i0 = camino[positionEnemies-1][0];
            int j0 = camino[positionEnemies-1][1];
            map.actualizarCelda(i0,j0,"C");
        }
        map.actualizarCelda(i,j,"E");
    }

    public void mostrarEstado(){
        System.out.println("-----------------------------------");
        map.mostrarMapa();
        System.out.println("Puntacion: "+puntuacion);
        System.out.println("Vida de la base: "+baseHealth);
        System.out.println("Vida de los enemigos: "+enemies.get(0).getHealth());
        System.out.println("-----------------------------------");
    }


    // colocamos una torre especifica en una posicion exacta
    public void colocarTorre(String name,int[]posicion){
        Tower tower;
        int i = posicion[0];
        int j = posicion[1];
        if(!map.isValidPosition(i,j)){ // si la posicion no es valida para colocar una torre
            return; // retornamos
        }
        map.colocarTorre(i,j);
        switch(name){
            case "Cannon": tower = new CannonTower();break; // insertamos una torre Cannon
            case "Sniper": tower = new SniperTower();break; // insertamos una torre Sniper
            default : throw new IllegalArgumentException("Error, torre no existente!"); // lanzamos una exception
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
