package org.example.shapes;

import org.example.graphics.Graphics;

public class Circle implements Shape {
    private final int radius;
    public Circle(int radius) {
        this.radius = radius;
    }
    @Override
    public void draw(Graphics g) {
        g.drawCircle(radius);
    }

}



