package com.company.model.game;

import com.company.model.Model;
import com.company.model.State;
import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;
import com.company.model.gameover.StateGameOver;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class StateGame extends State {

    private final ArrayList<SGObserver> observers;


    public StateGame(Model m) {
        super(m);
        observers = new ArrayList<>();

        new Player(this);
        new InvadersManager(this);
    }

    public void addObserver(SGObserver sgo) {
        observers.add(sgo);
    }

    public void removeObserver(SGObserver sgo) {
        observers.remove(sgo);
    }

    @Override
    public void stateUpdate(Model model) {
        parseKey();
        observers.forEach(v -> v.updateSGO(this));
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseKey() {
        if (keys.isPressed(KeyEvent.VK_Q)) {
            setStateActive(false);

            model.setCurrentState(new StateGameOver(model));
        }

    }
}
