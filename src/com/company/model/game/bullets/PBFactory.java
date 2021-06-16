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

                        //Never used
                        @Override
                        public boolean hitsPlayer(Player p) {
                            return false;
                        }
                    });
                }

            };
        }
        else if (type == BulletTypes.SHOTGUN) {
            return new Bullets() {

                private final InvadersManager invadersManager;

                //initialization
                {
                    invadersManager = manager;
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
                    bullets.add(new ShotGun(x-3, y, -3, -6));
                    bullets.add(new ShotGun(x-2, y, -2, -7));
                    bullets.add(new ShotGun(x-1, y, -1, -8));
                    bullets.add(new ShotGun(x, y, 0, -9));
                    bullets.add(new ShotGun(x+1, y, 1, -8));
                    bullets.add(new ShotGun(x+2, y, 2, -7));
                    bullets.add(new ShotGun(x+3, y, 3, -6));
                }

                class ShotGun extends Bullet {

                    public ShotGun(int x, int y, int vx, int vy) {
                        super(x, y, vx, vy);
                    }

                    @Override
                    public void drawBullet() {
                        g.setColor(Color.blue);
                        g.fillRect(x, y, 5, 5);
                    }

                    @Override
                    public void updateBullet() {
                        drawBullet();
                        x += vx;
                        y += vy;
                    }

                    //Never used
                    @Override
                    public boolean hitsPlayer(Player p) {
                        return false;
                    }
                }
            };

        }
        else return null;

    }

}
