package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    Calculadora calculadora;

    @BeforeEach
    void setUp(){
        calculadora = new Calculadora();
    }
    @Test
    void testAdd(){
        assertThat(calculadora.add(3,2)).isEqualTo(5);
    }

    @Test
    void productTest(){
        assertThat(calculadora.multiplication(3,2)).isEqualTo(6);
    }





}