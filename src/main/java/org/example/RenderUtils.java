package org.example;

import java.awt.Graphics;

public final class RenderUtils {
    private RenderUtils() {}

    public static void renderCircle(Graphics g, double x, double y, double radius) {
        g.fillOval((int) Math.round(x - radius), (int) Math.round(y - radius), (int) Math.round(2.0 * radius), (int) Math.round(2.0 * radius));
    }
}
