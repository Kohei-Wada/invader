package com.company.model.game.invaders;

import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;


public abstract class Invader {
    protected int x, y;
    protected int hp;
    protected int score;
    protected boolean dead;
    protected final Graphics g;

    public Invader(int x, int y) {
        this.x = x;
        this.y = y;
        this.dead = false;
        this.hp = 1;
        this.g = UI.getUi().graphic();
    }

    protected boolean isDead() {
        return dead;
    }

    protected void setDead() {
        dead = true;
    }

    protected void setScore(int n) {
        score = n;
    }

    protected int getY() {
        return y;
    }
    protected int getX() {
        return x;
    }

    public abstract void updateInvader();
    public abstract void drawInvader();
    public abstract boolean bulletHitInvader(PlayerBullet b);
    public abstract boolean hitPlayer(Player p);
}
