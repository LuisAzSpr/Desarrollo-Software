# Pruebas estructurales

### clumps

```java
public class Clumps {
/**
 * @param nums
 *
 * @return …
 */
public static int countClumps(int[] nums) {
    if (nums == null || nums.length == 0) { // 1
        return 0;
    }
    int count = 0;
    int prev = nums[0];
    boolean inClump = false;
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] == prev && !inClump) { // 2
            inClump = true;
            count += 1;
        }
        if (nums[i] != prev) { // 3
            prev = nums[i];
            inClump = false;
        }
    }
    return count;
}
}
```

Pregunta 16 : explica las líneas 1, 2 y 3 del código anterior.

En la linea 1 se verifica si el arreglo es nulo o esta vacio en ese caso retorna 0.

En la linea 2, verifica si el numero anterior y el numero actual en el arreglo son iguales y aun no estamos en un clump, en ese caso
inclump = true (indicamos que estamos dentro de un clump) y aumentamos la cantidad de grupos en 1.

En la linea 3 verificamos si el numero anterior es diferente del actual, en ese caso se termina el clump, es decir, inClump=false.

Supongamos que decidimos no mirar los requisitos. Queremos lograr, digamos, el 100% de
cobertura de ramas. Tres pruebas son suficientes para hacer eso (T1-T3). Tal vez también queramos
hacer algunas pruebas de límites adicionales y decidamos ejercitar el bucle, iterando una sola vez
(T4):

• T1: una matriz vacía

• T2: una matriz nula

• T3: una matriz con un s olo grupo de tres elementos en el medio (por ejemplo, [1,2,2,2,1])

• T4: una matriz con un solo elemento

```java
public class ClumpsOnlyStructuralTest {
    @ParameterizedTest
    @MethodSource("generator")
    void testClumps(int[] nums, int expectedNoOfClumps) {
        assertThat(Clumps.countClumps(nums))
                .isEqualTo(expectedNoOfClumps);
    }
    static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(new int[]{}, 0), // T1
                Arguments.of(null, 0), // T2
                Arguments.of(new int[]{1,2,2,2,1}, 1), // T3
                Arguments.of(new int[]{1}, 0), // T4
        );
    }
}


```

![img.png](Imagenes%2Fimg.png)

Vemos que se logra una captura del 100% lo que indica que todo nuestro codigo esta siendo probado.

![img_1.png](Imagenes%2Fimg_1.png)
![img_2.png](Imagenes%2Fimg_2.png)

**Las pruebas estructurales muestran su valor cuando se combinan con el conocimiento de la
especificación.**

Ahora derivamos tres casos de prueba más:

• T10: la longitud de padStr es igual a los espacios restantes en str.

• T11: la longitud de padStr es mayor que los espacios restantes en str.

• T12: la longitud de padStr es más pequeña que los espacios restantes en str (esta prueba
puede ser similar a T6).

```java
Arguments.of(new int[]{1,1,2,2,3,3}, 3), // T10: 3 grupos
Arguments.of(new int[]{1,1,1,2,2,2,3,3,3}, 3), // T11: 3 grupos grandes
Arguments.of(new int[]{1,2,2,2,3,3,4,4,4}, 3) // T12: 3 grupos separados
```
Ejecutamos todo y los resultados serian los siguientes:

```java
public class ClumpsOnlyStructuralTest {
    @ParameterizedTest
    @MethodSource("generator")
    void testClumps(int[] nums, int expectedNoOfClumps) {
        assertThat(Clumps.countClumps(nums))
                .isEqualTo(expectedNoOfClumps);
    }

    static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(new int[]{}, 0), // T1
                Arguments.of(null, 0), // T2
                Arguments.of(new int[]{1,2,2,2,1}, 1), // T3
                Arguments.of(new int[]{1}, 0), // T4
                Arguments.of(new int[]{1,1,2,2,3,3}, 3), // T10: 3 grupos
                Arguments.of(new int[]{1,1,1,2,2,2,3,3,3}, 3), // T11: 3 grupos grandes
                Arguments.of(new int[]{1,2,2,2,3,3,4,4,4}, 3) // T12: 3 grupos separados
        );
    }

}
```
![img_3.png](Imagenes%2Fimg_3.png)

![img_4.png](Imagenes%2Fimg_4.png)
