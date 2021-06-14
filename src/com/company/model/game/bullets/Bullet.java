package com.company.model.game.bullets;

import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;

public abstract class Bullet {
    protected int x, y;
    protected final Graphics g;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = UI.getUi().graphic();
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
