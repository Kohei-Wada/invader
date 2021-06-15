package com.company.model.game.bullets;

import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;

public class NormalBullets extends Bullets{

    private final int  stageY;

    public NormalBullets() {
        super();
        stageY = UI.getUi().getHgt();
    }
    @Override
    public void updateBullets() {
        bullets.forEach(Bullet::updateBullet);
        bullets.removeIf(b -> b.getY() > stageY);
    }

    @Override
    public boolean bulletsHitPlayer(Player p) {
        return bullets.stream().anyMatch(b -> b.hitsPlayer(p));
    }

    @Override
    public void addBullet(int x, int y) {
        bullets.add(new Bullet(x, y) {
            @Override
            public void drawBullet() {
                g.setColor(Color.red);
                g.fillRect(x, y, 5, 5);
            }

            @Override
            public void updateBullet() {
                drawBullet();
                y += 10;
            }

            @Override
            public boolean hitsPlayer(Player p) {
                int px = p.getX();
                int py = p.getY();
                return x >= px && x <= px + 30 && y >= py && y <= py + 20;
            }
        });
    }
}
