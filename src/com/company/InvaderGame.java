package com.company;

import com.company.model.Model;
import com.company.ui.UI;

import java.util.ArrayList;

public class InvaderGame {
    private static InvaderGame game = new InvaderGame();
    private ArrayList<IGObserver> observers = null;
    private boolean active = false;

    private Model model;
    private UI ui;

    public static InvaderGame getGame() {
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

    public void removeObserver(IGObserver o) {
        observers.remove(o);
    }

    public void run() {
        while (active) {
            observers.forEach(IGObserver::observerUpdate);
        }
    }

    public void gameInit() {
        model = Model.getModel();
        ui = UI.getUi();
        model.setKeys(ui.getKeys());
        active = true;
    }
}
