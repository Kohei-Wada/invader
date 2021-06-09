package com.company.model.game.player;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;
import com.company.ui.UI;

import java.awt.*;

public class Player implements SGObserver {
    private int x, y;

    private final Graphics g;
    private final int stageX, stageY;


    public Player(StateGame sg) {
        x = 0; y = 0;
        sg.addObserver(this);
        g = UI.getUi().graphic();
        stageX = UI.getUi().getWid();
        stageY = UI.getUi().getHgt();
    }

    public void addX(int n) {
        if (x + n > 0 && x + n < stageX)
        x += n;
    }

    public void addY(int n) {
        if (y + n > 0 && y + n < stageY)
        y += n;
    }

    private void drawPlayer() {
        g.setColor(Color.blue);
        g.fillRect(x + 10, y, 10, 10);
        g.fillRect(x, y + 10, 30, 10);
    }

    @Override
    public void updateSGO(StateGame sg) {
        drawPlayer();
    }
}
