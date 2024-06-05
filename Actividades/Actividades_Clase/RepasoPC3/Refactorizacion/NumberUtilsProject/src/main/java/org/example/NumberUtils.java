package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberUtils {
    public static List<Integer> add(List<Integer> left, List<Integer> right) {
        if (left == null || right == null) {
            return null;
        }
        // Saca la reversa para empezar de izquierda a derecha
        Collections.reverse(left);
        Collections.reverse(right);
        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;
        // para cada uno de los indices del numero con mayor numero de cifras
        for (int i = 0; i < Math.max(left.size(), right.size()); i++) {
            // tomamos 0 si ya no tenemos mas cifras en nuestro numero
            int leftDigit = left.size() > i ? left.get(i) : 0;
            int rightDigit = right.size() > i ? right.get(i) : 0;
            // si la cifra se encuentra en un rango incorrecto entonces lanza una exception
            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9) {
                throw new IllegalArgumentException();
            }
            // sumamos los numeros en la misma posicion mas el carry
            int sum = leftDigit + rightDigit + carry;
            // Añadimos el resto de 10 de la suma al resultado
            result.addFirst(sum % 10);
            // Y llevamos lo faltante a una unidad superior
            carry = sum / 10;
        }
        // si el carry sobro entonces lo agregamos al resultado
        if (carry > 0) {
            result.addFirst(carry);
        }
        // si hay solo 0s entonces los eliminamos
        while (result.size() > 1 && result.get(0) == 0) {
            result.remove(0);
        }
        // retornamos el resultado
        return result;
    }
}
