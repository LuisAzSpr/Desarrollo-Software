package org.example.shapes;

import org.example.graphics.Graphics;

public class Rectangle implements Shape {
    private int width;
    private int height;
    public Rectangle(int width,int height){
        this.height = height;
        this.width = width;
    }
    @Override
    public void draw(Graphics graphics) {
        for (int row = 0; row < height; row++) {
            graphics.drawHorizontalLine(width);
        }
    }

    public int getHeight() {
        return height;
    }
    public int getWidth(){
        return width;
    }
}