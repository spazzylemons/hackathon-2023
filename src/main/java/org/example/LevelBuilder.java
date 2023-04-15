package org.example;

public final class LevelBuilder {
    private LevelBuilder() {}

    public static Planet[] createLevel(int numRings) {
        var result = new Planet[1 + (4 * numRings)];
        result[0] = new Planet(0.0, 0.0, 0.0, 60.0, true);
        var distance = 0.0;
        var radius = 60.0;
        var j = 1;
        for (var i = 0; i < numRings; i++) {
            distance += radius * 4.0 + (Math.random() * 50.0) + 20.0;
            var angle = Math.random() * 2.0 * Math.PI;
            radius = (Math.random() * 60.0) + 80.0;
            var speed = 1.0 / Math.sqrt(distance) * 0.04;
            result[j++] = new Planet(angle, speed, distance, radius, false);
            angle += (Math.PI * 0.5);
            result[j++] = new Planet(angle, speed, distance, radius * 0.7, false);
            angle += (Math.PI * 0.5);
            result[j++] = new Planet(angle, speed, distance, radius * 0.8, false);
            angle += (Math.PI * 0.5);
            result[j++] = new Planet(angle, speed, distance, radius * 0.9, false);
        }
        return result;
    }
}
