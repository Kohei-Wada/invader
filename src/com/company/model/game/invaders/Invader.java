package com.company.model.game.invaders;

public abstract class Invader {
    protected int x, y;
    public abstract void updateInvader();
    public abstract void drawInvader();
}
