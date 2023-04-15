package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Canvas extends JPanel implements ActionListener {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;

    public final GolfBall ball = new GolfBall();
    public Planet[] planets = new Planet[0];

    public Canvas() {
        super();
        setSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        grabFocus();
        generateLevel();
        new Timer(16, this).start();
    }

    private void generateLevel() {
        var newPlanets = new Planet[8];
        var i = 0;
        for (var x = 0; x < 4; x++) {
            for (var y = 0; y < 2; y++) {
                var xPos = (x * 200.0) + (Math.random() * 100.0) + 50.0;
                var yPos = (y * 200.0) + (Math.random() * 100.0) + 50.0;
                var radius = 20.0 * Math.random() + 20.0;
                newPlanets[i++] = new Planet(xPos, yPos, radius);
            }
        }
        planets = newPlanets;
    }

    private void updateCanvas() {
        ball.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (var planet : planets) {
            planet.render(g);
        }
        ball.render(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateCanvas();
        repaint();
    }
}
