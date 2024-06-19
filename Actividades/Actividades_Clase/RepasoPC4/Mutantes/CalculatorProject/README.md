# Mutantes

Define los siguientes términos:
- Mutante : 
- Matar un mutante : 
- Sobrevivir un mutante : 
- Cobertura de mutación :

Explica las dos suposiciones principales que hacen las pruebas de mutación:

- Hipótesis del programador competente : 
- Efecto de acoplamiento : 

Ejercicio 23: Introducción a PIT (Pitest)
Configurar y ejecutar pruebas de mutación utilizando PIT en un proyecto Java.

```
plugins {
    id 'java'
    id 'info.solidsoft.pitest' version '1.15.0'
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.assertj:assertj-core:3.25.3'
    pitest 'org.pitest:pitest-junit5-plugin:1.1.0'
}

test {
useJUnitPlatform()
}

pitest {
    targetClasses = ['org.example.*'] // Paquete de clases a mutar
    mutators = ['DEFAULTS'] // Conjunto de mutadores [OLD_DEFAULTS, DEFAULTS, STRONGER, ALL]
    outputFormats = ['HTML'] // Formato de salida del informe
    timestampedReports = false // Deshabilitar informes con marca de tiempo para facilitar la navegación
    verbose = true
}
```
Clase: Calculator.java

```java
public class Calculator {
    
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        return a / b;
    }
    
}
```
Clase de Pruebas: CalculatorTest.java

```java
class CalculatorTest {
    
    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }
    
    @Test
    void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.subtract(3, 2));
    }
    
    @Test
    void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.multiply(2, 3));
    }
    
    @Test
    void testDivide() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.divide(6, 3));
    }
    
    @Test
    void testDivideByZero() {
        Calculator calc = new Calculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
        assertEquals("Divisor cannot be zero", exception.getMessage());
    }
}
```

![img_1.png](Imagenes%2Fimg_1.png)
![img.png](Imagenes%2Fimg.png)

Analiza el informe de mutación. ¿Cuántos mutantes fueron generados? ¿Cuántos mutantes
fueron matados? ¿Cuántos sobrevivieron? ¿Qué mutantes sobrevivieron y por qué?

Como podemos ver se generaron 9 mutantes de los cuales todos fueron matados.
No sobrevivieron mutantes porque no se generaron cambios que afectaran a los parametros que se
pasaron a las pruebas inicialmente.

Ejercicio 24: Mejorar el conjunto de pruebas

Instrucciones:

• Basándote en el informe de mutación generado en el ejercicio anterior, identifica las áreas
donde los mutantes sobrevivieron.

• Escribe pruebas adicionales para matar los mutantes sobrevivientes. Aquí hay algunas pistas:

o ¿Qué pasa si pruebas con números negativos?

o ¿Qué sucede si pruebas con el número 0 en las operaciones add, subtract y
multiply?

• Actualiza la clase de pruebas CalculatorTest con las nuevas pruebas:

Se añadiran las siguientes nuevas pruebas ... 

```java
@Test
void testAddWithNegativeNumbers() {
    Calculator calc = new Calculator();
    assertEquals(-1, calc.add(-2, 1));
}
@Test
void testSubtractWithNegativeNumbers() {
    Calculator calc = new Calculator();
    assertEquals(-1, calc.subtract(-2, -1));
}
@Test
void testMultiplyWithNegativeNumbers() {
    Calculator calc = new Calculator();
    assertEquals(-6, calc.multiply(-2, 3));
}
@Test
void testDivideWithNegativeNumbers() {
    Calculator calc = new Calculator();
    assertEquals(-2, calc.divide(-6, 3));
}
@Test
void testDivideWithZeroNumerator() {
    Calculator calc = new Calculator();
    assertEquals(0, calc.divide(0, 3));
}
```
Las mutaciones creadas y matadas siguen iguales ...

![img_2.png](Imagenes%2Fimg_2.png)

Ejercicio 25: Crear Mutantes manualmente

Comprender cómo se generan los mutantes y probar manualmente si las pruebas los detectan.

**Creamos 2 mutantes :** 

Mutante 1: Cambiar el operador de adición a sustracción
```java
public int add(int a, int b) {
    return a - b; // Error intencional
}
```
Mutante 2: Cambiar el operador de multiplicación a división
```java
public int multiply(int a, int b) {
    return a / b; // Error intencional
}
```

• Ejecuta las pruebas unitarias y verifica que las pruebas fallen.
• Vuelve a corregir los errores y asegura que todas las pruebas pasen de nuevo.
• Reflexiona sobre cómo las pruebas de mutación ayudan a identificar la debilidad en los
conjuntos de pruebas.

Podemos ver que los test fallan **(El mutante muere)** : 

![img_3.png](Imagenes%2Fimg_3.png)

Si eliminamos los mutantes podemos ver que vuelven a pasar:

![img_4.png](Imagenes%2Fimg_4.png)

Esto nos da una idea de que se pueden encontrar ciertos errores dentro de nuestras pruebas
ademas de que nos ayuda a construir pruebas mas robustas.

• Clasifica los mutantes generados en las siguientes categorías:

    o Mutantes aritméticos: Cambios en los operadores aritméticos (+, -, *, /).
        2. Replaced integer addition with subtraction → KILLED
        1. Replaced integer subtraction with addition → KILLED
        1. Replaced integer multiplication with division → KILLED
        2. Replaced integer division with multiplication → KILLED
    o Mutantes relacionales: Cambios en los operadores relacionales (<, >, <=, >=, ==, !=).
    o Mutantes lógicos: Cambios en los operadores lógicos (&&, ||, !).
        1. removed conditional - replaced equality check with false → KILLED
    o Mutantes de asignación: Cambios en las asignaciones (=, +=, -=, *=, /=).
        1. replaced int return with 0 for org/example/Calculator::add → KILLED
        2. replaced int return with 0 for org/example/Calculator::subtract → KILLED
        2. eplaced int return with 0 for org/example/Calculator::multiply → KILLED
        1. replaced int return with 0 for org/example/Calculator::divide → KILLED


Ejercicio 27: Pruebas de mutación en métodos complejos
Aplicar pruebas de mutación a un método más complejo para evaluar la solidez del conjunto de
pruebas.

Instrucciones:

• Agrega el siguiente método calculate a la clase Calculator:

```java

public int calculate(String operation, int a, int b) {
    switch (operation) {
        case "add":  return add(a, b);
        case "subtract":  return subtract(a, b);
        case "multiply": return multiply(a, b);
        case "divide":  return divide(a, b);
        default:  throw new IllegalArgumentException("Invalid operation");
    }
}
```

Agregamos pruebas unitarias para el metodo calculate cubriendo asi todas las ramas.

```java
@Test
void testCalculateAdd() {
    Calculator calc = new Calculator();
    assertEquals(5, calc.calculate("add", 2, 3));
}

@Test
void testCalculateSubtract() {
    Calculator calc = new Calculator();
    assertEquals(1, calc.calculate("subtract", 3, 2));
}

@Test
void testCalculateMultiply() {
    Calculator calc = new Calculator();
    assertEquals(6, calc.calculate("multiply", 2, 3));
}

@Test
void testCalculateDivide() {
    Calculator calc = new Calculator();
    assertEquals(2, calc.calculate("divide", 6, 3));
}

@Test
void testCalculateInvalidOperation() {
    Calculator calc = new Calculator();
    Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.calculate("mod",
    1, 1));
    assertEquals("Invalid operation", exception.getMessage());
}

```

• Ejecuta PIT y genera un informe de mutación.

• Analiza el informe y realiza las mejoras necesarias en el conjunto de pruebas para alcanzar
una mayor cobertura de mutación

![img_5.png](Imagenes%2Fimg_5.png)
![img_6.png](Imagenes%2Fimg_6.png)

Estos fueron los mutantes creados y matados:


1. replaced int return with 0 for org/example/Calculator::calculate → KILLED

1. replaced int return with 0 for org/example/Calculator::calculate → KILLED

1. replaced int return with 0 for org/example/Calculator::calculate → KILLED

1. replaced int return with 0 for org/example/Calculator::calculate → KILLED

Parecen ser el mismo, pero este mutante se aplica para todas las posibles ramas del metodo
Calculate, es decir para el case add, case sustract, case multiply y case divide.
