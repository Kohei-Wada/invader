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

    @Override
    public void updateInvaders() {
        if (random.nextInt(40) == 1) {
            addInvader(random.nextInt(sizeX - 30), 0);
        }
        normalInvaders.forEach(NormalInvader::updateInvader);
        checkInvaderIsDead();
        deleteInvaders();
    }

    @Override
    public boolean bulletHitsInvader(PlayerBullet b) {
        for (NormalInvader i : normalInvaders) {
            if (i.bulletHitInvader(b)) {
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

    public void checkInvaderIsDead() {
        normalInvaders.removeIf(NormalInvader::isDead);
    }

    public Graphics graphics() {
        return g;
    }

    private void deleteInvaders() {
        normalInvaders.removeIf(invader -> invader.getY() > sizeY);
    }

    public void addInvader(int x, int y) {
        normalInvaders.add(new NormalInvader(x, y, this));
    }

    public NormalInvaderBullets getNormalInvaderBullets() {
        return normalInvaderBullets;
    }
}


class NormalInvader extends Invader{
    //TODO clean dead count code
    private final Graphics g;
    private final NormalInvaderBullets normalInvaderBullets;
    private final Random random;
    private boolean dead;
    private int deadCount;
    private final int score = 30;

    public NormalInvader(int x, int y, NormalInvaders invaders) {
        this.normalInvaderBullets = invaders.getNormalInvaderBullets();
        this.dead = false;
        this.x = x;
        this.y = y;
        this.g = invaders.graphics();
        this.random = new Random();
        this.deadCount = -1;
    }

    private void firingBullet() {
        if (random.nextInt(20) == 1) {
            normalInvaderBullets.addNormalInvaderBullet(x + 12, y + 5);
        }
    }

    private void drawScore() {
        g.setColor(Color.WHITE);
        g.setFont(new Font(String.valueOf(score), Font.PLAIN, 15));
        g.drawString(String.valueOf(score), x, y);
    }

    @Override
    public void updateInvader() {
        if (deadCount == -1) {
            y += 5;
            drawInvader();
            firingBullet();
        } else {
            drawScore();
            if (--deadCount == 0) {
                dead = true;
            }
        }
    }

    @Override
    public void drawInvader() {
        g.setColor(Color.red);
        g.fillRect(x, y , 30, 10);
        g.fillRect(x + 10, y + 10, 10, 10);
    }

    @Override
    public boolean bulletHitInvader(PlayerBullet b) {
        if (deadCount != -1) return false;

        int bx = b.getX();
        int by = b.getY();

        if (bx >= x && bx <= x + 30 && by >= y && by <= y + 20) {
            deadCount = 20;
            return true;
        }
        return false;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public boolean hitPlayer(Player p) {
        int px = p.getX();
        int py = p.getY();
        return (x >= px && x <= px + 30 && y >= py && y <= py + 20) ||
                (x + 30 >= px && x + 30 <= px + 30 && y + 20 >= py && y + 20 <= py + 20);
    }
}