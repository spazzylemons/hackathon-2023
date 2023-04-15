package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public record Planet(double x, double y, double radius, boolean isGoal) {
    private static final Image planetImage = RenderUtils.getImage("planet.png");

    public void render(Graphics g) {
    	if(isGoal) {
    		g.setColor(Color.YELLOW);
            RenderUtils.renderCircle(g, x, y, radius);
    	}
    	else {
            g.drawImage(planetImage, (int) Math.round(x - radius), (int) Math.round(y - radius), (int) Math.round(2.0 * radius), (int) Math.round(2.0 * radius), null);
    	}
    }

    public double volume() {
        return radius * radius * radius * Math.PI * (4.0 / 3.0);
    }
}
