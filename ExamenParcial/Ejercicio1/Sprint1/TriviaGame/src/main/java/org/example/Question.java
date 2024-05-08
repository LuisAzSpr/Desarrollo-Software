package org.example;

import java.util.ArrayList;

public class Question {

    private ArrayList<String>respuestas;
    private int correcta;
    private String pregunta;

    public Question(String pregunta,int correcta){
        this.pregunta = pregunta;
        this.correcta = correcta;
    }

    public void agregarRespuesta(String respuesta){
        respuestas.add(respuesta);
    }

    public void agregarRespuestas(ArrayList<String> respuestas){
        this.respuestas = respuestas;
    }


}
