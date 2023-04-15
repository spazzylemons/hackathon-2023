package org.example;

import java.awt.Color;
import java.awt.Graphics;

public class GolfBall {
    public static final double RADIUS = 5.0;
    public static final double GRAVITY = 0.005;
    public double x = -50.0;
    public double y = -50.0;
    public double vx = 0.0;
    public double vy = 0.0;

    public int numCollideFrames = 0;
    public static final int COLLIDE_THRESHOLD = 30;
    public boolean hitGoal = false;

    public static final double GRAVITY_BOOST = 50.0;
    public static final double GRAVITY_BOOST_DISTANCE = 1000.0;
    
    public void resetBall() {
    	x = -50.0;
    	y = -50.0;
    	vx = 0.0;
    	vy = 0.0;
        numCollideFrames = 0;
    	hitGoal = false;
    }

    public void update(Planet[] planets) {
        // gravity calculations
        var gravityX = 0.0;
        var gravityY = 0.0;
        var closest = Double.POSITIVE_INFINITY;
        for (var planet : planets) {
            var dx = planet.x() - x;
            var dy = planet.y() - y;
            var rr = (dx * dx) + (dy * dy);
            var r = Math.sqrt(rr);
            var d = r - planet.radius();
            if (d < closest) {
                closest = d;
            }
            dx /= r;
            dy /= r;
            var force = (GRAVITY * planet.volume()) / rr;
            gravityX += dx * force;
            gravityY += dy * force;
        }
        // if far away from all planets, accelerate much faster
        if (closest > GRAVITY_BOOST_DISTANCE) {
            gravityX *= GRAVITY_BOOST;
            gravityY *= GRAVITY_BOOST;
        }
        vx += gravityX;
        vy += gravityY;
        x += vx;
        y += vy;
        // collision calculations
        for (var planet : planets) {
            var dx = planet.x() - x;
            var dy = planet.y() - y;
            var distSq = (dx * dx) + (dy * dy);
            var rSum = RADIUS + planet.radius();
            var rSumSq = rSum * rSum;
            if (distSq <= rSumSq) {
                var dist = Math.sqrt(distSq);
                dx /= dist;
                dy /= dist;
                var diff = rSum - dist;
                x -= dx * diff;
                y -= dy * diff;
                var len = Math.sqrt((vx * vx) + (vy * vy));
                var vxn = vx / len;
                var vyn = vy / len;
                if(planet.isGoal()) {
                	hitGoal = true;
                	return;
                }
                vx = (-vxn - dx) * 0.35 * len;
                vy = (-vyn - dy) * 0.35 * len;
                ++this.numCollideFrames;
                
                return;
            }
            
        }
        this.numCollideFrames = 0;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        RenderUtils.renderCircle(g, x, y, RADIUS);
    }
}
