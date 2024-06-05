## Sprint 3 


**Codigo en Sprint2**

```java

public int[] procesarComandos(String mov){
    int posicionActual[] = jugador.getPosicionActual();
    // copiamos el arreglo inicial en el final
    int posicionFinal[] = new int[2];
    posicionFinal[0] = posicionActual[0];
    posicionFinal[1] = posicionActual[1];
    // logica de los movimientos
    switch(mov){
        case "N": posicionFinal[0]-=1;break;
        case "S": posicionFinal[0]+=1 ;break;
        case "E":posicionFinal[1]+=1;break;
        case "O":posicionFinal[1]-=1;break;
    }

    // verificamos que el movimiento sea valido
    if(!noEsMovimientoValido(posicionFinal,laberinto.getSize())){
        return posicionFinal;
    }
    return null;
}
```

**Codigo en sprint3**

```java


    public Laberinto(int size){
        incializar();
    }

    public void incializar(){
        this.size = size;
        matriz = new String[size][size];
        // inicializamos con espacios vacios
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matriz[i][j] = ".";
            }
        }
        // Agregamos la posicion del jugador, que es aleatoria
        int[] posicionJugador = posicionAleatoria();
        matriz[posicionJugador[0]][posicionJugador[1]] = "P";

        colcarTesoros();
    }

```

Hemos reducido la complejidad del constructor, ahora se puede leer de una mejor manera
ademas podemos crear otro metodo aqui que inicialice en vacio a la matriz.
Esto nos puede servir mas adelante para poder realizar pruebas y modificar la matriz
a nuestro gusto para que satisfaga los escenarios en las pruebas, esto generalmente
va en setUp().

Al final el codigo quedaria asi, mucho mas entendible y utilizable

```java
public Laberinto(int size){
    this.size = size;
    incializar();
}

public void incializar(){
    inicializarEnVacio();
    // Agregamos la posicion del jugador, que es aleatoria
    int[] posicionJugador = posicionAleatoria();
    matriz[posicionJugador[0]][posicionJugador[1]] = "P";

    colcarTesoros();
}

public void inicializarEnVacio(){
    matriz = new String[size][size];
    // inicializamos con espacios vacios
    for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
            matriz[i][j] = ".";
        }
    }
}

```

Si bien es cierto no implementamos principios SOLID por falta de  un poco de tiempo, podemos 
destacar que delegar la responsabilidad a la clase Salidas para imprimir el estado del juego 
nos ayuda al primer principio SOLID, que es el de unica responsabilidad, ya que imprimir un juego
generalmente esta puesto en la clase Juego, sinembargo aqui le estamos delegando
esta responsabilidad a la clase Salidas, haciendo que la clase Juego tenga una responsabilidad menos.


```java

public class Salidas {

    private Juego juego;

    public Salidas(Juego juego){
        this.juego = juego;
    }

    public void mostrarLaberinto(){
        Laberinto laberinto = juego.getLaberinto();
        Jugador jugador = juego.getJugador();
        System.out.println("Vidas :"+jugador.getVidas());
        System.out.println("Puntaje :"+jugador.getPuntaje());
        for(int i=0;i< laberinto.getSize();i++){
            for(int j=0;j<laberinto.getSize();j++){
                System.out.print(laberinto.getMatriz()[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
}


```
Ahora, se podria extender las formas en las que se muestra el juego, podria ser
por consola, pero tambien podria ser por una GUI, para esto podemos utilizar el principio
SOLID que dice que debemos depender de interfaces y no de implementaciones, entonces
podemos crear una intefaz Graph que sea implementada por una clase que dibuje en GUI
y por una clase que dibuje en consola.




```java

```


