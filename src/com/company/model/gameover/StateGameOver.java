package com.company.model.gameover;

import com.company.model.Model;
import com.company.model.State;

import java.awt.event.KeyEvent;

public class StateGameOver extends State {


    public StateGameOver(Model model) {
        super(model);
    }

    @Override
    public void stateUpdate(Model model) {
        parseKey();

        try {
            Thread.sleep(33);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseKey() {
        if (keys.isPressed(KeyEvent.VK_Q)) {
            System.exit(0);
        }
    }
}

