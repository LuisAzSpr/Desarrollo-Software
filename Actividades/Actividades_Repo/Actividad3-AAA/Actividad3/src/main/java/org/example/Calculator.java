package org.example;

public class Calculator {
    public int sumar(int numeroA, int numeroB) {
        if(numeroA<0 || numeroB<0){
            throw new NegativeNumberException("Numero negativo no aceptado");
        }
        return numeroA + numeroB;
    }

    public int restar(int numeroA, int numeroB) {
        if(numeroA<0 || numeroB<0){
            throw new NegativeNumberException("Numero negativo no aceptado");
        }
        return numeroA - numeroB;
    }

    public int multiplicacion(int numeroA, int numeroB) {
        if(numeroA<0 || numeroB<0){
            throw new NegativeNumberException("Numero negativo no aceptado");
        }
        return numeroA * numeroB;
    }

    public double division(int numeroA, int numeroB) {
        if(numeroA<0 || numeroB<0){
            throw new NegativeNumberException("Numero negativo no aceptado");
        }
        if (numeroB == 0) {
            throw new ArithmeticException("Division por cero");
        }
        return (double) numeroA / numeroB;
    }
}
