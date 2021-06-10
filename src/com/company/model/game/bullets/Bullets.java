package com.company.model.game.bullets;

import com.company.model.game.player.Player;

public interface Bullets {
    public void updateBullets();
    public boolean bulletHitPlayer(Player p);
}
