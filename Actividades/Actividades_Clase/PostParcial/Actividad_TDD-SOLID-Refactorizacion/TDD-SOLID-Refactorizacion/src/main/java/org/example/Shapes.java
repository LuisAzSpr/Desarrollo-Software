package org.example;

import org.example.graphics.Graphics;
import org.example.shapes.Shape;
import java.util.ArrayList;
import java.util.List;

public class Shapes {
    private Shape shape; // strategy
    private final Graphics g;
    public void setShape(Shape shape){
        this.shape = shape;
    }
    public void draw(){
        shape.draw(g); // strategy.execute()
    }


    private final List<Shape> allShapes = new ArrayList<>();

    public Shapes(Graphics g){
        this.g = g;
    }

    public void add(Shape s){
        allShapes.add(s);
    }
    //public void draw(){
    //        allShapes.forEach(shape->shape.draw(g));
    //}



}


