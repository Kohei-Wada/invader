package com.company.model.game.player;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;
import com.company.model.game.bullets.*;
import com.company.model.game.invaders.InvadersManager;
import com.company.ui.UI;

import java.awt.*;

public class Player implements SGObserver {

    private final int             interval;
    private final Graphics        g;
    private final int             stageX;
    private final int             stageY;

    private boolean               dead;
    private int                   hp;
    private int                   x;
    private int                   y;
    private int                   counter;
    private Bullets               currentBullets;


    private final InvadersManager invadersManager;
    private final BulletsManager  bulletsManager;


    public Player(StateGame sg) {
        sg.addObserver(this);

        interval = 8;
        g      = UI.getUi().graphic();
        stageX = UI.getUi().getWid();
        stageY = UI.getUi().getHgt();

        x       = stageX / 2;
        y       = stageY / 3 * 2;
        hp      = 1;
        dead    = false;
        counter = 0;

        bulletsManager  = sg.getBulletsManager();
        invadersManager = sg.getInvadersManager();

        //bullets creation and registering
        //TODO implement a mechanism allow player to easily change bullets
        PBFactory factory = new PBFactory(invadersManager);
        currentBullets = factory.create(BulletTypes.NORMAL);
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
        if (counter > 0)
            --counter;
        else {
            currentBullets.addBullet(x + 12, y);
            counter = interval;
        }
    }

    public boolean isDead() {
        return dead;
    }

    private void damaged() {
        if (--hp == 0)
            dead = true;
    }

    private void setCurrentBullets(Bullets bs) {
        currentBullets = bs;
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
