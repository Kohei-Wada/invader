package com.company.model.game.bullets;

import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;
import java.util.LinkedList;

public class NormalBullets extends LinkedList<NormalBullet> implements Bullets{

    private final int  stageY;

    public NormalBullets() {
        stageY = UI.getUi().getHgt();
    }
    @Override
    public void updateBullets() {
        forEach(NormalBullet::updateBullet);
        removeIf(b -> b.getY() > stageY);
    }

    @Override
    public boolean bulletsHitPlayer(Player p) {
        return stream().anyMatch(b -> b.hitsPlayer(p));
    }
    public void addNormalBullet(int x, int y) {
        add(new NormalBullet(x, y));
    }
}


class NormalBullet extends Bullet {

    public NormalBullet(int x, int y) {
        super(x, y);
    }

    @Override
    public void drawBullet() {
        g.setColor(Color.red);
        g.fillRect(x, y, 5, 5);
    }

    @Override
    public void updateBullet() {
        drawBullet();
        y += 10;
    }

    @Override
    public boolean hitsPlayer(Player p) {
        int px = p.getX();
        int py = p.getY();
        return x >= px && x <= px + 30 && y >= py && y <= py + 20;
    }
}
