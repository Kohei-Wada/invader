package com.company.model.game.invaders;

import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;


public abstract class Invader {
    protected int x, y;
    protected int hp;
    protected boolean dead;
    protected int score; //score obtained by defeating this invader
    protected int interval; //time from hp to 0 to death
    protected final Graphics g;

    public Invader(int x, int y) {
        this.x = x;
        this.y = y;
        this.dead = false;
        this.hp = 1;
        this.interval = 20;
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

    protected void drawScore() {
        g.setColor(Color.WHITE);
        g.setFont(new Font(String.valueOf(score), Font.PLAIN, 15));
        g.drawString(String.valueOf(score), x, y);
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
