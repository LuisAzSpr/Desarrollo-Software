package org.example;

public class Enemy {
    private int speed; // número de celdas por turno
    private int health;
    private int reward;
    private int damage; // agregamos este atributo que es el daño que hara el enemigo
    // al chocar con la base

    public Enemy(int speed, int health, int reward,int damage) {
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getReward() {
        return reward;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getDamage() {
        return damage;
    }
}