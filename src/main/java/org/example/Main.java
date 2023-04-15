package org.example;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Canvas());
        frame.setSize(new Dimension(Canvas.WIDTH, Canvas.HEIGHT));
        frame.setVisible(true);
    }
}