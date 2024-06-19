# Actividad pruebas Basadas en especificaciones

### Number utils

```java
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
        if(leftDigit<0 || leftDigit>9 || rightDigit<0 || leftDigit>9){
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
```

**Ejercicio 12:** Explica el funcionamiento del algoritmo anterior.

• T1 = [1] + [1] = [2]

• T2 = [1,5] + [1,0] = [2,5]

• T3 = [1,5] + [1,5] = [3,0]

• T4 = [5,0,0] + [2,5,0] = [7,5,0]

El algoritmo anterior suma un arreglo de numeros en sus posiciones respectivas
llevando el carry de derecha a izquierda.

Ejercicio 13: Escribe un programa llamado NumberUtilsNonSystematicTest.java de la siguiente
manera:

```java
public class NumberUtilsNonSystematicTest {
    
    @Test
    void t1() {
        assertThat(new NumberUtils().add(numbers(1), numbers(1)))
            .isEqualTo(numbers(2));
        assertThat(new NumberUtils().add(numbers(1,5), numbers(1,0)))
            .isEqualTo(numbers(2, 5));
        assertThat(new NumberUtils().add(numbers(1,5), numbers(1,5)))
            .isEqualTo(numbers(3,0));
        assertThat(new NumberUtils().add(numbers(5,0,0), numbers(2,5,0)))
            .isEqualTo(numbers(7,5,0));
    }
    private static List<Integer> numbers(int... nums) {
        List<Integer> list = new ArrayList<>();
        for(int n : nums) {
            list.add(n);
        }
        return list;
    }
}

```
Podemos ver que todos los escenarios de la primera prueba pasan

![img.png](Imagenes%2Fimg.png)

#### Posibles valores de left y right

Ahora veamos las posibles entradas que tiene la funcion, ambos tienen el mismo conjunto de valores posibles.

    left :
    - Vacio
    - Nulo
    - Un solo digito
    - Multiples digitos sin ceros a la izquierda
    - Multiples digitos con ceros a la izquierda

    right :
    - Vacio
    - Nulo
    - Un solo digito
    - Multiples digitos sin ceros a la izquierda
    - Multiples digitos con ceros a la izquierda

#### Posibles entradas y funcionalidades

1. **Por el tamaño de left y right**

    Pueden tener diferentes tamaños y el programa debe manejarlos
    
        - size left > size right
        - size left < size right
        - size left = size right

2. **Por si existe acarreo o no**

    La suma puede no usar el acarreo, usarlo una vez al principio, al medio, al final o multiples veces.

         - Suma sin acarreo
         - Suma con acarreo : un acarreo al principio
         - Suma con acarreo : un acarreo en el medio
         - Suma con acarreo : muchos acarreos
         - Suma con acarreo : muchos acarreos, no seguidos
         - Suma con carreo : acarreo propagado a un digito nuevo (el mas significativo)

    El único límite que vale la pena probar es el siguiente: garantizar que casos como 99 + 1.


#### Caso de pruebas concretos:

**Nulos y vacíos**

• T1: left nula

• T2: left vacío

• T3: right nulo

• T4: right vacío

**Dígitos individuales**

• T5: un solo dígito, sin acarreo

• T6: un solo dígito, con acarreo

**Múltiples dígitos**

• T7: sin acarreo

• T8: acarreo en el dígito menos significativo

• T9: acarreo en el medio

• T10: muchos acarreos

• T11: muchos acarreos, no seguidos

• T12: acarreo propagado a un dígito nuevo (ahora el más significativo)

**Múltiples dígitos con diferentes longitudes (uno para el left más largo que right y otro para right
más largo que left)**

• T13: sin acarreo

• T14: acarreo en el dígito menos significativo

• T15: acarreo en el medio

• T16: muchos acarreos

• T17: muchos acarreos, no seguidos

• T18: acarreo propagado a un dígito nuevo (ahora el más significativo)

**Ceros a la izquierda**

• T19: sin acarreo

• T20: con acarreo

**Límites**

• T21: acarreo a un nuevo dígito más significativo, por uno (como 99 +1).



**Ejercicio 14:** Transforma los casos de prueba automatizados, creando una prueba parametrizada
llamada NumberUtilsTest.java

Introduciremos esta assertion personalizada para que sea mas facil de probar y ayudarnos a ahorrar tiempo en 
escribir codigo.

```java
private void assertThatNumbers(List<Integer> integersR,List<Integer> integersL,List<Integer> expected){
    NumberUtils numberUtils = new NumberUtils();
    List<Integer> resultado = numberUtils.add(integersR,integersL);
    assertThat(resultado).isEqualTo(expected);
}
```

Introduciremos todas estas pruebas:

```java
@Test
void nulosYVaciosTest(){
    assertThatNumbers(null,numbers(1,2,3),null); // T1
    assertThatNumbers(numbers(),numbers(1,2,3),numbers(1,2,3)); // T2
    assertThatNumbers(numbers(1,2,3),null,null); // T3
    assertThatNumbers(numbers(),numbers(1,2,3),numbers(1,2,3)); // T4
}
@Test
void digitosIndividuales(){
    assertThatNumbers(numbers(1),numbers(4),numbers(5)); // T5
    assertThatNumbers(numbers(4),numbers(8),numbers(1,2)); // T6
}
@Test
void multiplesDigitosConLaMismaLongitud(){
    assertThatNumbers(numbers(2,4),numbers(3,5),numbers(5,9)); //T7
    assertThatNumbers(numbers(1,2,5),numbers(2,3,5),numbers(3,6,0)); // T8
    assertThatNumbers(numbers(1,5,0),numbers(3,5,0),numbers(5,0,0)); // T9
    assertThatNumbers(numbers(2,3,4,5),numbers(1,6,5,5),numbers(4,0,0,0)); // T10
    assertThatNumbers(numbers(4,5,6,7,8),numbers(2,2,4,1,2),numbers(6,8,0,9,0));// T11
    assertThatNumbers(numbers(9,2),numbers(1,3),numbers(1,0,5)); // T12
}

@Test
void multiplesDigitosConDiferentesLongitudes(){
    assertThatNumbers(numbers(1,2,4),numbers(3,5),numbers(1,5,9)); //T13
    assertThatNumbers(numbers(1,2,5),numbers(3,5),numbers(1,6,0)); // T14
    assertThatNumbers(numbers(4,1,5,0),numbers(3,5,0),numbers(4,5,0,0)); // T15
    assertThatNumbers(numbers(2,3,4,5),numbers(6,5,5),numbers(3,0,0,0)); // T16
    assertThatNumbers(numbers(4,5,6,7,8),numbers(4,1,2),numbers(4,6,0,9,0));// T17
    assertThatNumbers(numbers(9,2),numbers(9),numbers(1,0,1)); // T18
}
@Test
void cerosAlaIzquierda(){
    assertThatNumbers(numbers(1,2,3,4),numbers(0,0,1,2),numbers(1,2,4,6)); // T19
    assertThatNumbers(numbers(4,1,6,3),numbers(0,0,3,7),numbers(4,2,0,0)); // T20
}
@Test
void limites(){
    assertThatNumbers(numbers(9,9),numbers(1),numbers(1,0,0)); // T21
}
```

Agregamos las pruebas que soportan a las excepciones

```java
@ParameterizedTest
@MethodSource("digitsOutOfRange")
void shouldThrowExceptionWhenDigitsAreOutOfRange(List<Integer> left, List<Integer> right) {
    assertThatThrownBy(() -> new NumberUtils().add(left, right))
            .isInstanceOf(IllegalArgumentException.class);
}

static Stream<Arguments> digitsOutOfRange() {
    return Stream.of(
            Arguments.of(numbers(1,-1,1), numbers(1)),
            Arguments.of(numbers(1), numbers(1,-1,1)),
            Arguments.of(numbers(1,11,1), numbers(1)),
            Arguments.of(numbers(1), numbers(1,11,1))
    );
}
```

Nuestro conjunto de pruebas terminaria asi:

```java
class NumberUtilsTest {
    @Test
    void nulosYVaciosTest(){
        assertThatNumbers(null,numbers(1,2,3),null); // T1
        assertThatNumbers(numbers(),numbers(1,2,3),numbers(1,2,3)); // T2
        assertThatNumbers(numbers(1,2,3),null,null); // T3
        assertThatNumbers(numbers(),numbers(1,2,3),numbers(1,2,3)); // T4
    }
    @Test
    void digitosIndividuales(){
        assertThatNumbers(numbers(1),numbers(4),numbers(5)); // T5
        assertThatNumbers(numbers(4),numbers(8),numbers(1,2)); // T6
    }
    @Test
    void multiplesDigitosConLaMismaLongitud(){
        assertThatNumbers(numbers(2,4),numbers(3,5),numbers(5,9)); //T7
        assertThatNumbers(numbers(1,2,5),numbers(2,3,5),numbers(3,6,0)); // T8
        assertThatNumbers(numbers(1,5,0),numbers(3,5,0),numbers(5,0,0)); // T9
        assertThatNumbers(numbers(2,3,4,5),numbers(1,6,5,5),numbers(4,0,0,0)); // T10
        assertThatNumbers(numbers(4,5,6,7,8),numbers(2,2,4,1,2),numbers(6,8,0,9,0));// T11
        assertThatNumbers(numbers(9,2),numbers(1,3),numbers(1,0,5)); // T12
    }

    @Test
    void multiplesDigitosConDiferentesLongitudes(){
        assertThatNumbers(numbers(1,2,4),numbers(3,5),numbers(1,5,9)); //T13
        assertThatNumbers(numbers(1,2,5),numbers(3,5),numbers(1,6,0)); // T14
        assertThatNumbers(numbers(4,1,5,0),numbers(3,5,0),numbers(4,5,0,0)); // T15
        assertThatNumbers(numbers(2,3,4,5),numbers(6,5,5),numbers(3,0,0,0)); // T16
        assertThatNumbers(numbers(4,5,6,7,8),numbers(4,1,2),numbers(4,6,0,9,0));// T17
        assertThatNumbers(numbers(9,2),numbers(9),numbers(1,0,1)); // T18
    }
    @Test
    void cerosAlaIzquierda(){
        assertThatNumbers(numbers(1,2,3,4),numbers(0,0,1,2),numbers(1,2,4,6)); // T19
        assertThatNumbers(numbers(4,1,6,3),numbers(0,0,3,7),numbers(4,2,0,0)); // T20
    }
    @Test
    void limites(){
        assertThatNumbers(numbers(9,9),numbers(1),numbers(1,0,0)); // T21
    }

    @ParameterizedTest
    @MethodSource("digitsOutOfRange")
    void shouldThrowExceptionWhenDigitsAreOutOfRange(List<Integer> left, List<Integer> right) {
        assertThatThrownBy(() -> new NumberUtils().add(left, right))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void assertThatNumbers(List<Integer> integersR, List<Integer> integersL, List<Integer> expected){
        NumberUtils numberUtils = new NumberUtils();
        List<Integer> resultado = numberUtils.add(integersR,integersL);
        assertThat(resultado).isEqualTo(expected);
    }
    private static List<Integer> numbers(int... nums){
        List<Integer> list = new ArrayList<>();
        for(int n:nums){
            list.add(n);
        }
        return list;
    }

    static Stream<Arguments> digitsOutOfRange() {
        return Stream.of(
                Arguments.of(numbers(1,-1,1), numbers(1)),
                Arguments.of(numbers(1), numbers(1,-1,1)),
                Arguments.of(numbers(1,11,1), numbers(1)),
                Arguments.of(numbers(1), numbers(1,11,1))
        );
    }
}
```

Podemos ver que todas pasan:

![img_1.png](Imagenes%2Fimg_1.png)


**Ejercicio 15:** Analiza los códigos de cobertura antes y después de los cambios.




