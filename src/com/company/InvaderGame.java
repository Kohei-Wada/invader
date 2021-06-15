package com.company;

import com.company.model.Model;
import com.company.ui.UI;

import java.util.ArrayList;

public class InvaderGame {
    //Singleton

    private static InvaderGame game = new InvaderGame();
    private final ArrayList<IGObserver> observers;
    private boolean active = false;
    private final int fps = 60;

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

    public synchronized void run() {

        while (active) {
            observers.forEach(IGObserver::observerUpdate);
            try {
                wait(1000/ fps);
            }
            catch (Exception e) {
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
