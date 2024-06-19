package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberUtilsTest {
    @Test
    void nulosYVaciosTest(){
        assertThatNumbers(null,numbers(1,2,3),null); // T1
        assertThatNumbers(numbers(),numbers(1,2,3),numbers(1,2,3)); // T2
        assertThatNumbers(numbers(1,2,3),null,null); // T3
        assertThatNumbers(numbers(),numbers(1,2,3),numbers(1,2,3)); // T4
    }
    @Test
    void digitosIndividuales(){
        assertThatNumbers(numbers(1),numbers(4),numbers(5)); // T5
        assertThatNumbers(numbers(4),numbers(8),numbers(1,2)); // T6
    }
    @Test
    void multiplesDigitosConLaMismaLongitud(){
        assertThatNumbers(numbers(2,4),numbers(3,5),numbers(5,9)); //T7
        assertThatNumbers(numbers(1,2,5),numbers(2,3,5),numbers(3,6,0)); // T8
        assertThatNumbers(numbers(1,5,0),numbers(3,5,0),numbers(5,0,0)); // T9
        assertThatNumbers(numbers(2,3,4,5),numbers(1,6,5,5),numbers(4,0,0,0)); // T10
        assertThatNumbers(numbers(4,5,6,7,8),numbers(2,2,4,1,2),numbers(6,8,0,9,0));// T11
        assertThatNumbers(numbers(9,2),numbers(1,3),numbers(1,0,5)); // T12
    }

    @Test
    void multiplesDigitosConDiferentesLongitudes(){
        assertThatNumbers(numbers(1,2,4),numbers(3,5),numbers(1,5,9)); //T13
        assertThatNumbers(numbers(1,2,5),numbers(3,5),numbers(1,6,0)); // T14
        assertThatNumbers(numbers(4,1,5,0),numbers(3,5,0),numbers(4,5,0,0)); // T15
        assertThatNumbers(numbers(2,3,4,5),numbers(6,5,5),numbers(3,0,0,0)); // T16
        assertThatNumbers(numbers(4,5,6,7,8),numbers(4,1,2),numbers(4,6,0,9,0));// T17
        assertThatNumbers(numbers(9,2),numbers(9),numbers(1,0,1)); // T18
    }
    @Test
    void cerosAlaIzquierda(){
        assertThatNumbers(numbers(1,2,3,4),numbers(0,0,1,2),numbers(1,2,4,6)); // T19
        assertThatNumbers(numbers(4,1,6,3),numbers(0,0,3,7),numbers(4,2,0,0)); // T20
    }
    @Test
    void limites(){
        assertThatNumbers(numbers(9,9),numbers(1),numbers(1,0,0)); // T21
    }

    @ParameterizedTest
    @MethodSource("digitsOutOfRange")
    void shouldThrowExceptionWhenDigitsAreOutOfRange(List<Integer> left, List<Integer> right) {
        assertThatThrownBy(() -> new NumberUtils().add(left, right))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void assertThatNumbers(List<Integer> integersR, List<Integer> integersL, List<Integer> expected){
        NumberUtils numberUtils = new NumberUtils();
        List<Integer> resultado = numberUtils.add(integersR,integersL);
        assertThat(resultado).isEqualTo(expected);
    }
    private static List<Integer> numbers(int... nums){
        List<Integer> list = new ArrayList<>();
        for(int n:nums){
            list.add(n);
        }
        return list;
    }

    static Stream<Arguments> digitsOutOfRange() {
        return Stream.of(
                Arguments.of(numbers(1,-1,1), numbers(1)),
                Arguments.of(numbers(1), numbers(1,-1,1)),
                Arguments.of(numbers(1,11,1), numbers(1)),
                Arguments.of(numbers(1), numbers(1,11,1))
        );
    }
}