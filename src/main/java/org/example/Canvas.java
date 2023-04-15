package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Canvas extends JPanel implements ActionListener, MouseListener {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;

    public final GolfBall ball = new GolfBall();
    public Planet[] planets = new Planet[0];
    public boolean mouseDown = false;
    public boolean lastMouseDown = false;
    public Canvas() {
        super();
        setSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        grabFocus();
        generateLevel();
        new Timer(16, this).start();
        this.addMouseListener(this);
    }

    private void generateLevel() {
    	ball.resetBall();
        var newPlanets = new Planet[8];
        var i = 0;
        for (var x = 0; x < 4; x++) {
            for (var y = 0; y < 2; y++) {
                var xPos = (x * 200.0) + (Math.random() * 100.0) + 50.0;
                var yPos = (y * 200.0) + (Math.random() * 100.0) + 50.0;
                var radius = 20.0 * Math.random() + 20.0;
                newPlanets[i++] = new Planet(xPos, yPos, radius, x==3&&y==1);
            }
        }
        planets = newPlanets;
        ball.x = planets[0].x();
        ball.y = planets[0].y() - planets[0].radius();
        
    }

    private void updateCanvas() {
    	if(ball.hitGoal) {
    		System.out.println("You Did It!");
    		generateLevel();
    		return;
    	}
    	else if (ball.numCollideFrames >= GolfBall.COLLIDE_THRESHOLD) {
            var point = getMousePosition();
            if (point != null) {
                if (!mouseDown && lastMouseDown) {
                    ball.vx = (point.x - (getWidth() / 2)) * 0.1;
                    ball.vy = (point.y - (getHeight() / 2)) * 0.1;
                    ball.numCollideFrames = 0;
                }
            }
        } else {
            ball.update(planets);
        }
        lastMouseDown = mouseDown;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var point = getMousePosition();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (mouseDown && ball.numCollideFrames >= GolfBall.COLLIDE_THRESHOLD && point != null) {
            g.setColor(Color.WHITE);
            g.drawLine(getWidth() / 2, getHeight() / 2, point.x, point.y);
        }
        g.translate((getWidth() / 2) - (int) Math.round(ball.x), (getHeight() / 2) - (int) Math.round(ball.y));
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

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = false;
        }
    }
}
