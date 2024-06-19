package org.example;

import java.security.PrivilegedActionException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberUtils {
    public List<Integer> add(List<Integer> left, List<Integer> right){
        // si alguna lista es nula retornamos null
        if(left==null || right==null){
            return null;
        }
        // Le damos la reversa, ya que la operacion de suma empieza desde derecha a izquierda
        Collections.reverse(left);
        Collections.reverse(right);
        // inicializamos la lista que contendra los resultados
        LinkedList<Integer> result = new LinkedList<>();
        // inicializamos en 0 el carry
        int carry = 0;
        // para cada digito
        for(int i=0;i<Math.max(left.size(),right.size());i++){
            // se tomara 0 en caso el indice sobrepase la cantidad de digitos de un numerop
            int leftDigit = left.size()>i ? left.get(i) : 0;
            int rightDigit = right.size()>i ? right.get(i) : 0;
            // si en caso el Integer se encuentra fuera de rango se retorna una exception
            if(leftDigit<0 || leftDigit>9 || rightDigit<0 || rightDigit>9){
                throw new IllegalArgumentException();
            }
            // en caso el digito sea correcto se hace la respectiva de los digitos junto al carry
            int sum = leftDigit + rightDigit + carry;
            // se toman las unidades
            result.addFirst(sum%10);
            // se llevan las decenas
            carry = sum/10;
        }
        // si al terminar la suma todavia existe un carry !=0 -> se añade
        if(carry>0){
            result.addFirst(carry);
        }
        // mientras que los primeros numeros sean ceros los eliminamos.
        while(result.size()>1 && result.get(0)==0){
            result.remove(0);
        }
        return result;
    }
}
