package com.company.model.game.invaders;

import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;


public abstract class Invader {
    protected int x, y;

    protected int getY() {
        return y;
    }

    protected int getX() {
        return x;
    }
    public abstract void updateInvader();
    public abstract void drawInvader();
    public abstract boolean isDead(PlayerBullet b);
    public abstract boolean hitPlayer(Player p);
}
