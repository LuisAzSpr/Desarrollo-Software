package org.example.graphics;

public class ConsoleGraphics implements Graphics{
    @Override
    public void drawText(String text) {
        print(text);
    }
    @Override
    public void drawHorizontalLine(int width) {
        var rowText = new StringBuilder();
        for (int i = 0; i < width; i++) {
            rowText.append('X');
        }
        print(rowText.toString());
    }
    private void print(String text) {
        System.out.println(text);
    }
    @Override
    public void drawCircle(int radius){
        System.out.println("Dibujando un circulo de radio: "+radius);
    }

}
