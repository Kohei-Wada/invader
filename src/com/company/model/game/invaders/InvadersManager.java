package com.company.model.game.invaders;

import com.company.InvaderGame;
import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;

import java.util.ArrayList;

public class InvadersManager implements SGObserver {

    private final ArrayList<Invaders> allInvaders;

    public InvadersManager(StateGame sg) {
        sg.addObserver(this);
        allInvaders = new ArrayList<>();

        addInvaders(new NormalInvaders());
    }

    private void addInvaders(Invaders i) {
        allInvaders.add(i);
    }

    @Override
    public void updateSGO(StateGame sg) {
        allInvaders.forEach(Invaders::updateInvaders);
    }
}
