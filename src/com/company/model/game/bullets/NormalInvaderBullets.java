package com.company.model.game.bullets;

import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.awt.*;
import java.util.LinkedList;

public class NormalInvaderBullets extends LinkedList<NormalInvaderBullet> implements Bullets{

    private final int  stageY;

    public NormalInvaderBullets() {
        stageY = UI.getUi().getHgt();
    }
    @Override
    public void updateBullets() {
        forEach(NormalInvaderBullet::updateBullet);
        deleteBullets();
    }

    @Override
    public boolean bulletsHitPlayer(Player p) {
        return stream().anyMatch(b -> b.hitsPlayer(p));
    }

    public void addNormalInvaderBullet(int x, int y) {
        add(new NormalInvaderBullet(x, y));
    }

    private void deleteBullets() {
        this.removeIf(bullet -> bullet.getY() > stageY);
    }
}


class NormalInvaderBullet extends Bullet {

    public NormalInvaderBullet(int x, int y) {
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
