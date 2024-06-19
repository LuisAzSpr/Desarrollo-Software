# Actividad Pruebas Estructurales

### CountWords

```java
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
}

```

**Ejercicio 1: Explica qué hacen las líneas 1, 2 y 3 en el código.**

Recorre todo el texto, almacenando siempre el  caracter anterior al caracter en la posicion actual,
en caso el caracter actual no sea una letra (lo que  indica el fin de una palabra) y la ultima letra
(ultima letra de la palabra) sea 's' o 't' entonces words incrementa en uno.

```java
class CountWordsTest {
    @Test
    void twoWordsEndingWithS() { // 1
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }
    @Test
    void noWordsAtAll() { // 2
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }
}

```

**Ejercicio 2: Explica qué hacen las líneas 1 y 2 del código. Presenta un informe generado por JaCoCo
(www.jacoco.org/jacoco) u otra herramienta de cobertura de código de tu preferencia en el IDE del
curso.**

La primera prueba evalua una cadena de texto con  2 palabras (dogs y cats) y se espera que la cantidad
de palabras que terminen en 's' o 'r' sean 2.

La segunda prueba evalua una cadena de texto con  las palabras dog y cat y se espera que la cantidad
de palabras que terminen en 's' o 'r' sea 0.

Veamos ahora el analisis de cobertura con jacoco

![img.png](Imagenes%2Fimg.png)

Veamos la clase CountWords que es nuestra clase de interes

![img_1.png](Imagenes%2Fimg_1.png)

Vemos que en CountWords no todo se ha ejecutado como se debe (las ramas no se han ejecutado), dado que 
ninguna de las pruebas pasa como parametro una palabra que temrine en 's' entonces no se esta ejecutando
cada una de las ramas como se debe.

![img_2.png](Imagenes%2Fimg_2.png)

Para esto podemos agregar nuevas pruebas

```java
@Test
void wordsThatEndInR() { // 1
    int words = new CountWords().count("car bar");
    assertThat(words).isEqualTo(2);
}
```

**Ejercicio 3: Explica la línea 1 y con el caso de prueba recién agregado en el conjunto de pruebas,
vuelve a ejecutar la herramienta de cobertura. Explica los cambios obtenidos.
Con el caso de prueba recién agregado en el conjunto de pruebas, vuelve a ejecutar la herramienta
de cobertura. Explica los cambios obtenidos y si hay partes del código que aún no estén cubiertas**

Habiendo agregado una nueva prueba que toma en cuenta  las palabras que terminan en 'r', ahora estamos
abarcando la proposicion last=='r'.

**Ejecutando la herramienta jacoco**: Podemos ver ahora que la cobertura es del 100% y todas las ramas han sido cubiertas

![img_3.png](Imagenes%2Fimg_3.png)


![img_4.png](Imagenes%2Fimg_4.png)


Lo mas importante es que hemos tenido un coverage del  100% lo que quiere decir que todo nuestro codigo
esta probado.

**Ejercicio4**

**ParteA**

1. **Explica qué hacen las líneas 1, 2 y 3 en el código.**


 Recorre todo el texto, almacenando siempre el 
 caracter anterior al caracter en la posicion actual, 
 en caso el caracter actual no sea una letra (lo que 
 indica el fin de una palabra) y la ultima letra
 (ultima letra de la palabra) sea 's' o 't' entonces 
 words incrementa en uno.

2. **¿Qué sucedería si se eliminara la línea 3 del código?**

Si eliminamos la linea 3 del codigo, la ultima letra  de cada palabra siempre seria un caracter ' ', por lo  que nunca entraria a ninguna condicional y el metodo
count retornaria 0.


3. **Escribe una descripción de alto nivel de lo que hace este método count**

El metodo count, cuenta todas las palabras de un
texto que terminen en 's' o en 'r', toma como entrada
el texto y devuelve el numero de palabras que cumplen
con la condicion anterior.

**ParteB**

```java
@Test
void twoWordsEndingWithS() { // 1
    int words = new CountWords().count("dogs cats");
    assertThat(words).isEqualTo(2);
}
@Test
void noWordsAtAll() { // 2
    int words = new CountWords().count("dog cat");
    assertThat(words).isEqualTo(0);
}
```

**4. Explica qué hacen las líneas 1 y 2 del código de prueba.**

La primera prueba evalua una cadena de texto con
2 palabras (dogs y cats) y se espera que la cantidad
de palabras que terminen en 's' o 'r' sean 2, ya que
ambas palabras terminan en 's'.

La segunda prueba evalua una cadena de texto con
las palabras dog y cat y se espera que la cantidad
de palabras que terminen en 's' o 'r' sea 0, ya que
ninguna palabra termina en 's' o 'r'.

**5. ¿Qué tipos de casos de prueba adicionales se deberían agregar para mejorar la cobertura?**

Se deberia agregar una prueba que evalue los casos
en los que existen palabras que terminan en 'r'.

**ParteC**

**6. Ejecuta las pruebas unitarias usando JaCoCo o una herramienta de cobertura de código de tu
   elección.**
**7. Genera y presenta el informe de cobertura.**


![img_2.png](Imagenes%2Fimg_2.png)

Jacoco nos da un informe de cobertura, para ver
cuales son las partes del codigo que estan siendo
probadas, completamente(verde) o parcialmente
(amarillo) o si de plano no existe prueba que cubra
cierta parte del codigo(rojo)


8. **Identifica las partes del código que no están cubiertas por las pruebas.**

La linea 8 y la linea 13, estan siendo parcialmente
probadas por las pruebas ya que, al no existir
ninguna prueba que verifique para una palabra
que termina en 'r' entonces esta proposicion
nunca llega a ser verdadera, lo que implica que
no estamos probando ese escenario.

**Ejercicio5**

Escribe nuevas pruebas unitarias para cubrir los casos que no están cubiertos actualmente. Usa el
siguiente formato como guía.

```java
@Test
void wordsThatEndInR() { // 1
    int words = new CountWords().count("car bar");
    assertThat(words).isEqualTo(2);
}
```

Parte B: Ejecución de herramienta de cobertura

1. **Agrega las nuevas pruebas al conjunto de pruebas.**
2. **Vuelve a ejecutar la herramienta de cobertura.**
3. **Explica los cambios obtenidos en el informe de cobertura.**
4. **Si todavía hay partes del código no cubiertas, repite el proceso: identifica las partes no
   cubiertas, comprende por qué no están cubiertas y escribe una prueba que ejerza esa parte
   del código.**

![img_3.png](Imagenes%2Fimg_3.png)
![img_3.png](Imagenes%2Fimg_4.png)

Habiendo agregado una nueva prueba que toma en cuenta
las palabras que terminan en 'r', ahora estamos
abarcando la proposicion last=='r'.

 Por lo que podemos concluir que el codigo funciona
 como se esperaba ya no solo con palabras que terminen
 en 's' o no, sino que ahora tambien para palabras que
 terminen en 'r'.

**Ejercicio 6:** Exploración y mejora

Parte A : Exploracion

1. **Modifica el método count para mejorar su claridad o eficiencia.**
2. **Escribe pruebas unitarias adicionales para asegurar que las modificaciones no rompan la
   funcionalidad existente.**


Hemos realizado los siguientes cambios:

- Hemos agregado un constructor CountWords que verifique que el texto siga un cierto formato (terminar en una letra y no estar vacio).

- En el metodo CountWords hemos agregado un caracter en blanco al final de la cadena para que no sea necesario validar de nuevo para la ultima palabra de la cadena y
hacer un codigo mas entendible.

- En el metodo CountWords hemos encapsulado la condicional que verifica si la ultima palabra termina o no en r o s en el metodo lastWordEndsInRorS.

```java
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

```
Escribe pruebas unitarias adicionales

```java
class CountWordsTest {
    @Test
    void twoWordsEndingWithS() { // 1
        int words = new CountWords("dogs cats").count();
        assertThat(words).isEqualTo(2);
    }
    @Test
    void noWordsAtAll() { // 2
        int words = new CountWords("dog cat").count();
        assertThat(words).isEqualTo(0);
    }

    @Test
    void wordsThatEndInR() { // 1
        int words = new CountWords("car bar").count();
        assertThat(words).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"","este texto no es valido.","este texto no es valido "})
    void incorrectFormatTextShouldThrowException(String text){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->{
                    new CountWords(text);
                });
    }
}

```
Vemos que todas las pruebas pasan

![img_5.png](Imagenes%2Fimg_5.png)

Parte B: Informe final

3. **Genera un informe final de cobertura después de todas las modificaciones y nuevas pruebas.**
4. **Compara el informe final con el informe inicial y discute los cambios y mejoras en la
   cobertura de código.**
En el informe final hemos mejorado ciertos puntos, entre estos se encuentran:

Hemos reducido la complejidad del metodo count haciendolo mas facil de leer.
Hemos agregado un constructor que verifica el formato del texto, con su respectiva prueba que espera un excepcion en caso el texto no tenga el formato adecuado.
Ambos informes tiene una covertura del 100%, por lo que los cambios agregados tambien se estan probando adecuadamente.

![img_6.png](Imagenes%2Fimg_6.png)

![img_7.png](Imagenes%2Fimg_7.png)

Tipos de cobertura :

1. **Cobertura de linea** : cantidad de líneas ejecutables en el código fuente que son ejecutadas
   al menos una vez durante la ejecución de las pruebas.
2. **Cobertura de ramas** : mide si cada rama de las
   estructuras de control de flujo ha sido ejecutada al menos una vez durante
   la ejecución de las pruebas.  Esto implica que tanto el camino verdadero como el falso de cada
   declaración de rama deben ser ejecutados.
3. **Condición + cobertura de rama** : mide si todas las condiciones dentro de una declaración de rama
   han sido evaluadas como verdaderas y falsas al menos una vez, que cada condición individual dentro de una declaración de rama y la declaración de rama
   completa sean probadas en todas las combinaciones posibles.
4. **Cobertura de rutas** : Cubre todas las combinaciones posibles de rutas de ejecución en el programa.

**Ejercicio 8: Cobertura de línea**

Asegura que cada línea de código en el método count de la clase CountWords esté cubierta por al
menos una prueba unitaria.

Creamos las siguientes pruebas : 

```java
@Test
void testCountWordsEndingWithS() {
    int words = new CountWords().count("dogs cats");
    assertThat(words).isEqualTo(2);
}

@Test
void testCountWordsEndingWithR() {
    int words = new CountWords().count("car bar");
    assertThat(words).isEqualTo(2);
}
@Test
void testCountNoWordsEndingWithSOrR() {
    int words = new CountWords().count("dog cat");
    assertThat(words).isEqualTo(0);
}
@Test
void testEmptyString() {
    int words = new CountWords().count("");
    assertThat(words).isEqualTo(0);
}
```
Veamos ahora la cobertura en jacoco.

![img_10.png](Imagenes%2Fimg_10.png)
![img_9.png](Imagenes%2Fimg_9.png)

Podemos ver que la cobertura de rutas es del 100%, todo nuestro codigo esta siendo probado
correctamente con estas 4 pruebas, lo cual implica que estos tests son suficientes (por ahora),
si hablamos de pruebas estructurales.

**Ejercicio 9: Cobertura de ramas**

Asegurar que cada rama del código en el método count esté cubierta por al menos una prueba
unitaria.

Se añaden las siguientes nuevas pruebas

```java
@Test
void testCountWordsWithSpecialCharacters() {
    int words = new CountWords().count("dog's car!");
    assertThat(words).isEqualTo(1);
}

@Test
void testCountSingleCharacterS() {
    int words = new CountWords().count("s");
    assertThat(words).isEqualTo(1);
}

@Test
void testCountSingleCharacterR() {
    int words = new CountWords().count("r");
    assertThat(words).isEqualTo(1);
}

@Test
void testCountSingleCharacterNonLetter() {
    int words = new CountWords().count("1");
    assertThat(words).isEqualTo(0);
}
```

Podemos ver que la prueba de caracteres especiales falla :

![img_11.png](Imagenes%2Fimg_11.png)

Expected : 1

Actual : 2

Esto es porque se esta considerando dog's como una unica palabra, lo cual es correcto
por lo que cambiaremos la prueba a 2.

```java
void testCountWordsWithSpecialCharacters() {
    int words = new CountWords().count("dog's car!");
    assertThat(words).isEqualTo(2);
}
```

Explicacion :
La cobertura de codigo anterior fue del 100% en cobertura de rutas, esto quiere
decir que tenemos 100% en cobertura de ramas. Por lo que al añadir mas pruebas no
cambiarian el porcentaje maximo alcanzado de cobertura. Las pruebas anteriores parecen
tener mas relacion con pruebas de especificaciones ya que se esta probando ciertos tipos
de entradas como una cadena vacia, caracteres especiales, un caracter, etc.
Otra forma de verlo seria tomar en consideracion la cantidad de veces que se ejecutara lo que esta dentro del bucle,
cuando word tiene una unica letra entonces se prueba 1 unica vez, cuando word tiene mas de una


**Ejercicio 10:** Condición + cobertura de rama

Asegurar que cada condición y cada rama del código en el método count esté cubierta por al menos
una prueba unitaria

Se añaden las siguientes nuevas pruebas

```java
@Test
void testCountWordsEndingWithRAndS() {
    int words = new CountWords().count("runners dogs cars");
    assertThat(words).isEqualTo(3);
}

@Test
void testCountWordsEndingWithNeither() {
    int words = new CountWords().count("hello world");
    assertThat(words).isEqualTo(0);
}

@Test
void testCountWordsEndingWithSAndSpecialChar() {
    int words = new CountWords().count("dogs cats@");
    assertThat(words).isEqualTo(2);
}
```

**Ejercicio 11: Cobertura de rutas**

Asegurar que todas las rutas posibles de ejecución en el método count estén cubiertas por al menos
una prueba unitaria.

Se añaden las siguientes nuevas pruebas

```java
@Test
void testCountWordsStartingWithNonLetter() {
    int words = new CountWords().count("1dogs 2cats");
    assertThat(words).isEqualTo(2);
}
@Test
void testCountWordsWithSpaces() {
    int words = new CountWords().count("dogs cats");
    assertThat(words).isEqualTo(2);
}
```

Terminamos con todo este set de pruebas:

```java
class CountWordsTest {
    
    @Test
    void testCountWordsEndingWithS() {
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }
    
    @Test
    void testCountWordsEndingWithR() {
        int words = new CountWords().count("car bar");
        assertThat(words).isEqualTo(2);
    }
    
    @Test
    void testCountNoWordsEndingWithSOrR() {
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }
    
    @Test
    void testCountWordsWithSpecialCharacters() {
        int words = new CountWords().count("dog's car!");
        assertThat(words).isEqualTo(2);
    }
    
    @Test
    void testEmptyString() {
        int words = new CountWords().count("");
        assertThat(words).isEqualTo(0);
    }
    
    @Test
    void testCountSingleCharacterS() {
        int words = new CountWords().count("s");
        assertThat(words).isEqualTo(1);
    }
    
    @Test
    void testCountSingleCharacterR() {
        int words = new CountWords().count("r");
        assertThat(words).isEqualTo(1);
    }
    
    @Test
    void testCountSingleCharacterNonLetter() {
        int words = new CountWords().count("1");
        assertThat(words).isEqualTo(0);
    }

    @Test
    void testCountWordsEndingWithRAndS() {
        int words = new CountWords().count("runners dogs cars");
        assertThat(words).isEqualTo(3);
    }

    @Test
    void testCountWordsEndingWithNeither() {
        int words = new CountWords().count("hello world");
        assertThat(words).isEqualTo(0);
    }

    @Test
    void testCountWordsEndingWithSAndSpecialChar() {
        int words = new CountWords().count("dogs cats@");
        assertThat(words).isEqualTo(2);
    }

    @Test
    void testCountWordsStartingWithNonLetter() {
        int words = new CountWords().count("1dogs 2cats");
        assertThat(words).isEqualTo(2);
    }
    
    @Test
    void testCountWordsWithSpaces() {
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }
}
```

Veamos que todas las pruebas pasan:

![img_12.png](Imagenes%2Fimg_12.png)

