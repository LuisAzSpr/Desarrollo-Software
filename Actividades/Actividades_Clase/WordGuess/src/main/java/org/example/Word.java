package org.example;

public class Word {

    private String correctWord;
    private int puntaje;
    private String status;


    public Word(String correctWord,int numIntentos) {
        this.correctWord = correctWord;
        this.puntaje = numIntentos;
        this.status = "NoTerminado";
    }

    public Score guess(String attempt) {

        if(coincide(attempt)){ // si coincide
            status = "terminado"; // el estado termina
            return null; // retorna null
        }
        puntaje --; // si no termina
        if(puntaje==0){ // verifica si el puntaje es 0 (no hay mas intentos)
            status = "terminado"; // si es asi, asigna el estado en terminado
            return null; // retorn null
        }
        return new Score(correctWord,attempt); // si aun no termina, devuelve score
    }

    public boolean coincide(String attempt){
        for(int i=0;i<correctWord.length();i++){
            if(correctWord.charAt(i)!=attempt.charAt(i)){
                return false;
            }
        }
        return true;
    }

}
