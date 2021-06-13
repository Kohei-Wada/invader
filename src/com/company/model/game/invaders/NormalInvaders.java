package com.company.model.game.invaders;

import com.company.model.game.bullets.NormalBullets;
import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class NormalInvaders extends Invaders{

    private final LinkedList<NormalInvader> normalInvaders;
    private final NormalBullets normalBullets;
    private final int sizeX, sizeY;
    private final Random random;


    public NormalInvaders(InvadersManager m) {
        normalInvaders = new LinkedList<>();
        normalBullets = new NormalBullets();
        UI ui = UI.getUi();
        sizeX = ui.getWid();
        sizeY = ui.getHgt();
        random = new Random();

        m.addInvaders(this);
        m.getBulletsManager().addBullets(normalBullets);
    }

    @Override
    public void updateInvaders() {
        if (random.nextInt(40) == 1) {
            addInvader(random.nextInt(sizeX - 30), 0);
        }
        normalInvaders.forEach(NormalInvader::updateInvader);
        removeDeadInvaders();
        removeInvaders();
    }

    @Override
    public boolean bulletHitsInvader(PlayerBullet b) {
        return normalInvaders.stream().anyMatch(i -> i.bulletHitInvader(b));
    }

    @Override
    public boolean invaderHitPlayer(Player p) {
        return normalInvaders.stream().anyMatch(i -> i.hitPlayer(p));
    }

    private void removeDeadInvaders() {
        normalInvaders.removeIf(NormalInvader::isDead);
    }

    //remove invaders that has gone off the stage
    private void removeInvaders() {
        normalInvaders.removeIf(invader -> invader.getY() > sizeY);
    }

    public void addInvader(int x, int y) {
        normalInvaders.add(new NormalInvader(x, y, this));
    }

    public NormalBullets getNormalInvaderBullets() {
        return normalBullets;
    }
}

class NormalInvader extends Invader{
    //TODO clean death count code
    private final NormalBullets normalInvaderBullets;

    public NormalInvader(int x, int y, NormalInvaders invaders) {
        super(x, y, 1, 30);
        normalInvaderBullets = invaders.getNormalInvaderBullets();
    }

    private void firingBullet() {
        if (random.nextInt(20) == 1) {
            normalInvaderBullets.addNormalBullet(x + 12, y + 5);
        }
    }

    @Override
    protected void drawInvader() {
        g.setColor(Color.red);
        g.fillRect(x, y , 30, 10);
        g.fillRect(x + 10, y + 10, 10, 10);
    }

    //this method is called when a bullet hits
    @Override
    public boolean bulletHitInvader(PlayerBullet b) {
        int bx = b.getX();
        int by = b.getY();

        if (bx >= x && bx <= x + 30 && by >= y && by <= y + 20 && hp != 0) {
            --hp;
            return true;
        }
        return false;
    }

    @Override
    public void updateInvader() {
        if (hp != 0) {
            y += 5;
            drawInvader();
            firingBullet();
        }
        else if (!dead){
            drawScore();
            if (--interval == 0) {
                setDead();
            }
        }
    }

    @Override
    public boolean hitPlayer(Player p) {
        int px = p.getX();
        int py = p.getY();
        return (x >= px && x <= px + 30 && y >= py && y <= py + 20) ||
                (x + 30 >= px && x + 30 <= px + 30 && y + 20 >= py && y + 20 <= py + 20);
    }
}