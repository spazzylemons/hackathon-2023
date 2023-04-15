package org.example;

public final class LevelBuilder {
    private LevelBuilder() {}

    public static Planet[] createLevel(int numPlanets) {
        var x = 0.0;
        var y = 0.0;
        var result = new Planet[numPlanets];
        for (var i = 0; i < numPlanets; i++) {
            var radius = (Math.random() * 100.0) + 50.0;
            result[i] = new Planet(x, y, radius, i == numPlanets - 1);
            var dir = (Math.random() - 0.5);
            var distance = radius + (Math.random() * 300.0) + 200.0;
            x += Math.cos(dir) * distance;
            y += Math.sin(dir) * distance;
        }
        return result;
    }
}
