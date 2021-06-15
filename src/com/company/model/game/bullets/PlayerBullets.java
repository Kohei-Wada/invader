package com.company.model.game.bullets;

import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;

import java.awt.*;


public class PlayerBullets extends Bullets {
    private final InvadersManager manager;

    public PlayerBullets(InvadersManager m) {
        super();
        manager = m;
    }

    @Override
    public void updateBullets() {
        bullets.forEach(Bullet::updateBullet);
        //remove bullets that has hit to invader
        bullets.removeIf(manager::bulletHistInvader);
        //remove bullets that has gone off the stage
        bullets.removeIf(b -> b.getY() < 0);
    }

    @Override
    public boolean bulletsHitPlayer(Player p) {
        return false;
    }

    @Override
    public void addBullet(int x, int y) {
        bullets.add(new Bullet(x, y) {
            @Override
            public void drawBullet() {
                g.setColor(Color.blue);
                g.fillRect(x, y, 5, 5);
            }

            @Override
            public void updateBullet() {
                drawBullet();
                y -= 10;
            }

            @Override
            public boolean hitsPlayer(Player p) {
                return false;
            }
        });
    }
}


