package com.company;

import java.util.ArrayList;

public class InvaderGame {
    private static InvaderGame game = new InvaderGame();
    private ArrayList<IGObserver> observers = null;
    private boolean active = false;

    public static InvaderGame getGame() {
        if (game == null)
            game = new InvaderGame();
        return  game;
    }

    private InvaderGame() {
        observers = new ArrayList<>();
        gameStart();
    }

    public void addObserver(IGObserver o) {
        observers.add(o);
    }

    public void removeObserver(IGObserver o) {
        observers.remove(o);
    }

    public void run() {
        while (active) {
            observers.forEach(IGObserver::observerUpdate);
        }
    }

    public void gameStart() {
        active = true;
    }

    public void gameStop() {
        active = false;
    }


}
