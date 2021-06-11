package com.company.model.game.invaders;

import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;

public abstract class Invaders {
    public abstract void updateInvaders();
    public abstract boolean bulletHitsInvader(PlayerBullet b);
    public abstract boolean invaderHitPlayer(Player p);
}
