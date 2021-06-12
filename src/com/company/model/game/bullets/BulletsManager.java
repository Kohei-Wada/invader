package com.company.model.game.bullets;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;
import com.company.model.game.player.Player;

import java.util.ArrayList;


public class BulletsManager implements SGObserver {
    private final ArrayList<Bullets> allBullets;

    public BulletsManager(StateGame sg) {
        sg.addObserver(this);
        allBullets = new ArrayList<>();
    }

    public void addBullets(Bullets b) {
        allBullets.add(b);
    }

    public boolean bulletHitsPlayer(Player p) {
        return allBullets.stream().anyMatch(v -> v.bulletHitPlayer(p));
    }

    @Override
    public void updateSGO(StateGame sg) {
        allBullets.forEach(Bullets::updateBullets);
    }

}
