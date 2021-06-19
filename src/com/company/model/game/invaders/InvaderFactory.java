package com.company.model.game.invaders;

import com.company.model.game.bullets.*;


public class InvaderFactory {

    private final IBFactory      bulletFactory;
    private final BulletsManager bulletsManager;

    private final Bullets        normalBullets;
    private final Bullets        shotgunBullets;



    public InvaderFactory(BulletsManager manager) {
        bulletsManager = manager;
        bulletFactory  = new IBFactory();

        normalBullets  = bulletFactory.create(BulletTypes.NORMAL);
        shotgunBullets = bulletFactory.create(BulletTypes.SHOTGUN);

        bulletsManager.addBullets(normalBullets);
        bulletsManager.addBullets(shotgunBullets);
    }

    public Invader create(InvaderTypes itype, BulletTypes btype, int x, int y) {

        Bullets bullets;

        switch (btype) {
            case NORMAL -> {
                bullets = normalBullets;
            }
            case SHOTGUN -> {
                bullets = shotgunBullets;
            }
            default -> throw new IllegalStateException("Unexpected value: " + btype);
        }

        return new NormalInvader(x , y, bullets);
    }
}
