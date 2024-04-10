package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class WordTestDBB2 {
    private Word word;
    private String wordGuess;
    private ArrayList<Letter> letters;
    private Score score;
    Letter let;

    @Given("^(?i)la\\s*palabra\\s*(\\w{4,})$")
    public void DadoUnaPalabra(String palabra){
        word = new Word(palabra,6);
    }

    @And("^(?i)mi\\s*palabra\\s*de\\s*intento\\s*es\\s*(\\w{4,})$")
    public void AdivinoUnaPalabra(String palabra){
        score = word.guess(palabra);
    }

    @When("^(?i)tomo\\s*la\\s*posicion\\s*(\\d{1,})$")
    public void TomoUnaPosicionDeLaPalabra(int posicion){
        let = score.letter(posicion);
    }

    @Then("^(?i)las\\s*letras\\s*coinciden")
    public void LetrasCoinciden(){
        assertThat(let).isEqualTo(Letter.CORRECT);
    }

    @Then("^(?i)las?\\s*letras?\\s*no\\s*coinciden\\s*pero\\s*se\\s*encuentra$")
    public void LetraNoCoincidenPeroSeEncuentra(){
        assertThat(let).isEqualTo(Letter.PART_CORRECT);
    }

    @Then("^(?i)la\\s*letra\\s*no\\s*se\\s*encuentra$")
    public void LetraNoSeEncuentra(){
        assertThat(let).isEqualTo(Letter.INCORRECT);
    }


}
