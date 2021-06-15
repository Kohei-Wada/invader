package com.company.model.game.bullets;

import com.company.model.game.player.Player;

import java.util.LinkedList;

public abstract class Bullets {

    protected LinkedList<Bullet> bullets;

    public Bullets() {
        bullets = new LinkedList<>();
    }

    void updateBullets() {
        bullets.forEach(Bullet::updateBullet);
    }
    abstract void addBullet(int x, int y);
    abstract boolean bulletsHitPlayer(Player p);
}
