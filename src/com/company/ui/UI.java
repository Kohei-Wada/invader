package com.company.ui;

import com.company.IGObserver;
import com.company.InvaderGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI extends JFrame implements IGObserver {
    private static UI ui = null;

    private final int wid = 500;
    private final int hgt = 500;
    private final Keys keys;
    private Graphics g;
    private ScreenPanel panel;

    public static UI getUi() {
        if (ui == null) {
            ui = new UI();
        }
        return ui;
    }

    private UI() {
        keys = new Keys();
        addKeyListener(keys);
        initUI();
    }

    private void initUI() {
        setSize(wid, hgt);
        setTitle("Invader");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        panel = new ScreenPanel(wid, hgt);
        add(panel);

        g = panel.graphic();

    }

    public Keys getKeys() {
        return keys;
    }

    public int getWid() {
        return wid;
    }

    public int getHgt() {
        return hgt;
    }

    public Graphics graphic() {
        return g;
    }

    public void clearUI() {
        g.setColor(Color.black);
        g.fillRect(0, 0, wid, hgt);
    }

    @Override
    public void observerUpdate() {
        clearUI();
        panel.draw();
    }
}

class ScreenPanel extends JPanel {

    private BufferedImage image;

    public ScreenPanel(int wid, int hgt) {
        super();
        image = new BufferedImage(wid, hgt, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }

    public void draw() {
        this.repaint();
    }

    public Graphics graphic() {
        return image.getGraphics();
    }

}