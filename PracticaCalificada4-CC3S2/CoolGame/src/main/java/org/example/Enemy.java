package org.example;

public class Enemy {
    private int speed; // número de celdas por turno
    private int health;
    private int reward;

    public Enemy(int speed, int health, int reward) {
        this.speed = speed;
        this.health = health;
        this.reward = reward;
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
}