package com.company.model.game.player;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;

public class Player implements SGObserver {

    public Player(StateGame sg) {
        sg.addObserver(this);

    }

    @Override
    public void updateSGO(StateGame sg) {
        //System.out.println("player update");
    }
}
