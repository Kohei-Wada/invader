package com.company.model;

import com.company.ui.Keys;

public abstract class State {
    protected Keys  keys;
    protected Model model;

    public State(Model m) {
        model = m;
        keys = model.getKeys();
    }

    public void setKeys(Keys k) {
        keys = k;
    }

    public abstract void stateUpdate(Model model);

    public abstract void parseKey();
}
