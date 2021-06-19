package com.company.model.game.invaders;

import com.company.model.game.SGObserver;
import com.company.model.game.StateGame;
import com.company.model.game.bullets.*;
import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.util.LinkedList;
import java.util.Random;

public class InvadersManager implements SGObserver {

    private final LinkedList<Invader> invaders;
    private final Random              random;
    private final int                 sizeX;
    private final int                 sizeY;
    private final InvaderFactory      factory;


    public InvadersManager(StateGame sg) {
        sg.addObserver(this);

        invaders = new LinkedList<>();
        random = new Random();

        factory = new InvaderFactory(sg.getBulletsManager());

        sizeX = UI.getUi().getWid();
        sizeY = UI.getUi().getHgt();

    }

    public boolean invaderHitsPlayer(Player p) {
        return invaders.stream().anyMatch(i -> i.hitPlayer(p));
    }

    public boolean bulletHitsInvader(Bullet b) {
        return invaders.stream().anyMatch(i -> i.bulletHitInvader(b));
    }

    private void addInvader() {
        if (random.nextInt(40) == 1) {
            invaders.add(factory.create(InvaderTypes.NORMAl, BulletTypes.SHOTGUN, random.nextInt(sizeX - 30), 0));
        }
    }

    @Override
    public void updateSGO(StateGame sg) {
        addInvader();
        invaders.forEach(Invader::updateInvader);
        invaders.removeIf(Invader::isDead);
        invaders.removeIf(i -> i.getY() > sizeY);
    }
}
