package org.example;

import org.example.graphics.ConsoleGraphics;
import org.example.graphics.Graphics;
import org.example.shapes.Circle;
import org.example.shapes.Rectangle;
import org.example.shapes.TextBox;


public class Main {
    public static void main(String[] args) {
        Graphics g = new ConsoleGraphics();
        Shapes shapes = new Shapes(g);

        shapes.add(new TextBox("Escribiendo en el textbox..."));
        shapes.add(new Rectangle(2,3));
        shapes.add(new Circle(4));

        shapes.draw();
    }
}