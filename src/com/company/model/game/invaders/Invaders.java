package com.company.model.game.invaders;

import com.company.model.game.bullets.Bullet;
import com.company.model.game.player.Player;
import com.company.ui.UI;

import java.util.LinkedList;
import java.util.Random;

public abstract class Invaders extends LinkedList<Invader> {

    protected final int sizeX;
    protected final int sizeY;
    protected final Random random;

    public Invaders() {
        sizeX = UI.getUi().getWid();
        sizeY = UI.getUi().getHgt();
        random = new Random();
    }

    abstract void updateInvaders();

    //this method is called to see if a bullet was hit
    abstract boolean bulletHitsInvader(Bullet b);

    //this method checks if an invader collides with a player
    abstract boolean invaderHitPlayer(Player p);
}
