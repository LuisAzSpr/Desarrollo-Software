package org.example;

/*
    Hemos hecho los siguientes cambios en el codigo:
    1. El nombre de la clase a uno mas intepretable
    2. Hemos cambiado el nombre del metodo a rangeOfNumber.
    3. Hemos evitado los condiconales anidados en la modificacion porque
    estos incrementan la complejidad del codigo.
 */

public class AnalysisNumber {

    public void rangeOfNumber(String number){
        int a = Integer.parseInt(number);
        if(a>100){
            System.out.println("A is greater than 100");
        }
        else if(a>50){
            System.out.println("A is greater than 50 but not more than 100");
        }
        else if(a<20){
            System.out.println("A is less than 20");
        }
        else{
            System.out.println("A is between 20 and 50");
        }
    }
}

/*
public class Refactor {
    public static void main(String[] args) {
    int a = Integer.parseInt(args[0]);
    if (a > 50) {
     if (a > 100) {
        System.out.println("A is greater than 100");
     } else {
        System.out.println("A is greater than 50 but not more than 100");
     }
    } else {
    if (a < 20) {
        System.out.println("A is less than 20");
     } else {
     System.out.println("A is between 20 and 50");
     }
    }
 }
}
 */
