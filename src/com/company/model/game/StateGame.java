package com.company.model.game;

import com.company.model.Model;
import com.company.model.State;

import java.util.concurrent.CompletionStage;

public class StateGame extends State {


    public StateGame() {
    }

    @Override
    public void stateUpdate(Model model) {
        System.out.println("inside game update");
    }
}
