package com.company;

import com.company.model.Model;
import com.company.ui.UI;

public class Main {
    public static void main(String[] args) {
        InvaderGame game = InvaderGame.getGame();
        game.addObserver(UI.getUi());
        game.addObserver(Model.getModel());
        game.run();
    }
}
