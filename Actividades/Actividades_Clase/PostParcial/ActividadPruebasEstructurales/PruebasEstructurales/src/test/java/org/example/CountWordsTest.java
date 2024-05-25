package org.example;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CountWordsTest {
    @Test
    void twoWordsEndingWithS() { // 1
        // Arrange
        CountWords countWords = new CountWords("dogs cats");
        // Act
        int words = countWords.count();
        //Assert
        assertThat(words).isEqualTo(2);
    }
    @Test
    void noWordsAtAll() { // 2
        // Arrange
        CountWords countWords = new CountWords("dog cat");
        //Act
        int words = countWords.count();
        //Assert
        assertThat(words).isZero();
    }
    @Test
    void wordsThatEndInR() {
        //Arrange
        CountWords countWords = new CountWords("car bar");
        //Act
        int words = countWords.count();
        //Assert
        assertThat(words).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"","este texto no es valido.","este texto no es valido "})
    void incorrectFormatTextShouldThrowException(String text){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->{
                    new CountWords(text);
                });
    }



}
