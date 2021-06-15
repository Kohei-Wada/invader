package com.company.model.game.bullets;

import com.company.model.game.player.Player;

public interface Bullets {
    void updateBullets();
    void addBullet(int x, int y);
    boolean bulletsHitPlayer(Player p);
}
