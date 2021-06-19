package com.company.model.game.bullets;

import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;

//Invaders Bullet Factory
public class IBFactory {

    public Bullets create(BulletTypes type) {

        switch (type) {
            case NORMAL -> {
                return new Bullets() {
                    private final int stageY;

                    //initialization
                    {
                        stageY = UI.getUi().getHgt();
                    }

                    @Override
                    boolean bulletsHitPlayer(Player p) {
                        return bullets.stream().anyMatch(b -> b.hitsPlayer(p));
                    }

                    @Override
                    void updateBullets() {
                        bullets.forEach(Bullet::updateBullet);
                        bullets.removeIf(b -> b.getY() > stageY);
                    }

                    @Override
                    public void addBullet(int x, int y) {
                        bullets.add(new Bullet(x, y, 0, 8) {
                            @Override
                            public void drawBullet() {
                                g.setColor(Color.red);
                                g.fillRect(x, y, 5, 5);
                            }

                            @Override
                            public void updateBullet() {
                                drawBullet();
                                y += vy;
                            }

                            @Override
                            public boolean hitsPlayer(Player p) {
                                int px = p.getX();
                                int py = p.getY();
                                return x >= px && x <= px + 30 && y >= py && y <= py + 20;
                            }
                        });
                    }
                };
            }

            case SHOTGUN -> {
                return new Bullets() {

                    class ShotGun extends Bullet{
                        public ShotGun(int x, int y, int vx, int vy) {
                            super(x, y, vx, vy);
                        }

                        @Override
                        public void drawBullet() {
                            g.setColor(Color.red);
                            g.fillRect(x, y, 5, 5);
                        }

                        @Override
                        public void updateBullet() {
                            drawBullet();
                            x += vx;
                            y += vy;
                        }

                        @Override
                        public boolean hitsPlayer(Player p) {
                            int px = p.getX();
                            int py = p.getY();
                            return x >= px && x <= px + 30 && y >= py && y <= py + 20;
                        }
                    }

                    private final int stageY;

                    //initialization
                    {
                        stageY = UI.getUi().getHgt();
                    }

                    @Override
                    boolean bulletsHitPlayer(Player p) {
                        return bullets.stream().anyMatch(b -> b.hitsPlayer(p));
                    }

                    @Override
                    void updateBullets() {
                        bullets.forEach(Bullet::updateBullet);
                        bullets.removeIf(b -> b.getY() > stageY);
                    }

                    @Override
                    public void addBullet(int x, int y) {
                        bullets.add(new ShotGun(x - 3, y, -3, 6));
                        bullets.add(new ShotGun(x - 2, y, -2, 7));
                        bullets.add(new ShotGun(x - 1, y, -1, 8));
                        bullets.add(new ShotGun(x, y, 0, 9));
                        bullets.add(new ShotGun(x + 1, y, 1, 8));
                        bullets.add(new ShotGun(x + 2, y, 2, 7));
                        bullets.add(new ShotGun(x + 3, y, 3, 6));
                    }
                };
            }
            default -> {
                return null;
            }
        }
    }
}
