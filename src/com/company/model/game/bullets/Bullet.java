package com.company.model.game.bullets;

import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;

public abstract class Bullet {
    protected int x, y;
    protected int vx, vy; //velocity vector unit
    protected final Graphics g;

    public Bullet(int x, int y, int vx, int vy) {
        this.x  = x;
        this.y  = y;
        this.vx = vx;
        this.vy = vy;
        this.g  = UI.getUi().graphic();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void drawBullet();

    public abstract void updateBullet();

    public abstract boolean hitsPlayer(Player p);
}
