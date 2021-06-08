package com.company.model;

import com.company.IGObserver;

public class Model implements IGObserver {

    private static Model model = null;

    public static Model getModel() {
        if (model == null) {
            model = new Model();
        }
        return  model;
    }

    private Model() {

    }

    @Override
    public void observerUpdate() {

    }
}
