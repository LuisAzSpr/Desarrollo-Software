# Pruebas Basadas en Especificaciones 
### SubStringsBetween

1. Ejercicio 1 : Escribe el código de prueba y considera las entradas str = "axcaycazc", open = "a" y close
   = "c" y explica lo que hace el código anterior.

Creamos la prueba para el codigo SubStringsBetween.

```java
@Test
void subStringsBetweenShouldReturnCorrectArray(){
  String[] arrayOfStrs = StringsUtil.substringsBetween("axcaycazc", "a","c");
  assertThat(arrayOfStrs).isEqualTo(new String[]{"x","y","z"});
}
``` 
Explicaremos que hace el codigo anteriormente probado.
Este codigo encuentra los substrings dentro de un string base pasado como parametro
al que llamaremos string base, la condicion que cumplen estos substrings
es que se encuentren entre 2 strings, uno inicial y uno final, a los que llamaremos strings 
delimitadores, es decir,  la funcion se encarga de hallar todos los substrings dentro de la cadena base que se encuentren entre
un string inicial y un string final pasados como parametros junto con el string base.

Veamos la documentacion en el codigo:

```java
public static String[] substringsBetween(final String str, final String open, final String close) {
    // Si la cadena es nula o alguno de los strings delimitadores es empty retornamos null
   if (str == null || isEmpty(open) || isEmpty(close)) {
      return null;
   }
   final int strLen = str.length();
   // Si la cadena base es vacia (no hay nada que buscar)
   if (strLen == 0) {
      // entonces restornamos un array vacio
      return EMPTY_STRING_ARRAY; 
   }
   // Caclulamos los tamanios de las cadenas de apertura y cierre
   final int closeLen = close.length();
   final int openLen = open.length();
   // Inicializamos un arrayList
   final List<String> list = new ArrayList<>();
   // Comenzamos desde la posicion inicial (desde el primer caracter)
   int pos = 0;
   while (pos < strLen - closeLen) {
      // Buscaremos la posicion de open en str desde el indice pos
      int start = str.indexOf(open, pos);
      // Si no se encontro la cadena "open" start=-1 < 0
      if (start < 0) {
          break;
      }
      // Si se encontro la cadena "open" entonces se toma el indice de la cadena restante 
      // despues de tomar la cadena "open", que sera en donde se buscar la cadena "close"
      start += openLen;
      final int end = str.indexOf(close, start);
      // Si no se encontro la cadena "close" start=-1 < 0
      if (end < 0) {
          break;
      }
      // Si se encontro la cadena "close" se añade la cadena entre star y end
      list.add(str.substring(start, end));
      // Por ultimo se toma el indice del final de la cadena "close" 
      pos = end + closeLen;
   }
   // Si la lista esta vacia retornamos null
   if (list.isEmpty()) {
      return null;
   }
   // Si no esta vacia retornamos list
   return list.toArray(EMPTY_STRING_ARRAY);
}
```
2. Ejercicio 2: Revisa los requisitos una vez más y escribe todos los casos de prueba que se te ocurran.
El formato no importa, puede ser algo así como "todos los parámetros son nulos". Cuando hayas
terminado con esta nota, compara tu conjunto de pruebas inicial con el que estamos a punto de
derivar.


   **Paso 1: Comprensión de los requisitos, entradas y salidas**

   - Entradas:
   
     - str : cadena base de la cual se extraeran las subcadenas.
     - open : cadena de apertura de la subcadena extraida.
     - close : cadena de cierre de la subcadena extraida.
     
   - Salida :
   
     - Arreglo con todas las subcadenas que cumplen la condicion de estar entre open y close dentro de str. 


   **Paso 2: Explora lo que hace el programa para varias entradas.**

```java
public class StringUtilsExploracionTest {
    @Test
    void simpleCase() {
        assertThat(StringsUtil.substringsBetween("abcd", "a", "d"))
                .isEqualTo(new String[] { "bc" });
    }
    @Test
    void manyStrings() {
        assertThat(StringsUtil.substringsBetween("abcdabcdab", "a", "d"))
                .isEqualTo(new String[] { "bc", "bc" });
    }
    @Test
    void openAndCloseTagsThatAreLongerThan1Char() {
        assertThat(StringsUtil.substringsBetween("aabcddaabfddaab", "aa", "dd"))
                .isEqualTo(new String[]{ "bc", "bf" });
    }
}
```



3. **Entradas Equivalentes :** "El programa hace lo mismo"

    substringsBetween("abcd","a","d") == substringsBetween("xyzw","x","w")
    
    ##### Preguntas clave : 

    • Cada entrada individualmente: "¿Cuáles son las posibles clases de entradas que puedo
    proporcionar?"
    
    • Cada entrada en combinación con otras entradas: "¿Qué combinaciones puedo probar entre
    las etiquetas de open y close?"
    
    • Las diferentes clases de resultados que se esperan de este programa: “¿Devuelve arreglos?
    ¿Puede devolver un arreglo vacío? ¿Puede devolver valores nulos?"


Empezamos con entradas individuales:

    Parametro str:  
    - Cadena null
    - Cadena vacia
    - Cadena de longitud 1
    - Cadena de longitud > 1
    
    Parametro open:
    - Cadena null
    - Cadena vacia
    - Cadena de longitud 1
    - Cadena de longitud > 1

    Parametro close :
    - Cadena null
    - Cadena vacia
    - Cadena de longitud 1
    - Cadena de longitud > 1

    Salidas:
        Arreglo de cadenas:
        - Arreglo null
        - Arreglo vacio
        - Item unico
        - Multiples items

        Cada cadena individual:
        - Vacio
        - Unico caracter
        - Multiples caracteres
    
## Casos

Se considera que "open" y "close" se comportan de la misma forma, es decir, se tomara para
(open=1 & close=1) y (open>1 & close>1), y dejaremos de lado (open>1 & close=1) o (open=1 & close>1).

**1. Casos excepcionales**

- T1 : str es null

- T2 : str es vacio

- T3 : open es null

- T4 : open es vacio

- T5 : close es null

- T6 : close es vacio

**2. Cadena de longitud 1:**

- T7 :  el carácter único en str coincide con la etiqueta open.

- T8 : el carácter único en str coincide con la etiqueta de close.

- T9 : el carácter único en str no coincide ni con la etiqueta de open ni con la de close.

- T10 : el carácter único en str coincide con las etiquetas de open y close.

**3. Ahora, cadena de longitud > 1, longitud de open = 1, longitud de close = 1.**

- T11 :  str no contiene ni la etiqueta de open ni la de close.

- T12 : str contiene la etiqueta open pero no contiene la etiqueta close.

- T13 : str contiene la etiqueta de close pero no contiene la etiqueta de open.

- T14 : str contiene las etiquetas de open y close.

- T15 : str contiene las etiquetas de open y close varias veces.

- T16 : str contiene las etiquetas de open y close pero no hay nada entre ellas.

**4. A continuación, cadena de longitud > 1, longitud de open > 1, longitud de close > 1:**

- T17 : str no contiene ni la etiqueta de open ni la de close

- T18 : str contiene la etiqueta open pero no contiene la etiqueta close.

- T19 : str contiene la etiqueta de close pero no contiene la etiqueta de open.

- T20 :  str contiene las etiquetas de open y close.

- T21 : str contiene las etiquetas de open y close varias veces.


```java
public class StringsUtilTest {
    
    @Test
    void strIsNullOrEmpty(){
        assertThat(StringsUtil.substringsBetween(null,"a","c")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("","a","c")).isEqualTo(new String[0]);
    }
    
    @Test
    void openIsNullOrEmpty(){
        assertThat(StringsUtil.substringsBetween("abc",null,"c")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("abc","","c")).isEqualTo(null);
    }
    
    @Test
    void closeIsNullOrEmpty(){
        assertThat(StringsUtil.substringsBetween("abc","a",null)).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("abc","a","")).isEqualTo(null);
    }
    
    @Test
    void strOfLenght1(){
        assertThat(StringsUtil.substringsBetween("a","a","b")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","b","a")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","b","b")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","a","a")).isEqualTo(null);
    }
    
    @Test
    void openAndCloseOfLenght1(){
        // ningun delimitador se encuentra
        assertThat(StringsUtil.substringsBetween("abc","x","y")).isEqualTo(null);
        // solo el delimitador "open" se encuentra
        assertThat(StringsUtil.substringsBetween("abc","a","y")).isEqualTo(null);
        // solo el delimitador "close" se encuentra
        assertThat(StringsUtil.substringsBetween("abc","x","c")).isEqualTo(null);
        // Los 2 delimitadores se encuentra pero no hay nada en el medio
        assertThat(StringsUtil.substringsBetween("abc","a","b")).isEqualTo(new String[]{""});
        // Los 2 delimitadores y hay una cadena en el medio
        assertThat(StringsUtil.substringsBetween("abc","a","c")).isEqualTo(new String[]{"b"});
        // str con espacios con delimitadores de longitud 1
        assertThat(StringsUtil.substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(new String[]{"b", "byt byr"});
    }
    
    @Test
    void openAndCloseTagsOfDifferentSizes() {
        // ninguno de los limitadores se encuentra
        assertThat(StringsUtil.substringsBetween("aabcc","xx","yy")).isEqualTo(null);
        // el delimitador "open" se encuentra
        assertThat(StringsUtil.substringsBetween("aabcc","aa","yy")).isEqualTo(null);
        // el delimitador "close" se encuentra
        assertThat(StringsUtil.substringsBetween("aabcc","xx","cc")).isEqualTo(null);
        // los dos delimitadores se encuentran con nada en el medio
        assertThat(StringsUtil.substringsBetween("aacc","aa","cc")).isEqualTo(new String[]{""});
        // los dos delimitadores se encuentra con algo en el medio
        assertThat(StringsUtil.substringsBetween("aabcc","aa","cc")).isEqualTo(new String[]{"b"});
        // str con espacios, para delimitadores con espacios
        assertThat(StringsUtil.substringsBetween("a abb ddc ca abbcc", "a a", "c c")).isEqualTo(new String[]{"bb dd"});
    }
}

/*
A diferencia del codigo inicial se elimino este test y se puso como un escenario en los otros test
ya que se considera que se podria comportar diferente para cuando los delimitadores son de tamanio 1
o mayor que 1.

@Test
void notSubstringBetweenOpenAndCloseTags() {
 assertThat(substringsBetween("aabb", "aa", "bb")).isEqualTo(new String[]{""});
}
 */
```

### Modificando al metodo

1. Modifica el método substringsBetween para que si la cadena str contiene caracteres
   especiales (como *, ?, !, etc.), los ignore y solo considere letras y números para la búsqueda
   de subcadenas.
2. Asegúrate de que todas las pruebas existentes aún pasen.
3. Escribe nuevas pruebas que validen esta funcionalidad.

```java
public static String[] substringsBetween(final String str, final String open, final String close) {
    // Si la cadena es nula o alguno de los strings delimitadores es empty retornamos null
    if (str == null || isEmpty(open) || isEmpty(close)) {
        return null;
    }
    // seleccionamos solo los caracteres alfanumericos
    final String sanitizedStr = str.replaceAll("[^a-zA-Z0-9]", "");
    final int strLen = sanitizedStr.length();
    // Si la cadena base es vacia (no hay nada que buscar)
    if (strLen == 0) {
        // entonces restornamos un array vacio
        return EMPTY_STRING_ARRAY;
    }
    // Caclulamos los tamanios de las cadenas de apertura y cierre
    final int closeLen = close.length();
    final int openLen = open.length();
    // Inicializamos un arrayList
    final List<String> list = new ArrayList<>();
    // Comenzamos desde la posicion inicial (desde el primer caracter)
    int pos = 0;
    while (pos < strLen - closeLen) {
        // Buscaremos la posicion de open en str desde el indice pos
        int start = sanitizedStr.indexOf(open, pos);
        // Si no se encontro la cadena "open" start=-1 < 0
        if (start < 0) {
            break;
        }
        // Si se encontro la cadena "open" entonces se toma el indice de la cadena restante
        // despues de tomar la cadena "open", que sera en donde se buscar la cadena "close"
        start += openLen;
        final int end = sanitizedStr.indexOf(close, start);
        // Si no se encontro la cadena "close" start=-1 < 0
        if (end < 0) {
            break;
        }
        // Si se encontro la cadena "close" se añade la cadena entre star y end
        list.add(sanitizedStr.substring(start, end));
        // Por ultimo se toma el indice del final de la cadena "close"
        pos = end + closeLen;
    }
    // Si la lista esta vacia retornamos null
    if (list.isEmpty()) {
        return null;
    }
    // Si no esta vacia retornamos list
    return list.toArray(EMPTY_STRING_ARRAY);
}
```

**Pruebas cambiadas:**

```java
@Test
void openAndCloseTagsOfDifferentSizes() {
    // ... linea 47
    assertThat(StringsUtil.substringsBetween("a abb ddc ca abbcc", "a a", "c c")).isEqualTo(null);
}
@Test
void openAndCloseOfLenght1(){
    // ... linea 63
    assertThat(StringsUtil.substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(new String[]{"b", "bytbyr"});
}

```


**Pruebas nuevas:**

```java
@Test
void ignoresSpecialCharacters() {
    assertThat(StringsUtil.substringsBetween("a*b?c!a@d", "a", "d")).isEqualTo(new String[] { "bca" });
}
```

###  Implementación de excepciones personalizadas

Instrucciones:
1. Crea una excepción personalizada llamada InvalidDelimiterException.
2. Modifica el método substringsBetween para lanzar esta excepción si las etiquetas open o
   close están vacías.
3. Escribe pruebas unitarias para asegurar que la excepción se lanza en los casos adecuados.

```java
public class StringsUtil {

    private static String[] EMPTY_STRING_ARRAY = new String[0];

    public static String[] substringsBetween(final String str, final String open, final String close) {
        // Si la cadena es nula retornamos null
        if (str == null ){
            return null;
        }
        // Si alguno de los delimitadores es empty retornamos una exception
        if (isEmpty(open) || isEmpty(close)) {
            throw new InvalidDelimiterException("Open or close delimiter cannot be empty");
        }
        final int strLen = str.length();
        // Si la cadena base es vacia (no hay nada que buscar)
        if (strLen == 0) {
            // entonces restornamos un array vacio
            return EMPTY_STRING_ARRAY;
        }
        // Caclulamos los tamanios de las cadenas de apertura y cierre
        final int closeLen = close.length();
        final int openLen = open.length();
        // Inicializamos un arrayList
        final List<String> list = new ArrayList<>();
        // Comenzamos desde la posicion inicial (desde el primer caracter)
        int pos = 0;
        while (pos < strLen - closeLen) {
            // Buscaremos la posicion de open en str desde el indice pos
            int start = str.indexOf(open, pos);
            // Si no se encontro la cadena "open" start=-1 < 0
            if (start < 0) {
                break;
            }
            // Si se encontro la cadena "open" entonces se toma el indice de la cadena restante
            // despues de tomar la cadena "open", que sera en donde se buscar la cadena "close"
            start += openLen;
            final int end = str.indexOf(close, start);
            // Si no se encontro la cadena "close" start=-1 < 0
            if (end < 0) {
                break;
            }
            // Si se encontro la cadena "close" se añade la cadena entre star y end
            list.add(str.substring(start, end));
            // Por ultimo se toma el indice del final de la cadena "close"
            pos = end + closeLen;
        }
        // Si la lista esta vacia retornamos null
        if (list.isEmpty()) {
            return null;
        }
        // Si no esta vacia retornamos list
        return list.toArray(EMPTY_STRING_ARRAY);
    }

    private static boolean isEmpty(String cadena){
        return cadena==null || cadena.isEmpty();
    }
}
```


```java
class StringsUtilTest {

    @Test
    void strIsNullOrEmpty(){
        assertThat(StringsUtil.substringsBetween(null,"a","c")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("","a","c")).isEqualTo(new String[0]);
    }

    @Test
    void throwsExceptionWhenOpenOrCloseIsEmpty() {
        assertThrows(InvalidDelimiterException.class, () -> {
            StringsUtil.substringsBetween("abc", "", "b");
        });
        assertThrows(InvalidDelimiterException.class, () -> {
            StringsUtil.substringsBetween("abc", "a", "");
        });
    }

    @Test
    void strOfLenght1(){
        assertThat(StringsUtil.substringsBetween("a","a","b")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","b","a")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","b","b")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","a","a")).isEqualTo(null);
    }

    @Test
    void openAndCloseOfLenght1(){
        // Los 2 delimitadores se encuentra pero no hay nada en el medio
        assertThat(StringsUtil.substringsBetween("abc","a","b")).isEqualTo(new String[]{""});
        // Los 2 delimitadores y hay una cadena en el medio
        assertThat(StringsUtil.substringsBetween("abc","a","c")).isEqualTo(new String[]{"b"});
        // str con espacios con delimitadores de longitud 1
        assertThat(StringsUtil.substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(new String[]{"b", "byt byr"});
    }

    @Test
    void openAndCloseTagsOfDifferentSizes() {
        // los dos delimitadores se encuentran con nada en el medio
        assertThat(StringsUtil.substringsBetween("aacc","aa","cc")).isEqualTo(new String[]{""});
        // los dos delimitadores se encuentra con algo en el medio
        assertThat(StringsUtil.substringsBetween("aabcc","aa","cc")).isEqualTo(new String[]{"b"});
        // str con espacios, para delimitadores con espacios
        assertThat(StringsUtil.substringsBetween("a abb ddc ca abbcc", "a a", "c c")).isEqualTo(new String[]{"bb dd"});
    }
    
}
```


### Optimización del método original

Optimiza el método substringsBetween para mejorar su eficiencia en términos de tiempo y espacio


```java
public static String[] substringsBetween(final String str, final String open, final String close) {
    if (str == null || isEmpty(open) || isEmpty(close)) {
        return null;
    }
    final int strLen = str.length();
    if (strLen == 0) {
        return EMPTY_STRING_ARRAY;
    }
    final int closeLen = close.length();
    final int openLen = open.length();
    final List<String> list = new ArrayList<>();
    int pos = 0;
    while (pos < strLen - closeLen) {
        int start = str.indexOf(open, pos);
        if (start < 0) {
            break;
        }
        start += openLen;
        final int end = str.indexOf(close, start);
        if (end < 0) {
            break;
        }
        list.add(str.substring(start, end));
        pos = end + closeLen;
    }
    if (list.isEmpty()) {
        return null;
    }
    return list.toArray(new String[list.size()]);
}
```
Comprobemos que la pruebas pasen

![img.png](Imagenes%2Fimg.png)

Veamoslo en el codigo : 

![img_1.png](Imagenes%2Fimg_1.png)
![img_2.png](Imagenes%2Fimg_2.png)

