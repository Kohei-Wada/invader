package com.company.model.game.invaders;

import com.company.InvaderGame;
import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;

public class InvadersManager implements SGObserver {

    public InvadersManager(StateGame sg) {
        sg.addObserver(this);
    }

    @Override
    public void updateSGO(StateGame sg) {
        System.out.println("invaders manager update");
    }
}
