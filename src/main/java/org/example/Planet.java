package org.example;

import java.awt.Color;
import java.awt.Graphics;

public record Planet(double x, double y, double radius) {
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        RenderUtils.renderCircle(g, x, y, radius);
    }

    public double volume() {
        return radius * radius * radius * Math.PI * (4.0 / 3.0);
    }
}
