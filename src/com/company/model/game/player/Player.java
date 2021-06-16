package com.company.model.game.player;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;
import com.company.model.game.bullets.*;
import com.company.model.game.invaders.InvadersManager;
import com.company.ui.UI;

import java.awt.*;

public class Player implements SGObserver {

    private boolean dead;
    private int hp;
    private int x, y;
    private int interval;

    private final Graphics g;
    private final int stageX, stageY;
    private final Bullets currentBullets;
    private final InvadersManager invadersManager;
    private final BulletsManager bulletsManager;


    public Player(StateGame sg) {
        sg.addObserver(this);

        g = UI.getUi().graphic();
        stageX = UI.getUi().getWid();
        stageY = UI.getUi().getHgt();

        x = stageX / 2;
        y = stageY / 3 * 2;
        interval = 8;
        hp = 1;
        dead = false;


        bulletsManager = sg.getBulletsManager();
        invadersManager = sg.getInvadersManager();

        //bullets creation and registering
        PBFactory factory = new PBFactory(invadersManager);
        currentBullets = factory.create(BulletTypes.SHOTGUN);
        bulletsManager.addBullets(currentBullets);
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
        if (interval > 0)
            --interval;
        else {
            currentBullets.addBullet(x + 12, y);
            interval = 8;
        }
    }

    public boolean isDead() {
        return dead;
    }

    private void damaged() {
        if (--hp == 0)
            dead = true;
    }

    @Override
    public void updateSGO(StateGame sg) {
        if (invadersManager.invaderHitsPlayer(this))
            damaged();
        if (bulletsManager.bulletsHitPlayer(this))
            damaged();
        drawPlayer();
    }
}
