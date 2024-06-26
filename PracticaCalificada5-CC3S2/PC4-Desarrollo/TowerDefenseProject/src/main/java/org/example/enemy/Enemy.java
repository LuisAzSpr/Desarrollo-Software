package org.example.enemy;

public class Enemy {
    private int speed; // número de celdas por turno que recorre el enemigo
    private int health; // vida del enemigo
    private int reward; // beneficio que da el enemigo al eliminarlo
    private int damage; // daño que hace el enemigo a nuestra base
    private int[] position; // posicion del enemigo en el mapa

    public Enemy(int speed, int health, int reward,int damage,int[] position) {
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.damage = damage;
        this.position = position;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int[] getPosition() {
        return position;
    }


    public void setHealth(int health) {
        if(health<0){
            this.health = 0;
            return;
        }
        this.health = health;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}
