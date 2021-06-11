package com.company.model.game.invaders;

import com.company.model.Result;
import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;
import com.company.model.game.bullets.BulletsManager;
import com.company.model.game.bullets.PlayerBullet;
import com.company.model.game.player.Player;

import java.util.ArrayList;

public class InvadersManager implements SGObserver {

    private final ArrayList<Invaders> allInvaders;
    private final BulletsManager bulletsManager;

    public InvadersManager(StateGame sg) {
        sg.addObserver(this);
        allInvaders = new ArrayList<>();
        bulletsManager = sg.getBulletsManager();

        initAllInvaders();
    }

    private void initAllInvaders() {
        new NormalInvaders(this);
    }

    public boolean invaderHitsPlayer(Player p) {
        for (Invaders i : allInvaders) {
            if (i.invaderHitPlayer(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean bulletHistInvader(PlayerBullet b) {
        for (Invaders i : allInvaders) {
            if (i.bulletHitsInvader(b)) {
                return true;
            }
        }
        return false;
    }

    public void addInvaders(Invaders i) {
        allInvaders.add(i);
    }

    public BulletsManager getBulletsManager() {
        return bulletsManager;
    }

    @Override
    public void updateSGO(StateGame sg) {
        allInvaders.forEach(Invaders::updateInvaders);
    }

    @Override
    public void getResult(Result result) {

    }
}
