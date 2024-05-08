# SPRINT 1

Podemos verificar que existe una prueba que ya pasa...

![alt text](Imagenes/image-5.png)

Por lo que desde el primer ciclo TDD intentaremos no solo hacer pasar la prueba en la que nos centremos en escribir codigo, sino tambien en hacer que esta prueba mantenga su estado en "verde".


Esta prueba verifica si la letra en la primera posicion (posicion 0) de la  palabra correcta y la palabra intento coinciden.

![alt text](Imagenes/image-10.png)

-------------------------

### 1. Primer ciclo TDD

 <span style="color:red;">1. RED</span>

Veamos que la prueba falle :
Como podemos ver se esperaba CORRECT pero se obtuvo INCORRECT. Esto se debe a que siempre devuelve incorrect (no tiene ninguna logica implementada aun), es por esto que la primera prueba pasa...

![alt text](Imagenes/image.png)

 <span style="color:green;">2. GREEN</span>

Ahora escribamos el codigo suficiente para que pase, en este caso estamos creando el metodo asses en Score que verifica si el caracter en la posicion **i** de ambas cadenas es el mismo y en caso asi sea almacena Letter.CORRECT en la variable de instancia resultado.

![alt text](Imagenes/image-2.png)

El metodo guess de Word llama al metodo asses de Score que a su vez devuelve una instancia score que contiene como variable de instancia el resultado de esa comparacion, que luego sera accedida por el metodo letter para hacer la verificacion.

![alt text](Imagenes/image-8.png)

Podemos ver que esta en verde!! Ahora la prueba pasa. y no solo eso, sino que tambien lo hace la anterior quiere decir que no hemos introducido ningun bug en la otra prueba, por lo que vamos por buen camino.
![alt text](Imagenes/image-6.png)
![alt text](Imagenes/image-7.png)


 <span style="color:BLUE;">3. Refactor</span>

 Ahora que ya hemos hecho que pasen estas 2 pruebas, debemos pararnos y pensar en que tan bien estamos escribiendo el codigo. Por lo que ahora trataremos de controlar los code smells...

 Podemos extraer la comparacion isCorrectLetter e implementarla en un metodo de tal forma que todo se mantiene mas claro...

 ![alt text](Imagenes/image-9.png)

 Ademas podemos personalizar nuestra propia assertion de tal forma que el codigo de prueba sea mas legible y entendible...

 ![alt text](Imagenes/image-12.png)

Podemos ver que ahora llamamos directamente a ese assertion personalizado y todo es mas entendible.

 ![alt text](Imagenes/image-13.png)

La siguiente refactorizacion seria convertir position en una variable de instancia, esto puede parecer bastante raro al principio,ya que solo esta evaluando la posicion 0, pero si nos paramos a pensar, solo necesitamos eso por ahora, ya que las pruebas evaluan esa posicion...

![alt text](Imagenes/image-14.png)

Ahora una parte importante en cada paso de refactorizacion anterior es verificar si todo continua en verde despues de realizar cada refactorizacion, si no hemos roto algo a la hora de refactorizar, por lo que corramos de nuevo las pruebas y verifiquemos...

En efecto... podemos ver que siguen en verde!! y ahora tenemos un codigo mas limpio :) ...

![alt text](Imagenes/image-11.png)

# SPRINT 2

### 2do ciclo TDD

Llegados a este punto, podemos decir que hemos abarcado el codigo para palabras de una sola letra, por lo que ahora introduciremos pruebas para 2 letras...

Para esto esto necesitamos introducir un nuevo concepto : una letra puede estar presente en la palabra, pero no en la posición que adivinamos (PART_CORRECT).

Empecemos escribiendo la prueba...

Esta prueba verifica que la letra de la  posicion 1 de la palabar adivinar "ZA" sea parcialmente correcta (ya que se encuentra en la palabra word pero no en esa posicion)

![alt text](Imagenes/image-15.png)


-------------------------

 <span style="color:red;">1. RED</span>
 
 Podemos ver que la prueba esta en rojo...

![alt text](Imagenes/image-20.png)


 <span style="color:green;">2. GREEN</span>

Ahora toca escribir el codigo suficiente para que esta prueba pase ....

Este codigo toma un contador position, inicializado en 0, y luego va ir aumentando uno en uno, esto devuelve el resultado de la ultima letra, para las pruebas anteriores es suciente, pero... toca preguntarnos si esta prueba es lo suficientemente confiable.

![alt text](Imagenes/image-22.png)

podemos ver que ahora esta en verde...
![alt text](Imagenes/image-23.png)



### 3er ciclo TDD

Hay que reconocer que la prueba anterior no es lo suficientemente buena como para respaldar lo que se quiere probar, es decir, esta prueba puede pasar... pero es porque no estamos evaluando otras posibilidades, hagamos un esfuerzo y escribamos una prueba un poco mas verosimil con el comportamiento que queremos que tenga nuestra clase.

La siguiente prueba verifica en 3 posiciones 3 posibles resultados, por lo que podemos considerar que podemos confiar mas en el codigo que pase esta prueba.


![alt text](Imagenes/image-17.png)

 <span style="color:red;">1. RED</span>


 Podemos ver que la prueba esta en rojo...
 Esto se debe a que siempre se obtiene el resultado de la ultima letra ....

![alt text](Imagenes/image-18.png)
![alt text](Imagenes/image-19.png)

 <span style="color:green;">2. GREEN</span>

 Ahora escribiremos el codigo necesario para que pase ....

En este codigo se hace algo parecido al anterior, solo que se almacena en un arreglo results para cada valor de position, es decir, se almacena un valor resultado para cada una de las letras.

![alt text](Imagenes/image-25.png)

Ahora veamos si este codigo puede pasar nuestra prueba ... 

Podemos ver que ahora si pasa!!

![alt text](Imagenes/image-26.png)

Podemos agregar la prueba del ciclo anterior para ver que todo funciona correctamente (nada se ha roto)...

![alt text](image.png)

 <span style="color:blue;">3. Refactor</span>


Una vez hemos hecho que todas las pruebas pasen, toca refactorizar un poco...

Lo que haremos sera aislar la logica de asignar un resultado a cada una de las letras.

![alt text](Imagenes/image-27.png)

Ahora podemos refactorizar nuestro codigo de prueba para intentar hacer que luzca mejor, para esto solucionaremos los duplicados asserts que se daban para una prueba, para esto extraeremos el metodo assertScoreForGuess(), este metodo 

![alt text](Imagenes/image-29.png)

Al final nuestro codigo de prueba se encuentra asi...

![alt text](Imagenes/image-31.png)


Ahora toca verificar que todo sigue en verde y no hayamos roto nada ...

Sigue en verde!!

![alt text](Imagenes/image-28.png)

## Analisis en sonarqueb

![alt text](image-1.png)

Ahora vamos a limpiar ciertos olores de codigo...

1. Remover el "import" duplicado

![alt text](image-4.png)

2. Remover el modificador publico en los metodos del test

![alt text](image-2.png)

Hagamos otro analisis estatico 

Podemos ver que hemos reducido a unicamente 1 olor de codigo 

![alt text](image-5.png)

## Sprint 3

Implementacion del juego en consola

![alt text](image-6.png)

Ahora toca refactorizar un poco...

![alt text](image-7.png)

Podemos crear un metodo ImprimirResults que imprima los resultados en consola desde la clase score...

![alt text](image-8.png)


## Zona de preguntas

----------------------

1. ¿Cuáles son los elementos clave que se deben considerar al diseñar un juego de adivinanza de palabras como "Wordz"? ¿Cómo influyen los sistemas de retroalimentación en la experiencia del jugador en juegos de trivia o adivinanzas?

Los elementos claves seria la arquitectura que se va a usar, el publico objetivo (quienes van a utilizar la aplicacion), como jugadores de wordz, etc, Los requisitos del publico objetivo.

La retroalimentacion permite que el usuario final (jugador de wordz), pueda obtener el producto que quiere, ya que puede estar dando constantemente comentarios sobre como le parece el estado del producto.

----------------------


2. Describe cómo el principio de "feedback continuo" en las metodologías ágiles podría aplicarse en el desarrollo del juego "Wordz".

El feedback continuo ayudaria a diseñar una mejor aplicacion haciendo que los jugadores puedan probar una parte del juego o puedan aportar opiniones (capturadas como requisitos),como por ejemplo:

- Como jugador de Wordz me gustaria ver los resultados en cada letra para determinar que tan lejos estoy de la palabra a adivinar.

--------------------------

3. ¿Cuáles son algunas características nuevas de JUnit 5 que lo hacen adecuado para pruebas unitarias en proyectos Java modernos? ¿Cómo se podría diseñar una suite de pruebas unitarias para validar la lógica de puntuación en "Wordz"?

JUnit5 permite poder usar pruebas parametrizadas, lo cual puede hacer que nuestro codigo sea mas corto y facil de leer evaluando al mismo tiempo multiples posibilidades.

Para validar la puntuacion podriamos usar un test que dado una palabra correcta y una lista de palabras de adivinanza, de tal forma que si la palabra correcta se encuentra en la posicion i de la lista, entonces el resultado seria 6-i (ya que se tienen 6 intentos) y si no se encuentra debe ser 0, por lo que deberiamos implementar un assert que verifique ese puntaje correcto.

----------------------

4. ¿Por qué es importante la refactorización en el desarrollo continuo de un juego y cómo puede afectar la mantenibilidad del código? Proporciona un ejemplo de cómo podrías refactorizar un método complejo en el juego "Wordz" para mejorar la claridad y eficiencia.

Como vimos anteriormente teniamos este metodo ...

![alt text](image-9.png)

que fue refactorizar de esta forma...

que como podemos ver es mucho mas claro y legible

![alt text](image-10.png)

----------------------
