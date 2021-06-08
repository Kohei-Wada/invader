package com.company.model.game;

import com.company.model.Model;
import com.company.model.State;
import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;
import com.company.model.gameover.StateGameOver;
import com.company.ui.Keys;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class StateGame extends State {

    private final ArrayList<SGObserver> observers;
    private Player player = null;

    public StateGame(Model m) {
        super(m);
        stateActive = true;
        observers = new ArrayList<>();
        player = new Player(this);
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
        if (stateActive) {
            try {
                parseKey();
                Thread.sleep(100);
                observers.forEach(v -> v.updateSGO(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void parseKey() {
        if (keys.isPressed(KeyEvent.VK_Q)) System.out.println("hello world");

    }
}
