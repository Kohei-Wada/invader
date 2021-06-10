package com.company.model.game.invaders;

import com.company.model.game.bullets.PlayerBullet;

public abstract class Invaders {
    public abstract void updateInvaders();
    public abstract boolean invaderIsDead(PlayerBullet b);
}
