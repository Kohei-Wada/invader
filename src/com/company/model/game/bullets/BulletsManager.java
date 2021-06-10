package com.company.model.game.bullets;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;

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

    @Override
    public void updateSGO(StateGame sg) {
        allBullets.forEach(Bullets::updateBullets);
    }
}