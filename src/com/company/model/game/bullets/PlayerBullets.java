package com.company.model.game.bullets;

import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;

import java.util.LinkedList;

public class PlayerBullets extends LinkedList<PlayerBullet> implements Bullets{

    private final InvadersManager manager;

    public PlayerBullets(InvadersManager m) {
        manager = m;
    }

    @Override
    public void updateBullets() {
        forEach(PlayerBullet::updateBullet);

        //remove bullets that has hit to invader
        removeIf(manager::bulletHistInvader);

        //remove bullets that has gone off the stage
        removeIf(b -> b.getY() < 0);
    }

    //Never used method
    @Override
    public boolean bulletsHitPlayer(Player p) {
        return false;
    }

    public void addPlayerBullet(int x, int y) {
        add(new PlayerBullet(x, y));
    }
}


