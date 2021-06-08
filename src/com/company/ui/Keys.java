package com.company.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;

public class Keys extends KeyAdapter {

    private final ArrayList<Integer> keys;

    public Keys() {
        keys = new ArrayList<>();
    }

    public boolean isPressed(int key) {
        return keys.contains(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        //System.out.println("pressed");
        Integer key = e.getKeyCode();
        if (!keys.contains(key))
            keys.add(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        //System.out.println("released");
        Integer key = e.getKeyCode();
        keys.remove(key);
    }
}
