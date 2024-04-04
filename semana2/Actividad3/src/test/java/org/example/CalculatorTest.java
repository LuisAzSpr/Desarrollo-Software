package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    @Test
    public void testSum_PositiveNumbers_ShouldReturnCorrectSum(){
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculator.sumar(numeroA,numeroB);

        // Assert
        assertEquals(15,resultado,"10+5 deberia ser 15");
    }


    @Test
    public void testRest_PositiveNumbers_ShouldReturnCorrectRest(){
        //Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculator.restar(numeroA,numeroB);

        // Assert
        assertEquals(5,resultado,"10-5 deberia ser 5");
    }


    @Test
    public void testMult_PositiveNumbers_ShouldReturnCorrectMult(){
        //Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculator.restar(numeroA,numeroB);

        // Assert
        assertEquals(5,resultado,"10*5 deberia ser 50");
    }


    @Test
    public void testDiv_PositiveNumbers_ShouldReturnCorrectDiv( ){
        //Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        double resultado = calculator.division(numeroA,numeroB);

        // Assert
        assertEquals(2,resultado,"10/5 deberia ser 2");

    }


    @Test
    public void testDiv_byZero_shouldReturnException(){
        // Arrange
        Calculator calculator = new Calculator();
        int numero = 10;
        Exception e = null;

        // Assert
        assertThrows(ArithmeticException.class, () -> {
            // Act
            calculator.division(numero, 0);
        });
    }


    @Test
    public void testSum_NegativeNumber_ShouldReturnException(){
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = -5;

        // Assert
        assertThrows(NegativeNumberException.class, () -> {
            // Act
            calculator.sumar(numeroA, numeroB);
        });

    }


    @Test
    public void testRest_NegativeNumber_ShouldReturnException(){

        //Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = -5;

        // Assert
        assertThrows(NegativeNumberException.class, () -> {
            // Act
            calculator.restar(numeroA, numeroB);
        });
    }


    @Test
    public void testMult_NegativeNumber_ShouldReturnException(){
        //Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = -5;

        // Assert
        assertThrows(NegativeNumberException.class, () -> {
            // Act
            calculator.multiplicacion(numeroA, numeroB);
        });
    }


    @Test
    public void testDiv_NegativeNumber_ShouldReturnException( ){
        //Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = -5;

        // Assert
        assertThrows(NegativeNumberException.class, () -> {
            // Act
            calculator.division(numeroA, numeroB);
        });
    }

}