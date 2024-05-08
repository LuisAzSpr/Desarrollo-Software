package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Stepdefs {

    FootballTeam footballTeam;

    @BeforeEach
    void setUp(){
        footballTeam = new FootballTeam();
        System.out.println("beforeEach----");
    }

    @AfterEach
    void tearDown(){
        System.out.println("afterEach-----");
    }

    @Given("^Un equipo de futbol (\\w+)$")
    public void dadoUnEquipoDeFutbol(String nombre){
        System.out.println("Equipo de futbol: "+nombre);
    }

    @When("^Este equipo gana (\\d+) partidos de la (?:primera|segunda|tercera) fecha$")
    public void ganaUnPartido(int numPartidos){
        footballTeam = new FootballTeam(numPartidos);
    }

    @Then("^El equipo debe tener (\\d+) partidos ganados$")
    public void partidosGanados(int numPartidos){
        assertThat(footballTeam.getGamesWon()).isEqualTo(numPartidos);
    }

}
