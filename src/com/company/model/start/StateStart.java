package com.company.model.start;

import com.company.model.Model;
import com.company.model.State;
import com.company.model.game.StateGame;

import java.awt.event.KeyEvent;

public class StateStart extends State {
    public StateStart(Model m) {
        super(m);
        System.out.println("state start");
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
        if (keys.isPressed(KeyEvent.VK_SPACE)) {
            model.setCurrentState(new StateGame(model));
        }
    }
}
