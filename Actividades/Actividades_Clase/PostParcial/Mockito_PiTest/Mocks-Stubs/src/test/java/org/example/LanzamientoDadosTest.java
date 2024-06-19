package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LanzamientoDadosTest {
    @Mock
    private NumerosAleatorios numerosAleatorios;
    @InjectMocks
    private LanzamientoDados lanzamientoDados;

    @BeforeEach
    void setUp(){
        when(numerosAleatorios.getNumero(1,6)).thenReturn(3);
    }
    @Test
    void verificarNumero3(){
        assertThat(lanzamientoDados.lanzarDado()).isEqualTo(3);
    }
}