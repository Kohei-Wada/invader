package com.company.model.game;

import com.company.model.Model;
import com.company.model.Result;
import com.company.model.State;
import com.company.model.game.bullets.BulletsManager;
import com.company.model.game.invaders.InvadersManager;
import com.company.model.game.player.Player;
import com.company.model.gameover.StateGameOver;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class StateGame extends State {

    private final ArrayList<SGObserver> observers;
    private final Player player;
    private final BulletsManager bulletsManager;
    private final InvadersManager invadersManager;

    public StateGame(Model m) {
        super(m);

        System.out.println("state game");
        observers = new ArrayList<>();

        /*
        program will die if you make a mistake
        in the initialization order
         */
        bulletsManager = new BulletsManager(this);
        invadersManager = new InvadersManager(this);
        player = new Player(this);
    }

    public InvadersManager getInvadersManager() {
        return invadersManager;
    }

    public BulletsManager getBulletsManager() {
        return bulletsManager;
    }

    public void addObserver(SGObserver sgo) {
        observers.add(sgo);
    }

    @Override
    public void stateUpdate(Model model) {

        if (!player.isDead()) {
            parseKey();
            observers.forEach(v -> v.updateSGO(this));
        }
        else {
            Result result = new Result();
            observers.forEach(v -> v.getResult(result));
            model.setResult(result);
            model.setCurrentState(new StateGameOver(model));
        }

        try {
            Thread.sleep(33);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void parseKey() {
        if (keys.isPressed(KeyEvent.VK_LEFT))
            player.addX(-8);
        if (keys.isPressed(KeyEvent.VK_RIGHT))
            player.addX(8);
        if (keys.isPressed(KeyEvent.VK_UP))
            player.addY(-8);
        if (keys.isPressed(KeyEvent.VK_DOWN))
            player.addY(8);
        if (keys.isPressed(KeyEvent.VK_SPACE))
            player.firingBullet();
    }
}
