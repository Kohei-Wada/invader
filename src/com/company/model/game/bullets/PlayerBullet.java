package com.company.model.game.bullets;

import com.company.ui.UI;

import java.awt.*;

public class PlayerBullet extends Bullet {

    Graphics g;

    public PlayerBullet(int x, int y) {
        super(x, y);
        g = UI.getUi().graphic();
    }

    @Override
    public void drawBullet() {
        g.setColor(Color.blue);
        g.fillRect(x, y, 5, 5);
    }

    @Override
    public void updateBullet() {
        drawBullet();
        y -= 10;
    }
}
