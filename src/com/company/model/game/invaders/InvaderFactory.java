package com.company.model.game.invaders;

import com.company.model.game.bullets.BulletTypes;
import com.company.model.game.bullets.Bullets;
import com.company.model.game.bullets.BulletsManager;
import com.company.model.game.bullets.IBFactory;


public class InvaderFactory {

    private final IBFactory bulletFactory;
    private final Bullets normalBullets;


    private final BulletsManager bulletsManager;

    public InvaderFactory(BulletsManager manager) {
        bulletsManager = manager;
        bulletFactory = new IBFactory();

        normalBullets = bulletFactory.create(BulletTypes.NORMAL);
        bulletsManager.addBullets(normalBullets);

    }

    public Invader create(int x, int y) {
        return new NormalInvader(x , y, normalBullets);
    }
}
