package org.example;

import java.util.ArrayList;

public class Quiz {
    private int puntuacion;
    private int numRondas;
    private boolean juegoFin;

    private ArrayList<Integer> respuestasCorrectas;
    private ArrayList<Question> questions;
    public Quiz(ArrayList<Question>questions,ArrayList<Integer> respuestasCorrectas,int numRondas){
        this.numRondas = numRondas;
    }

    public void verificar(){

    }

}
