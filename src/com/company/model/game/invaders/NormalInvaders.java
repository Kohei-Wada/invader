package com.company.model.game.invaders;

import com.company.model.game.bullets.NormalInvaderBullets;
import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;
import java.util.LinkedList;

public class NormalInvaders extends Invaders{
    private final Graphics g;
    private final LinkedList<NormalInvader> normalInvaders;

    private final NormalInvaderBullets normalInvaderBullets;

    private final int sizeX, sizeY;

    public NormalInvaders(InvadersManager manager) {
        normalInvaders = new LinkedList<>();
        normalInvaderBullets = new NormalInvaderBullets();
        UI ui = UI.getUi();
        g = ui.graphic();
        sizeX = ui.getWid();
        sizeY = ui.getHgt();

        //test
        addInvader(100, 100);
        addInvader(400, 100);

        manager.addInvaders(this);
        manager.getBulletsManager().addBullets(normalInvaderBullets);


    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    @Override
    public void updateInvaders() {
        normalInvaders.forEach(NormalInvader::updateInvader);
    }

    @Override
    public boolean invaderIsDead(PlayerBullet b) {
        for (NormalInvader i : normalInvaders) {
            if (i.isDead(b)) {
                removeInvader(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean invaderHitPlayer(Player p) {
        for (NormalInvader i : normalInvaders) {
            if (i.hitPlayer(p)) {
                return true;
            }
        }
        return false;
    }

    public Graphics graphics() {
        return g;
    }

    public void addInvader(int x, int y) {
        normalInvaders.add(new NormalInvader(x, y, this));
    }

    public void removeInvader(NormalInvader i) {
        normalInvaders.remove(i);
    }

    public NormalInvaderBullets getNormalInvaderBullets() {
        return normalInvaderBullets;
    }
}


class NormalInvader extends Invader{
    private final Graphics g;
    private final NormalInvaders invaders;
    private final NormalInvaderBullets normalInvaderBullets;

    public NormalInvader(int x, int y, NormalInvaders invaders) {
        this.normalInvaderBullets = invaders.getNormalInvaderBullets();
        this.invaders = invaders;
        this.x = x;
        this.y = y;
        this.g = invaders.graphics();

    }

    private void firingBullet() {
        normalInvaderBullets.addNormalInvaderBullet(x + 12, y);
    }

    @Override
    public void updateInvader() {
        drawInvader();
        firingBullet();
        if (y > invaders.getSizeY())
            invaders.removeInvader(this);
    }

    @Override
    public void drawInvader() {
        g.setColor(Color.red);
        g.fillRect(x, y , 30, 10);
        g.fillRect(x + 10, y + 10, 10, 10);
    }

    @Override
    public boolean isDead(PlayerBullet b) {
        int bx = b.getX();
        int by = b.getY();
        return bx >= x && bx <= x + 30 && by >= y && by <= y + 20;
    }

    @Override
    public boolean hitPlayer(Player p) {
        int px = p.getX();
        int py = p.getY();

        return (x >= px && x <= px + 30 && y >= py && y <= py + 20) ||
                (x + 30 >= px && x + 30 <= px + 30 && y + 20 >= py && y + 20 <= py + 20);
    }
}