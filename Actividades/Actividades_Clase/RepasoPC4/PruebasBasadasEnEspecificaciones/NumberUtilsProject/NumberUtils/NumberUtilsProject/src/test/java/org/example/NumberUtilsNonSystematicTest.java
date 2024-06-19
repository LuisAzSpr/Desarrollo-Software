package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumberUtilsNonSystematicTest {
    @Test
    void nulosYVaciosTest(){
        assertThatNumbers(null,numbers(1,2,3),null); // T1
        assertThatNumbers(numbers(),numbers(1,2,3),numbers(1,2,3)); // T2
        assertThatNumbers(numbers(1,2,3),null,null); // T3
        assertThatNumbers(numbers(),numbers(1,2,3),numbers(1,2,3)); // T4
    }

    @Test
    void digitosIndividuales(){

    }

    private void assertThatNumbers(List<Integer> integersR,List<Integer> integersL,List<Integer> expected){
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
}

