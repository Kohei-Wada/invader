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
    public boolean bulletHitPlayer(Player p) {
        for (NormalInvaderBullet b : this) {
            if (b.hitPlayer(p)) {
                return true;
            }
        }
        return false;
    }

    public void addNormalInvaderBullet(int x, int y) {
        add(new NormalInvaderBullet(x, y));
    }

    private void deleteBullets() {
        for (int i = 0; i < size(); ++i) {
            NormalInvaderBullet b = get(i);
            if (b.getY() > stageY) {
                remove(b);
                --i;
            }
        }
    }
}



class NormalInvaderBullet extends Bullet {
    private final Graphics g;

    public NormalInvaderBullet(int x, int y) {
        super(x, y);
        g = UI.getUi().graphic();
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
    public boolean hitPlayer(Player p) {
        int px = p.getX();
        int py = p.getY();

        return x >= px && x <= px + 30 && y >= py && y <= py + 20;
    }
}
