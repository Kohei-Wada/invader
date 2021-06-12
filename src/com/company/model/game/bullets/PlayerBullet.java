package com.company.model.game.bullets;

import com.company.model.game.player.Player;

import java.awt.*;

public class PlayerBullet extends Bullet {

    public PlayerBullet(int x, int y) {
        super(x, y);
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

    //Never used
    @Override
    public boolean hitPlayer(Player p) {
        return false;
    }
}
