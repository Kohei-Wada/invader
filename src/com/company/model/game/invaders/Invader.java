package com.company.model.game.invaders;

import com.company.model.game.bullets.Bullet;
import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;
import java.util.Random;


public abstract class Invader {
    protected int            x;
    protected int            y;
    protected int            hp;
    protected boolean        dead;
    protected int            score;     //score obtained by defeating this invader
    protected int            interval;  //time from hp to 0 to death

    protected final Graphics g;
    protected final Random   random;

    public Invader(int x, int y, int hp, int score) {
        this.x        = x;
        this.y        = y;
        this.hp       = hp;
        this.score    = score;
        this.dead     = false;
        this.interval = 20;
        this.g        = UI.getUi().graphic();
        this.random   = new Random();
    }

    protected boolean isDead() {
        return dead;
    }

    protected void drawScore() {
        g.setColor(Color.WHITE);

        //UI will flick with this code, so I erased it.
        //g.setFont(new Font(String.valueOf(score), Font.PLAIN, 15));
        g.drawString(String.valueOf(score), x, y);
    }

    protected int getY() {
        return y;
    }

    protected int getX() {
        return x;
    }

    public abstract void updateInvader();

    protected abstract void drawInvader();

    public abstract boolean bulletHitInvader(Bullet b);

    public abstract boolean hitPlayer(Player p);
}
