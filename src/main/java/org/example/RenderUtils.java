package org.example;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class RenderUtils {
    private RenderUtils() {}

    public static void renderCircle(Graphics g, double x, double y, double radius) {
        g.fillOval((int) Math.round(x - radius), (int) Math.round(y - radius), (int) Math.round(2.0 * radius), (int) Math.round(2.0 * radius));
    }

    public static Image getImage(String name) {
        try (var resource = ClassLoader.getSystemResourceAsStream(name)) {
            if (resource == null) {
                throw new RuntimeException("Resource not found");
            }
            return ImageIO.read(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
