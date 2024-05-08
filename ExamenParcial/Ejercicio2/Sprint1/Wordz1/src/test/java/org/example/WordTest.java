package org.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.Letter.*;

public class WordTest {
    @Test
    public void oneIncorrectLetter() {
        var word = new Word("A");
        var score = word.guess("Z");
        assertScoreForGuess(score,0,INCORRECT);
    }

    @Test
    public void oneCorrectLetter() {
        var word = new Word("A");
        var score = word.guess("A");
        assertScoreForGuess(score,0, CORRECT);
    }

    private void assertScoreForGuess(Score score,int position, Letter letter) {
        assertThat(score.letter(position)).isEqualTo(letter);
    }

}