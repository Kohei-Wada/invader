package com.company;

public class Main {

    public static void main(String[] args) {
        InvaderGame game = InvaderGame.getGame();
        UI ui = UI.getUi();
        Model model = Model.getModel();

        game.addObserver(ui);
        game.addObserver(model);

        game.run();


    }
}
