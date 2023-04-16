package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Planet {
    private double angle;
    private final double speed;
    private final double distance;
    private final double radius;
    private final boolean isGoal;

    public Planet(double angle, double speed, double distance, double radius, boolean isGoal) {
        this.angle = angle;
        this.speed = speed;
        this.distance = distance;
        this.radius = radius;
        this.isGoal = isGoal;
    }

    private static final Image[] PLANET_IMAGES = new Image[24];
    private static final Image SUN_IMAGE = RenderUtils.getImage("sun.png");

    static {
        for (var i = 0; i < 24; i++) {
            PLANET_IMAGES[i] = RenderUtils.getImage("planet/" + String.format("%04d", i + 1) + ".png");
        }
    }

    public void render(Graphics g) {
        var x = this.x();
        var y = this.y();
    	if(isGoal) {
            g.drawImage(SUN_IMAGE, (int) Math.round(x - radius), (int) Math.round(y - radius), (int) Math.round(2.0 * radius), (int) Math.round(2.0 * radius), null);
    	}
    	else {
            g.drawImage(PLANET_IMAGES[((int) Math.round(this.angle / (2.0 * Math.PI) * 24.0) + 6) % 24], (int) Math.round(x - radius), (int) Math.round(y - radius), (int) Math.round(2.0 * radius), (int) Math.round(2.0 * radius), null);
    	}
    }

    public void update() {
        this.angle = (this.angle + this.speed + (2.0 * Math.PI)) % (2.0 * Math.PI);
    }

    public double volume() {
        return radius * radius * radius * Math.PI * (4.0 / 3.0);
    }

    public double x() {
        return Math.cos(this.angle) * this.distance;
    }

    public double y() {
        return Math.sin(this.angle) * this.distance;
    }

    public double radius() {
        return this.radius;
    }

    public boolean isGoal() {
        return this.isGoal;
    }
}
