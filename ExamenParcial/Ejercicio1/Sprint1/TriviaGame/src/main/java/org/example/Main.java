package org.example;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ArrayList<Question> questions = new ArrayList<>();

        // definamos las preguntas

        Question q1 = new Question("La capital de francia?",1);
        Question q2 = new Question("La capital de Lima?",0);
        Question q3 = new Question("La capital de Chile?",2);

        // sus respuestas
        ArrayList<String> respuestas = new ArrayList<>();

        respuestas.add("Lima");
        respuestas.add("Paris");
        respuestas.add("Comas");
        respuestas.add("Santiago de chile");

        // agregamos las respuestas a cada pregunta (en este caso son la misma)
        q1.agregarRespuestas(respuestas);
        q2.agregarRespuestas(respuestas);
        q3.agregarRespuestas(respuestas);

        // agregamos cada  question a cada pregunta
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);


        System.out.println("Bienvenido al juego de Trivia!!!");






    }
}