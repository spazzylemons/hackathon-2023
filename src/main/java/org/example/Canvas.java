package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;


public final class Canvas extends JPanel implements ActionListener, MouseListener {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;

    public final GolfBall ball = new GolfBall();
    public Planet[] planets = new Planet[0];
    public boolean mouseDown = false;
    public boolean lastMouseDown = false;
    public int hits = 0;
    public int levelsCompleted = 0;
    public Font text = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    private static final Image IMAGE = RenderUtils.getImage("bg.png");
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
        var i = 0;
//        System.out.println("Level " + (levelsCompleted+1));
        hits = 0;
        planets = LevelBuilder.createLevel(8);
        ball.x = planets[0].x();
        ball.y = planets[0].y() - planets[0].radius();
        
    }

    private void updateCanvas() {
    	if(ball.hitGoal) {
//    		System.out.println("You Did It!");
    		levelsCompleted++;
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
                    hits++;
                }
            }
        } else {
            ball.update(planets);
        }
        lastMouseDown = mouseDown;
    }

    private void renderParallax(Graphics g, double scale, int addX, int addY) {
        var imgWidth = IMAGE.getWidth(null);
        var imgHeight = IMAGE.getHeight(null);
        var offsetX = Math.floorMod(addX + (getWidth() / 2) - (int) Math.round(ball.x * scale), IMAGE.getWidth(null)) - imgWidth;
        var offsetY = Math.floorMod(addY + (getHeight() / 2) - (int) Math.round(ball.y * scale), IMAGE.getHeight(null)) - imgHeight;
        for (var y = offsetY; y < getHeight(); y += imgHeight) {
            for (var x = offsetX; x < getWidth(); x += imgWidth) {
                g.drawImage(IMAGE, x, y, imgWidth, imgHeight, null);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
    	g.setFont(text);
        super.paintComponent(g);
        var point = getMousePosition();
//        g.drawRect(0, 0, 12, 13);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        renderParallax(g, 0.25, 10, 50);
        renderParallax(g, 0.175, 70, 70);
        renderParallax(g, 0.125, 80, 200);
        g.setColor(Color.RED);
        g.drawString("Level " + (levelsCompleted+1), 10, 12);
        if (mouseDown && ball.numCollideFrames >= GolfBall.COLLIDE_THRESHOLD && point != null) {
            g.setColor(Color.WHITE);
            g.drawLine(getWidth() / 2, getHeight() / 2, point.x, point.y);
        }
        g.drawString("Hits "+(hits), 10, 25);
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
