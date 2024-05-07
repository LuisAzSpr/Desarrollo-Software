package org.example;

import org.example.Word;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLOutput;
import java.util.ArrayList;


class WordTest {

    @Test
    void oneIncorrectLetter(){ // Test para una letra incorrecta

        // Arrange
        var word = new Word("A",6);

        // Act
        var score = word.guess("Z");
        var result = score.letter(0);

        // Assert
        assertThat(result).isEqualTo(Letter.INCORRECT);

    }

    @Test
    void oneCorrectLetter(){ // Test para una letra correcta
        // Arrange
        var word = new Word("A",6);

        // Act
        var score = word.guess("A");
        var result = score.letter(0);

        //Assert
        assertThat(result).isEqualTo(Letter.CORRECT);
    }
    @Test
    void LetterInWord(){

        //Arrange
        var word = new Word("laptop",6);

        // ACT
        String guessWord = "catdog"; // palabra a adivinar
        Score score = word.guess("catdog"); // instanciamos un objeto de tipo Score

        // buscamos la letra t, que se enucentra tanto en laptop como en catdog, pero en difrentes posiciones
        int position = guessWord.indexOf("t");
        // guardamos que nos devuelve el sistema
        Letter result = score.letter(position);

        // ASSERT
        // Nos aseguramos que sea parcialmente correcta (que se encuentre en la palabra pero no en la posicion)
        assertThat(result).isEqualTo(Letter.PART_CORRECT);

    }

    @Test
    void IncorrectWord(){
        // Arrange
        String correctWord = "cream"; // palabra word
        String guessWord = "brain"; // palabra intento
        var word = new Word(correctWord,6);
        Score score = word.guess(guessWord);

        ArrayList<Letter>lettersReal = new ArrayList<>(); // lista de letters

        //llenamos la lista de letters
        lettersReal.add(Letter.INCORRECT);      // b not in cream
        lettersReal.add(Letter.CORRECT);        // r == r
        lettersReal.add(Letter.PART_CORRECT);   // a not in cream
        lettersReal.add(Letter.INCORRECT);      // i not in cream
        lettersReal.add(Letter.INCORRECT);      // n not in cream

        // Act
        ArrayList<Letter>lettersGuess = score.letters(); //generamos los letters de la palabra

        //Assert
        for(int i=0;i<correctWord.length();i++){
            assertThat(lettersGuess.get(i)).isEqualTo(lettersReal.get(i)); // verificamos que los letters coincidan
        }

    }

}

