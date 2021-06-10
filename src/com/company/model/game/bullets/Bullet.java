package com.company.model.game.bullets;

public abstract class Bullet {
    protected int x, y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void drawBullet();
    public abstract void updateBullet();
}
