package com.company.model.game.bullets;

import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;

import java.awt.*;

//Player Bullets Factory
public class PBFactory {

    private final InvadersManager manager;

    public PBFactory(InvadersManager invadersManager) {
        this.manager = invadersManager;
    }

    public Bullets create(BulletTypes type) {
        if (type == BulletTypes.NORMAL) {
            return new Bullets() {
                private final InvadersManager invadersManager;

                //initialization
                {
                    this.invadersManager = manager;
                }

                @Override
                boolean bulletsHitPlayer(Player p) {
                    return false;
                }
                @Override
                void updateBullets() {
                    bullets.forEach(Bullet::updateBullet);
                    //remove bullets that has hit to invader
                    bullets.removeIf(invadersManager::bulletHistInvader);
                    //remove bullets that has gone off the stage
                    bullets.removeIf(b -> b.getY() < 0);
                }

                @Override
                public void addBullet(int x, int y) {
                    bullets.add(new Bullet(x, y, 0, -8) {
                        @Override
                        public void drawBullet() {
                            g.setColor(Color.blue);
                            g.fillRect(x, y, 5, 5);
                        }

                        @Override
                        public void updateBullet() {
                            drawBullet();
                            y += vy;
                        }

                        @Override
                        public boolean hitsPlayer(Player p) {
                            return false;
                        }
                    });
                }

            };
        }
        else return null;

    }

}
