package com.company.model;

import com.company.IGObserver;
import com.company.InvaderGame;
import com.company.model.game.StateGame;
import com.company.model.gameover.StateGameOver;

public class Model implements IGObserver {

    private static Model model = new Model();
    private State state;
    private  Result result;

    public static Model getModel() {
        if (model == null) {
            model = new Model();
        }
        return  model;
    }

    private Model() {
        state = new StateGame();
    }

    public State getCurrentState() {
        return state;
    }

    public void setCurrentState(State s) {
        state = s;
    }

    public void setResult(Result r) {
        result = r;
    }

    public Result getResult(Result r) {
        return r;
    }

    @Override
    public void observerUpdate() {
        state.stateUpdate(model);
    }
}
