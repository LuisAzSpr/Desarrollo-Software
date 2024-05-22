package org.example;

import org.example.graphics.ConsoleGraphics;
import org.example.graphics.Graphics;
import org.example.shapes.Circle;
import org.example.shapes.Rectangle;
import org.example.shapes.Shape;
import org.example.shapes.TextBox;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Graphics g = new ConsoleGraphics();
        Shapes shapes = new Shapes(g);
        Scanner scanner = new Scanner(System.in);
        String tipo = "";
        do{
            System.out.print("Ingrese el tipo de figura: ");
            tipo = scanner.nextLine();
            String parametros = scanner.nextLine();
            Shape shape = mapear(tipo,parametros);
            shapes.setShape(shape);
        }while(!tipo.equals("end"));


        shapes.draw();

    }

    private static Shape mapear(String tipo,String parametros){
        Scanner scanner = new Scanner(System.in);
        try{
            switch (tipo){
                case "Circle":
                    int radius = Integer.parseInt(parametros);
                    return new Circle(radius);
                case "Rectangle":
                    int width =  parametros.indexOf(0);
                    int height  = Integer.parseInt(parametros.indexOf(2));



            }
        }
        catch(Exception e){
            throw new IllegalArgumentException("Incorrect parameters");
        }

        return null;
    }
}