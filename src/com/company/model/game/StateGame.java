package com.company.model.game;

import com.company.model.Model;
import com.company.model.State;
import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;
import com.company.model.gameover.StateGameOver;

import java.util.ArrayList;
import java.util.concurrent.CompletionStage;

public class StateGame extends State {

    private final ArrayList<SGObserver> observers;


    public StateGame() {
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
        try {
            Thread.sleep(100);
            observers.forEach(v -> v.updateSGO(this));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
