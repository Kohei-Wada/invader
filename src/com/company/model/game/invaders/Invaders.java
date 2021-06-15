package com.company.model.game.invaders;

import com.company.model.game.bullets.Bullet;
import com.company.model.game.player.Player;

public interface Invaders {
    void updateInvaders();

    //this method is called to see if a bullet was hit
    boolean bulletHitsInvader(Bullet b);

    //this method checks if an invader collides with a player
    boolean invaderHitPlayer(Player p);
}
