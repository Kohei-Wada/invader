package com.company.ui;

import com.company.IGObserver;
import com.company.InvaderGame;

import javax.swing.*;

public class UI extends JFrame implements IGObserver {
    private static UI ui = null;

    private InvaderGame game = null;
    private final int wid = 500;
    private final int hgt = 500;


    public static UI getUi() {
        if (ui == null) {
            ui = new UI();
        }
        return ui;
    }


    private UI() {
        game = InvaderGame.getGame();
        initUI();

    }

    private void initUI() {
        setSize(wid, hgt);
        setTitle("Invader");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }


    @Override
    public void observerUpdate() {

    }
}
