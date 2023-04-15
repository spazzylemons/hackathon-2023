package org.example;

public final class LevelBuilder {
    private LevelBuilder() {}

    public static Planet[] createLevel(int numRings) {
        var result = new Planet[1 + (3 * numRings)];
        result[0] = new Planet(0.0, 0.0, 0.0, 100.0, true);
        var distance = 0.0;
        var radius = 100.0;
        var j = 1;
        for (var i = 0; i < numRings; i++) {
            distance += radius * 4.0 + (Math.random() * 50.0) + 20.0;
            var angle = Math.random() * 2.0 * Math.PI;
            radius = (Math.random() * 50.0) + 100.0;
            var speed = Math.random() * 0.002 + 0.002;
            result[j++] = new Planet(angle, speed, distance, radius, false);
            angle += (Math.PI * 2.0 / 3.0);
            result[j++] = new Planet(angle, speed, distance, radius * 0.7, false);
            angle += (Math.PI * 2.0 / 3.0);
            result[j++] = new Planet(angle, speed, distance, radius * 0.8, false);
        }
        return result;
    }
}
