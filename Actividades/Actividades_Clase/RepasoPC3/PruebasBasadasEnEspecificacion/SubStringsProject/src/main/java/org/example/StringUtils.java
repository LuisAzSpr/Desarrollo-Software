package org.example;

import java.util.ArrayList;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    // Este metodo verifica si la cadena es nula o vacia
    private static boolean isEmpty(final String str) {
        return str == null || str.isEmpty();
    }

    // Metodo que obtiene todas las cadenas que se encuentran entre un limitador inicial (open) y un
    // delimitador final (close) en la cadena base str
    public static String[] substringsBetween(final String str, final String open, final String close) {
        // si la cadena base str es nulo entonces retorna nulo
        if(str==null){
            return null;
        }
        // si alguno de los delimtitadores es empty lanza una exception
        if(isEmpty(open) || isEmpty(close)){
            throw new InvalidDelimiterException("Open or close delimator cannot be empty");
        }
        // solo tomamos letras minusculas y mayusculas y numeros
        final String sanitizedStr = str.replaceAll("[^a-zA-Z0-9]", "");

        final int strLen = sanitizedStr.length();
        //Si la cadena esta vacia, retorna un arreglo vacío, indicando que no se encontro ninguna cadena
        if (strLen == 0) {
            return new String[0];
        }

        // tomamos los tamanios de cada uno de los delimitadores
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0; // inicializamos en 0 a pos que se encargara de recorrer la cadena sanitizedStr

        // encuentra las cadenas que se encuetran entre open y close en ese orden
        while (pos < strLen - closeLen) {
            // se toma el indice en el que se encuentra la cadena open en la cadena sanitizedStr contando desde pos
            int start = sanitizedStr.indexOf(open, pos);
            // si start < 0 indica que no se encontro un delimitador open y sale del bucle
            if (start < 0) {
                break;
            }
            // si se encontro entonces start se actualiza
            start += openLen;
            // ahora se toma el indice en el que se encuentra la cadena close en la cadena sanitizedStr apartir de start
            final int end = sanitizedStr.indexOf(close, start);
            // Sino se encuentra ninguna cadena close entonces sale del bucle
            if (end < 0) {
                break;
            }
            // Se añade la cadena que se encuentra entre star y end a la lista list
            list.add(sanitizedStr.substring(start, end));
            //Actualizar posicion
            pos = end + closeLen;
        }

        //si la lista esta vacia retornar null
        if (list.isEmpty()) {
            return null;
        }

        // convertimos la lista en un array
        return list.toArray(new String[0]);
    }


    public static String[] regexSubstringsBetween(final String str, final String open, final String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        final String regex = Pattern.quote(open) + "(.*?)" + Pattern.quote(close);
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(str);
        final List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(new String[0]);
    }

}