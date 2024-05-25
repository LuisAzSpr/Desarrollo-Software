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
        Shape shape = null;
        String tipo = "";
        do{
            System.out.print("Ingrese el tipo de figura: ");
            tipo = scanner.nextLine();
            System.out.print("Ingrese sus parametros: ");
            String parametros = scanner.nextLine();
            shape = mapear(tipo,parametros);
            shapes.setShape(shape);
            shapes.draw();
            System.out.println("\n");
        }while(shape!=null);
        shapes.draw();
    }


    private static Shape mapear(String tipo,String parametros){
        try{
            switch (tipo){
                case "Circle":
                    int radius = Integer.parseInt(parametros);
                    return new Circle(radius);
                case "Rectangle":
                    int width =  parametros.charAt(0) - 48;
                    int height  = parametros.charAt(2) - 48;
                    return new Rectangle(width,height);
                case "TextBox":
                    return new TextBox(parametros);
                case "end":
                    return null;
                default:
                    throw new ClassNotFoundException("No se encontro la clase de forma");
            }
        }
        catch(Exception e){
            throw new IllegalArgumentException("Incorrect parameters");
        }
    }
}