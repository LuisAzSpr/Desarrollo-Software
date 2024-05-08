package org.example;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
public class Score {
    private final String correct;

    private Letter resultado = Letter.INCORRECT;

    public Score(String correct) {this.correct = correct;
    }
    public Letter letter(int position) {
        return resultado;
    }
    public void assess(int i,String attempt) {
        if(correct.charAt(i)==attempt.charAt(i)){
            resultado =  Letter.CORRECT;
        }
    }
}
