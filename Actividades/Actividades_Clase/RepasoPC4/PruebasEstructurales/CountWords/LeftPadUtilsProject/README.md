# Pruebas Estructurales

### LeftPadUtils

```java
public class LeftPadUtils {
    private static final String SPACE = " ";
    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    /**
     * @param str
     * @param size
     * @param padStr
     * @return left-padded string or {@code null}
     */
    public static String leftPad(final String str, final int size, String padStr) {
        if (str == null) { // 1
            return null;
        }
        if (isEmpty(padStr)) { // 2
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) { // 3
            return str;
        }
        if (pads == padLen) { // 4
            return padStr.concat(str);
        } else if (pads < padLen) { // 5
            return padStr.substring(0, pads).concat(str);
        } else { // 6
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }
}

```

**Ejercicio 12: Explica los comentarios 1, 2, 3, 4 y 5 del código anterior.**

El metodo recibe como parametros:

- str : Cadena inicial (base).
- size : Tamaño de la cadena devuelta.
- padStr : Es la cadena que se va a insertar al inicio (por la derecha) a la cadena inicial.

1. Vefica si la cadena inicial es nula, si es asi, retorna una cadena nula.
2. Si la cadena a insertar esta vacia, entonces solo insertamos un espacio.
3. Si pads < 0, lo que indica que que el tamanio de la cadena devuelta es menor que 
la cadena incial, entonces retornar una subcadena de la cadena inicial.
4. Si pads = padLen, indica que el tamanio de la cadena devuelta calza perfectamente 
con el tamanio de la cadena incial y el padStr que se concatena al inicio.
5. Si pads < padLen, indica que el tamanio de la cadena devuelta sobrepasa lo que tiene 
permitido, por lo que solo se toma una subcadena de esta.
6. Si pads > padLen, indica que el tamanio de la cadena devuelta sobra por lo que ahora se repetiran un numero definido de veces hasta que se complete.

**Empezamos con las pruebas de especifiacion**

Veamos los valores indiviauales que puede tomar cada parametro:

Parámetro str:

    o Null
    o Cadena vacía
    o Cadena no vacía

• Parámetro size:

    o Número negativo
    o Número positivo

• Parámetro padStr:

    o Null
    o Vacío
    o No vacío

Ahora veamos como se pueden dar de manera conjunta:

• Parámetros str y size:
    
    o size < len(str)
    o size > len(str)

Evaluando los limites

• limites:

    o size es precisamente 0
    o str con longitud 1
    o padStr con longitud 1
    o size es precisamente la longitud de str

Podemos idear las siguientes pruebas para casos excepcionales: 

• T1: str es nulo

• T2: str está vacío

• T3: size negativo

• T4: padStr es nulo

• T5: padStr está vacío

• T6: padStr tiene un solo carácter

• T7: size es igual a la longitud de str

• T8: size es igual a 0

• T9: size es más pequeño que la longitud de str

Ahora las automatizamos:

```java
@ParameterizedTest
@MethodSource("generator")
void test(String originalStr, int size, String padString, String expectedStr) { // 1
    assertThat(LeftPadUtils.leftPad(originalStr, size, padString))
            .isEqualTo(expectedStr);
}

static Stream<Arguments> generator() { // 2
    return Stream.of(
            Arguments.of(null, 10, "-", null), // T1
            Arguments.of("", 5, "-", "-----"), // T2
            Arguments.of("abc", -1, "-", "abc"), // T3
            Arguments.of("abc", 5, null, " abc"), // T4
            Arguments.of("abc", 5, "", " abc"), // T5
            Arguments.of("abc", 5, "-", "--abc"), // T6
            Arguments.of("abc", 3, "-", "abc"), // T7
            Arguments.of("abc", 0, "-", "abc"), // T8
            Arguments.of("abc", 2, "-", "abc") // T9
    );
}

```

![img.png](Imagenes%2Fimg.png)




Es hora de aumentar el conjunto de pruebas a través de pruebas estructurales. Usemos una
herramienta de cobertura de código para decirnos lo que ya hemos cubierto. Analiza el informe y
responde qué sucede con las expresiones if (pads == padLen) y else if (pads < padLen).

![img_1.png](Imagenes%2Fimg_1.png)

![img_2.png](Imagenes%2Fimg_2.png)

No existe ninguna prueba que considere que la cantidad de "espacios libres" que existen
tengan el mismo tamaño que la cadena con la que se van a rellenar estos espacios, ademas tampoco
existe en el caso en el que la cadena con la que se va a rellenar los espacios en blanco es mayor 
que los espacios en blancos existentes.


Agregamos T10, T11 y T12.
Un test evalua que la cadena de salida --abc tenga justamente 5 caracteres, lo cual encaja perfectamente.
Los otros dos verfican, las otras 2 condiciones, cuando se sobrepasa y cuando sobra.

Ahora podemos derivar 3 casos de prueba :

• T10: la longitud de padStr es igual a los espacios restantes en str.

• T11: la longitud de padStr es mayor que los espacios restantes en str.

• T12: la longitud de padStr es más pequeña que los espacios restantes en str (esta prueba
puede ser similar a T6).


```java
static Stream<Arguments> generator() {
    return Stream.of(
    // ... otros casos de prueba aquí
    Arguments.of("abc", 5, "--", "--abc"), // T10
    Arguments.of("abc", 5, "---", "--abc"), // T11
    Arguments.of("abc", 5, "-", "--abc") // T12
    );
}
```

Podemos agregar tambien el caso en el que el tamaño de la cadena original sea mayor que el
tamaño de la cadena de salida, es decir, pads<0.

```java
@Test
void sameInstance() {
    String str = "sometext";
    assertThat(LeftPadUtils.leftPad(str, 5, "-")).isSameAs(str);
}
```

Volvemos a ejecutar la herramiena jacoco

![img_3.png](Imagenes%2Fimg_3.png)
![img_4.png](Imagenes%2Fimg_4.png)
![img_5.png](Imagenes%2Fimg_5.png)

Podemos ver que ahora tenemos una cobertura del 100%.