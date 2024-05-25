package org.example;

public class CountWords {
    private String str;
    public CountWords(String str){
        if(str.isEmpty() || textEndsWithoutLetter(str)){
            throw new IllegalArgumentException("El texto no sigue el formato");
        }
        this.str = str;
    }
    public int count() {
        str += " ";
        int words = 0;
        char last = ' ';
        for(char c:str.toCharArray()){ // para cada caracter de la cadena
            if(lastWordEndsInRorS(last,c)){ // si la ultima palabra termina en r o s
                words++; // aumentamos en 1
            }
            last = c; // guardamos la ultima letra
        }
        return words;
    }
    private boolean lastWordEndsInRorS(char last,char actual){ // verifica si la palabra anterior termina en r o s
        return !isLetter(actual) && (last=='r'||last=='s');
    }
    private boolean isLetter(char c) { // verifica si el caracter es una letra
        return Character.isLetter(c);
    }
    private boolean textEndsWithoutLetter(String str){
        return !isLetter(str.charAt(str.length()-1));
    }
}



/*
public class CountWords {
    public int count(String str) {
        int words = 0;
        char last = ' ';
        for (int i = 0; i < str.length(); i++) { // 1
            if (!isLetter(str.charAt(i)) && (last == 's' || last == 'r')) { // 2
                words++;
            }
            last = str.charAt(i); // 3
        }
        if (last == 'r' || last == 's') {
            words++;
        }
        return words;
    }
    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
}*/