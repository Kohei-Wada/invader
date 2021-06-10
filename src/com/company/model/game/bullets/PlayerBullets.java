package com.company.model.game.bullets;

import com.company.model.game.invaders.InvadersManager;

import javax.swing.*;
import java.util.LinkedList;

public class PlayerBullets extends LinkedList<PlayerBullet> implements Bullets{

    private final InvadersManager manager;

    public PlayerBullets(InvadersManager m) {
        manager = m;
    }

    public void checkInvaderIsDead() {
        for (PlayerBullet b : this) {
            if (manager.invaderIsDead(b)) {
                remove(b);
                break;
            }
        }
    }

    @Override
    public void updateBullets() {
        checkInvaderIsDead();
        forEach(PlayerBullet::updateBullet);
    }

    public void addPlayerBullet(int x, int y) {
        add(new PlayerBullet(x, y));
    }
}


