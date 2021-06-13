package com.company;

import com.company.model.Model;
import com.company.ui.UI;

import java.util.ArrayList;

public class InvaderGame {
    private static InvaderGame game = new InvaderGame();
    private final ArrayList<IGObserver> observers;
    private boolean active = false;

    public synchronized static InvaderGame getGame() {
        if (game == null)
            game = new InvaderGame();
        return  game;
    }

    private InvaderGame() {
        observers = new ArrayList<>();
        gameInit();
    }

    public void addObserver(IGObserver o) {
        observers.add(o);
    }

    //TODO program IGObservers as thread
    //TODO add FPS code
    public synchronized void run() {
        while (active) {
            observers.forEach(IGObserver::observerUpdate);
            try {
                wait(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void gameInit() {
        Model model = Model.getModel();
        UI ui = UI.getUi();
        model.setKeys(ui.getKeys());
        active = true;
    }
}
