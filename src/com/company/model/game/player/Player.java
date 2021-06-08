package com.company.model.game.player;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;

public class Player implements SGObserver {
    private int x, y;

    public Player(StateGame sg) {
        x = 0; y = 0;
        sg.addObserver(this);
    }

    private void addX(int n) {
        x += n;
    }

    private void addY(int n) {
        y += n;
    }

    @Override
    public void updateSGO(StateGame sg) {
        //System.out.println("player update");
    }
}
