package com.company.model.game.invaders;

import com.company.model.game.bullets.NormalInvaderBullets;
import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class NormalInvaders extends Invaders{
    private final Graphics g;
    private final LinkedList<NormalInvader> normalInvaders;
    private final NormalInvaderBullets normalInvaderBullets;
    private final int sizeX, sizeY;

    private final Random random;

    public NormalInvaders(InvadersManager manager) {
        normalInvaders = new LinkedList<>();
        normalInvaderBullets = new NormalInvaderBullets();
        UI ui = UI.getUi();
        g = ui.graphic();
        sizeX = ui.getWid();
        sizeY = ui.getHgt();
        random = new Random();

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
        if (random.nextInt(40) == 1) {
            addInvader(random.nextInt(sizeX - 30), 0);
        }
        normalInvaders.forEach(NormalInvader::updateInvader);
        deleteInvaders();
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

    private void deleteInvaders() {
        for (int i = 0; i < normalInvaders.size(); ++i) {
            NormalInvader invader = normalInvaders.get(i);
            if (invader.getY() > sizeY) {
                normalInvaders.remove(invader);
                --i;
            }
        }
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
    private final NormalInvaderBullets normalInvaderBullets;
    private final Random random;

    public NormalInvader(int x, int y, NormalInvaders invaders) {
        this.normalInvaderBullets = invaders.getNormalInvaderBullets();
        this.x = x;
        this.y = y;
        this.g = invaders.graphics();
        this.random = new Random();
    }

    private void firingBullet() {
        if (random.nextInt(20) == 1) {
            normalInvaderBullets.addNormalInvaderBullet(x + 12, y + 5);
        }
    }

    @Override
    public void updateInvader() {
        y += 5;
        drawInvader();
        firingBullet();
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