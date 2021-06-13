package com.company.model.game.bullets;

import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;

import java.util.LinkedList;

public class PlayerBullets extends LinkedList<PlayerBullet> implements Bullets{

    private final InvadersManager manager;

    public PlayerBullets(InvadersManager m) {
        manager = m;
    }

    public void checkBulletHitsInvader() {
        for (PlayerBullet b : this) {
            if (manager.bulletHistInvader(b)) {
                remove(b);
                break;
            }
        }
    }

    private void deleteBullets() {
        removeIf(bullet -> bullet.getY() < 0);
    }

    @Override
    public void updateBullets() {
        checkBulletHitsInvader();
        forEach(PlayerBullet::updateBullet);
        deleteBullets();
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


