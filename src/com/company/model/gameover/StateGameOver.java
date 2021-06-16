package com.company.model.gameover;

import com.company.model.Model;
import com.company.model.State;
import com.company.model.game.StateGame;

import java.awt.event.KeyEvent;

public class StateGameOver extends State {

    public StateGameOver(Model model) {
        super(model);
        System.out.println("state game over");
    }

    @Override
    public void stateUpdate(Model model) {
        parseKey();
    }

    @Override
    public void parseKey() {
        if (keys.isPressed(KeyEvent.VK_Q)) {
            System.exit(0);
        }
        if (keys.isPressed(KeyEvent.VK_R)) {
            model.setCurrentState(new StateGame(model));
        }
    }
}

