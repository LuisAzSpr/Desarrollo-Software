# CoolGame (PC4)

**Puntaje completo: 10 puntos**

**Preguntas de diseño e implementación (5 puntos)**


Veamos la implementacion en codigo:

### Diseño de la clase Map:

• ¿Cómo implementarías la clase Map para representar el mapa del juego, asegurando que se
puedan agregar y verificar posiciones de torres y caminos?

```java
public class Map {
    
    private String[][] mapa;
    private int size;

    public Map(int size){
        this.size = size;
        mapa = new String[size][size];
        inicializarMapaVacio(size);
        mapaPorDefecto();
    }
    
    
    // generamos un mapa por defecto
    private void mapaPorDefecto(){
        mapa[0][2] = "C";
        mapa[1][1] = "C";
        mapa[2][0] = "C";
        mapa[3][1] = "C";
        mapa[3][2] = "C";
        mapa[2][3] = "C";
        mapa[2][4] = "B";
    }
    
    // inicializamos el mapa de manera vacia
    private void inicializarMapaVacio(int size) {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                mapa[i][j] = " ";
            }
        }
    }

    public void colocarCamino(int i,int j){
        mapa[i][j] = "C";
    }

    public void colocarBase(int i,int j){
        mapa[i][j] = "B";
    }

    public void colocarTorre(int i,int j){
        mapa[i][j] = "T";
    }

    public boolean verificarTorre(int i,int j){
        return mapa[i][j].equals("T");
    }

    public boolean verificarCamino(int i,int j){
        return mapa[i][j].equals("C");
    }
    // metodos getters and setters 
}

```

• Implementa un método en la clase Map llamado isValidPosition(int x, int y) que verifique si
una posición es válida para colocar una torre.

```java
public boolean isValidPosition(int x,int y){
    return mapa[x][y].equals(" ");
}
```

### Enemigos con diferentes características:

• Diseña e implementa una clase SpeedyEnemy que herede de Enemy y tenga una velocidad
mayor pero menos vida.

```java
public class SpeedyEnemy extends Enemy{
    public SpeedyEnemy() {
        super(2, 70, 15);
    }
}
```

• ¿Cómo gestionarías el movimiento de los enemigos en el mapa, asegurando que sigan el
camino predefinido?

Podemos añadir una matriz que represente el camino que deben seguir los enemigos, almacenando los indices del mapa(como si fuera una ruta)
para despues poder usarlos...


```java
// Indica que la tercera celda a recorrer es la celda [i,j] en el mapa.
camino[3][0] = i , camino[3][1] = j
```

```java
private void mapaPorDefecto(){
    mapa[0][2] = "C"; camino[0][0] = 0; camino[0][1] = 2;
    mapa[1][1] = "C"; camino[1][0] = 1; camino[1][1] = 1;
    mapa[2][0] = "C"; camino[2][0] = 2; camino[2][1] = 0;
    mapa[3][1] = "C"; camino[3][0] = 3; camino[2][1] = 1;
    mapa[3][2] = "C"; camino[4][0] = 2; camino[4][1] = 2;
    mapa[2][3] = "C"; camino[5][0] = 2; camino[5][1] = 3;
    mapa[2][4] = "B"; camino[6][0] = 2; camino[6][1] = 4;
}

```


### Torres con diferentes habilidades:

• Implementa una clase SniperTower que tenga un daño alto y un alcance muy largo pero una
velocidad de disparo baja.

```java
public class SniperTower extends Tower{
    public SniperTower(int damage, int range, int fireRate) {
        super(100, 3, 5);
    }
}
```

• ¿Cómo implementarías el método attack(List<Enemy> enemies) en la clase Tower para
atacar a los enemigos dentro de su alcance?


```java
public void attack(List<Enemy> enemies,Map map){
    boolean enemigosSeEncuentran = false;
    // si los enemigos se encuentran en el rango
    for(int i=position[0]-range;i<=position[0]+range;i++){
        for(int j=position[1]-range;j<=position[1]+range;j++){
            if(map.getMapa()[i][j].equals("E")){
                enemigosSeEncuentran = true;
            }
        }
    }
    if(enemigosSeEncuentran){
        for(Enemy enemy:enemies){
            enemy.setHealth(enemy.getHealth()-damage);
        }
    }
}
```

Sistema de oleadas:

• ¿Cómo diseñarías la generación de oleadas para que cada oleada sea progresivamente más
difícil?



• Implementa un método en la clase Wave llamado spawnEnemies() que genere los enemigos
de la oleada y los coloque en el mapa.



SALIDA DEL JUEGO:


![img.png](Imagenes%2Fimg.png)
![img_1.png](Imagenes%2Fimg_1.png)