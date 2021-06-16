package com.company.model.game.bullets;

import com.company.model.game.player.Player;

import java.util.LinkedList;

public abstract class Bullets {

    protected LinkedList<Bullet> bullets;

    public Bullets() {
        bullets = new LinkedList<>();
    }

    abstract boolean bulletsHitPlayer(Player p);

    abstract void updateBullets();

    public abstract void addBullet(int x, int y);
}
