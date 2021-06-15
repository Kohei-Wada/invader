package com.company.model.game.invaders;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;
import com.company.model.game.bullets.Bullet;
import com.company.model.game.bullets.BulletsManager;
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
        return allInvaders.stream().anyMatch(i -> i.invaderHitPlayer(p));
    }

    public boolean bulletHistInvader(Bullet b) {
        return allInvaders.stream().anyMatch(i -> i.bulletHitsInvader(b));
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
}
