package com.company.model.game.bullets;

import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;

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

    /*
    delete bullets that have gone off the screen
     */
    private void deleteBullets() {
        for (int i = 0; i < this.size(); ++i) {
            PlayerBullet b = this.get(i);
            if (b.getY() < 0) {
                remove(b);
                --i;
            }
        }
    }

    @Override
    public void updateBullets() {
        checkInvaderIsDead();
        forEach(PlayerBullet::updateBullet);
        deleteBullets();
    }

    //Never used method
    @Override
    public boolean bulletHitPlayer(Player p) {
        return false;
    }

    public void addPlayerBullet(int x, int y) {
        add(new PlayerBullet(x, y, this));
    }
}


