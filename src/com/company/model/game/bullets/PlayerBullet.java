package com.company.model.game.bullets;

import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;

public class PlayerBullet extends Bullet {

    private final Graphics g;
    private final int stageY;
    private final PlayerBullets playerBullets;

    public PlayerBullet(int x, int y, PlayerBullets playerBullets) {
        super(x, y);

        this.playerBullets = playerBullets;
        stageY = UI.getUi().getHgt();
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

    //Never used
    @Override
    public boolean hitPlayer(Player p) {
        return false;
    }
}
