package com.company.model.game.invaders;

import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;

public abstract class Invaders {
    public abstract void updateInvaders();

    //this method is called to see if a bullet was hit
    public abstract boolean bulletHitsInvader(PlayerBullet b);

    //this method checks if an invader collides with a player
    public abstract boolean invaderHitPlayer(Player p);
}
