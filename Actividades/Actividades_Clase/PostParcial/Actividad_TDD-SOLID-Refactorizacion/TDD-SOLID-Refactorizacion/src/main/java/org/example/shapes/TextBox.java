package org.example.shapes;

import org.example.graphics.ConsoleGraphics;
import org.example.graphics.Graphics;

public class TextBox implements Shape {
    private String text;

    public TextBox(String text){
        this.text = text;
    }
    @Override
    public void draw(Graphics graphics){
        graphics.drawText(text);
    }
}

