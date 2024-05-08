package org.example;

public class Main {
    public static void main(String []args){

        String palabra = "mouse";
        String entrada = "";
        boolean adivino = false;
        int puntaje = 6;
        Word word = new Word(palabra);

        System.out.println("------------TIENES 6 INTENTOS------");
        for(int i=0;i<6;i++){
            entrada = System.in.toString();
            if(entrada.equals(palabra)){
                adivino = true;
                System.out.println("Adivinaste la palabra correcta!!! -> puntaje: "+puntaje);
            }
            else{
                // crea un objeto score
                Score score = word.guess(entrada);
                score.ImprimirResults();
            }
            //  el puntaje disminuye en 1 en cada iteracion
            puntaje -= i;
        }

        // si no adivino la palabra se muestra puntaje 0 (no adivino la palabra)
        if(!adivino){
            System.out.println("Su puntaje es 0 (no adivino la palabra)");
        }

    }
}
