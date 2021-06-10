package com.company.model.game.player;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;
import com.company.model.game.bullets.BulletsManager;
import com.company.model.game.bullets.PlayerBullets;
import com.company.model.game.invaders.InvadersManager;
import com.company.ui.UI;

import java.awt.*;

public class Player implements SGObserver {


    private boolean dead;
    private int x, y;
    private final Graphics g;
    private final int stageX, stageY;
    private final PlayerBullets playerBullets;
    private final InvadersManager invadersManager;
    private final BulletsManager bulletsManager;


    public Player(StateGame sg) {
        x = 0;
        y = 0;
        dead = false;
        sg.addObserver(this);
        g = UI.getUi().graphic();
        stageX = UI.getUi().getWid();
        stageY = UI.getUi().getHgt();

        bulletsManager = sg.getBulletsManager();
        invadersManager = sg.getInvadersManager();

        playerBullets = new PlayerBullets(invadersManager);
        bulletsManager.addBullets(playerBullets);

    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addX(int n) {
        if (x + n > 0 && x + n < stageX)
        x += n;
    }

    public void addY(int n) {
        if (y + n > 0 && y + n < stageY)
        y += n;
    }

    private void drawPlayer() {
        g.setColor(Color.blue);
        g.fillRect(x + 10, y, 10, 10);
        g.fillRect(x, y + 10, 30, 10);
    }

    public void firingBullet() {
        playerBullets.addPlayerBullet(x + 12, y);
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void updateSGO(StateGame sg) {
        if (invadersManager.invaderHitsPlayer(this)) {
            dead = true;
        }
        if (bulletsManager.bulletHitsPlayer(this)) {
            dead = true;
        }

        drawPlayer();
    }
}
