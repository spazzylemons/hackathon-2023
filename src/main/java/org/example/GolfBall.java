package org.example;

import java.awt.Color;
import java.awt.Graphics;

public class GolfBall {
    public static final double RADIUS = 5.0;
    public double x = 10.0;
    public double y = 10.0;

    public void update() {}

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        RenderUtils.renderCircle(g, x, y, RADIUS);
    }
}
