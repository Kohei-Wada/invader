package com.company.model.game.bullets;

import com.company.model.game.player.Player;

public interface Bullets {
    void updateBullets();
    boolean bulletHitPlayer(Player p);
}
