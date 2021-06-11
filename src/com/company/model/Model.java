package com.company.model;

import com.company.IGObserver;
import com.company.InvaderGame;
import com.company.model.game.StateGame;
import com.company.model.gameover.StateGameOver;
import com.company.model.start.StateStart;
import com.company.ui.Keys;

import java.security.Key;

public class Model implements IGObserver {

    private static Model model = new Model();
    private State state;
    private  Result result;
    private Keys keys;

    public static Model getModel() {
        if (model == null) {
            model = new Model();
        }
        return  model;
    }

    private Model() {
        setCurrentState(new StateStart(this));
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

    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys k) {
        keys = k;
        state.setKeys(k);
    }

    @Override
    public void observerUpdate() {
        state.stateUpdate(model);
    }
}
