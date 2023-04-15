package org.example;

import java.awt.Color;
import java.awt.Graphics;

public class GolfBall {
    public static final double RADIUS = 5.0;
    public static final double GRAVITY = 0.04;
    public double x = 10.0;
    public double y = 10.0;
    public double vx = 0.0;
    public double vy = 0.0;

    public void update(Planet[] planets) {
        var gravityX = 0.0;
        var gravityY = 0.0;
        for (var planet : planets) {
            var dx = planet.x() - x;
            var dy = planet.y() - y;
            var rr = (dx * dx) + (dy * dy);
            var r = Math.sqrt(rr);
            dx /= r;
            dy /= r;
            var force = Math.max((GRAVITY * planet.volume()) / rr, GRAVITY);
            if (force < 10.0f) {
                gravityX += dx * force;
                gravityY += dy * force;
            }
        }
        vx += gravityX;
        vy += gravityY;
        x += vx;
        y += vy;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        RenderUtils.renderCircle(g, x, y, RADIUS);
    }
}
