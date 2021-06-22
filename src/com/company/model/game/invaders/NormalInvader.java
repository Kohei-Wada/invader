package com.company.model.game.invaders;

import com.company.model.game.bullets.Bullet;
import com.company.model.game.bullets.Bullets;
import com.company.model.game.player.Player;

import java.awt.*;

class NormalInvader extends Invader {
    private final Bullets bullets;
    private final int     bulletInterval;
    private int intervalCount;
    private final int vy = 5;

    public NormalInvader(int x, int y, Bullets bullets) {
        super(x, y, 1, 30);
        this.bullets = bullets;
        this.bulletInterval = 0;
        this.intervalCount = 0;
    }

    private void firingBullet() {
        if (intervalCount > 0) {
            --intervalCount;
        }
        else {
            if (random.nextInt(20) == 1)
                bullets.addBullet(x + 12, y + 5);
            intervalCount = bulletInterval;
        }
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
            y += vy;
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
