package org.example;

/* Creamos una clase que herede de RunTimeException para que pueda ser
 lanzada en tiempo de ejecucion cuando se le da un numero negativo
 a un parametro de algun metodo de Calculator */
public class NegativeNumberException extends RuntimeException{

    // message es el mensaje que se mostrara cuando la exepcion sea lanzada
    public NegativeNumberException(String message) {
        super(message);
    }
}
