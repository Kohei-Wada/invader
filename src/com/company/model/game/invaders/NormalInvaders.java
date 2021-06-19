package com.company.model.game.invaders;

import com.company.model.game.bullets.*;
import com.company.model.game.player.Player;

import java.awt.*;

public class NormalInvaders extends Invaders {

    private final Bullets normalBullets;

    public NormalInvaders(InvadersManager m) {
        super();
        IBFactory factory = new IBFactory();
        normalBullets     = factory.create(BulletTypes.NORMAL);
        m.addInvaders(this);
        m.getBulletsManager().addBullets(normalBullets);
    }

    @Override
    public void updateInvaders() {
        if (random.nextInt(40) == 1) {
            add(new NormalInvader(random.nextInt(sizeX - 30), 0, this));
        }

        forEach(Invader::updateInvader);
        //remove dead invaders
        removeIf(Invader::isDead);
        //remove invaders that has gone off the stage
        removeIf(invader -> invader.getY() > sizeY);
    }

    @Override
    public boolean bulletHitsInvader(Bullet b) {
        return stream().anyMatch(i -> i.bulletHitInvader(b));
    }

    @Override
    public boolean invaderHitPlayer(Player p) {
        return stream().anyMatch(i -> i.hitPlayer(p));
    }

    public Bullets getNormalInvaderBullets() {
        return normalBullets;
    }
}

class NormalInvader extends Invader {
    private final Bullets normalBullets;

    public NormalInvader(int x, int y, NormalInvaders invaders) {
        super(x, y, 1, 30);
        normalBullets = invaders.getNormalInvaderBullets();
    }

    private void firingBullet() {
        if (random.nextInt(20) == 1)
            normalBullets.addBullet(x + 12, y + 5);
    }

    @Override
    protected void drawInvader() {
        g.setColor(Color.red);
        g.fillRect(x, y, 30, 10);
        g.fillRect(x + 10, y + 10, 10, 10);
    }

    //this method is called when a bullet hits
    @Override
    public boolean bulletHitInvader(Bullet b) {
        int bx = b.getX();
        int by = b.getY();

        if (hp != 0 && bx >= x && bx <= x + 30 && by >= y && by <= y + 20) {
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
        } else if (!dead) {
            drawScore();
            if (--interval == 0) {
                dead = true;
            }
        }
    }

    //collision detection
    @Override
    public boolean hitPlayer(Player p) {
        int px = p.getX();
        int py = p.getY();
        return hp != 0 && ((x >= px && x <= px + 30 && y >= py && y <= py + 20) ||
                (x + 30 >= px && x + 30 <= px + 30 && y + 20 >= py && y + 20 <= py + 20));
    }
}