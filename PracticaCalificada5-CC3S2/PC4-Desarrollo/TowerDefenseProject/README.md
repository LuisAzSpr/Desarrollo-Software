# TowerDefense


Veamos la salida:

![img.png](Imagenes%2Fimg.png)

En este caso, empezamos colocando una torre en la posicion  (0,0)
Los enemigos salen de la primera posicion del camino, tambien podemos ver despues
de cada ataque como la torre les va bajando vida.

![img_1.png](Imagenes%2Fimg_1.png)

Al final podemos ver que un enemigo llega a atacar la base y como es un enemigo basico
la vida que le bajara sera de 5, por lo que la base queda con 95 de vida.


**Mocks:**

• Utiliza Mockito para crear mocks de las clases Enemy y Tower para verificar la interacción
entre objetos.

```java
@BeforeEach
void setUp(){
    tower = new Tower(10,2,1,new int[]{0,0});
    map = new Map(5);
    map.inicializarPorDefecto();
}

```

```java

@Test
void towerAttackTest(){
    // declaramos los mocks
    Enemy enemy1 = mock(Enemy.class);
    Enemy enemy2 = mock(Enemy.class);

    // definimos su comportamiento (posicion)
    when(enemy1.getPosition()).thenReturn(new int[]{2,0}); // es alcanzado por la torre
    when(enemy2.getPosition()).thenReturn(new int[]{3,2}); // no es alcanzado por la torre

    // definimos su vida inicial
    when(enemy1.getHealth()).thenReturn(100);
    when(enemy2.getHealth()).thenReturn(100);

    Wave wave = new Wave(1,map.getCamino());

    HashMap<Enemy,Integer> enemies = new HashMap<>();
    enemies.put(enemy1,0);
    enemies.put(enemy2,1);

    wave.setEnemies(enemies);

    // Ejecutamos el ataque
    tower.attack(wave);

    // debe ser invoocado restandole 10 puntos por el ataque de la torre
    verify(enemy1).setHealth(90);
    // no debe ser invocado ya que se encuentra fuera del rango de la torre
    verify(enemy2,never()).setHealth(anyInt());

}

```


**Stubs:**

• Crea stubs para métodos que devuelven enemigos o torres específicos.


```java
Wave wave1 = mock(Wave.class);
Wave wave2 = mock(Wave.class);

List<Enemy> enemies1 = new ArrayList<>();
enemies1.add(new BasicEnemy(map.getCamino()[0]));
enemies1.add(new BasicEnemy(map.getCamino()[1]));
enemies1.add(new BasicEnemy(map.getCamino()[2]));

List<Enemy> enemies2 = new ArrayList<>();
enemies2.add(new BossEnemy(map.getCamino()[0]));

// el metodo getEnemies para este stub se encarga de de dolver 3 enemigos basicos
when(wave1.getEnemies()).thenReturn(enemies1);
// el metodo getEnemies para este stub se encarga de devolver un bossEnemy 
when(wave2.getEnemies()).thenReturn(enemies2);
```


Preguntas de diseño e implementación (5 puntos)


**Diseño de la clase Map:**

• ¿Cómo implementarías la clase Map para representar el mapa del juego, asegurando que se
puedan agregar y verificar posiciones de torres y caminos?

**Colocaciones**

```java
public boolean isValid(int i,int j){
    return mapa[i][j].equals(" ");
}

public void colocarTorre(int i,int j){
    if(isValid(i,j)){
        actualizarCelda(i,j,"T");
    }
}

public void colocarCamino(int i,int j){
    mapa[i][j] = "C";
}

```

**verificaciones**
```java
public boolean verificar(int i,int j,String elemento){
    return mapa[i][j].equals(elemento);
}

public boolean verificarCamino(int i,int j){
    return verificar(i,j,"C");
}

public boolean verificarBase(int i,int j){
    return verificar(i,j,"B");
}

public boolean verificarTorre(int i,int j){
    return verificar(i,j,"T");
}
```

• Implementa un método en la clase Map llamado isValidPosition(int x, int y) que verifique si
una posición es válida para colocar una torre.


```java
public boolean isValidPosition(int i,int j){
    return mapa[i][j].equals(" ");
}

public void colocarTorre(int i,int j){
    if(isValidPosition(i,j)){
        actualizarCelda(i,j,"T");
    }
}
```

**Enemigos con diferentes características:**

• Diseña e implementa una clase SpeedyEnemy que herede de Enemy y tenga una velocidad
mayor pero menos vida.

```java
public class SpedyEnemy extends Enemy{
    public SpedyEnemy(int[]position) {
        super(2, 70, 15,8,position);
    }
}
```

• ¿Cómo gestionarías el movimiento de los enemigos en el mapa, asegurando que sigan el
camino predefinido?

Creamos una clase Path que sera el camino que va a ser recorrido por los enemigos.

```java
public class Path {

    private int[][] camino;
    private String[][] mapa;
    private int size;

    public Path(String[][] mapa) {
        this.mapa = mapa;
        // encuentra todas las celdas del camino
        this.size = contarCamino();
        // la ultima celda del camino es la de la base
        this.camino = new int[size + 1][2];
    }

    public boolean encontrarCamino() {

        boolean exito = false;

        // Encontrando la primera celda (la primera celda debe encontrarse en la primera fila)
        for (int j = 0; j < mapa.length; j++) {
            if (mapa[0][j].equals("C")) {
                camino[0][0] = 0;
                camino[0][1] = j;
                exito = true;
            }
        }

        // si no se encontro la primera celda retornamos una exception
        if (!exito) {
            throw new PathIsntCorrect("El camino no es correcto");
        }

        // ahora vamos armando el camino (tienen que ser celdas vecinas)
        for (int i = 0; i < size; i++) {
            int[] celda = siguienteCeldaDelCamino(camino[i][0], camino[i][1], "C");
            if (i == size - 1) {
                break;
            }
            if (celda[0] == -1) {
                throw new PathIsntCorrect("El camino contiene saltos ... \n " +
                        "Los enemigos no pueden llegar a nuestra base!!");
            }
            camino[i + 1][0] = celda[0];
            camino[i + 1][1] = celda[1];
        }

        // Ahora tenemos que asegurarnos que la ultima celda del camino es adyacente a la base
        int[] celda = siguienteCeldaDelCamino(camino[size - 1][0], camino[size - 1][1], "B");
        camino[size][0] = celda[0];
        camino[size][1] = celda[1];
        return true;
    }
}
```

**Torres con diferentes habilidades:**

• Implementa una clase SniperTower que tenga un daño alto y un alcance muy largo pero una
velocidad de disparo baja.

```java
public class SniperTower extends Tower{
    public SniperTower(int[]position) {
        super(100,3,5,position);
    }
}
```

• ¿Cómo implementarías el método attack(List<Enemy> enemies) en la clase Tower para
atacar a los enemigos dentro de su alcance?

A todos los que se encuentran dentro del rango les disminuimos vida

```java
public void attack(Wave wave){
    for(Enemy enemy:wave.getEnemies()){
        int[]enemyPosition = enemy.getPosition();
        if(Math.abs(enemyPosition[0]-position[0])<=range &&
            Math.abs(enemyPosition[1]-position[1])<=range){
            enemy.setHealth(enemy.getHealth()-damage);
        }
    }
}
```


**Sistema de puntuación y salud de la base:**

• ¿Cómo actualizarías la puntuación del jugador y la salud de la base cuando un enemigo es
derrotado o alcanza la base?

```java
public void atacarBase(List<Enemy> enemies){
    for(Enemy enemy:enemies){
        int[] position = enemy.getPosition();
        if(position[0]==posicionBase[0] && position[1]==posicionBase[1]){ // vemos si su posicion es la misma que la base
            baseHealth -= enemy.getDamage(); // un enemigo ataca a l abase
            enemy.setHealth(0); // los enemigos que atacan, mueren
        }
    }
}
```

**Cobertura de ramas:**

• Ejercicio: Implementa pruebas para garantizar que todas las ramas del método placeTower
en la clase TowerDefenseGame estén cubiertas.

```java
@Test
public void testPlaceTower_ValidPosition() {
    // declaramos un moc
    Map mockMap = mock(Map.class);
    // Configurar mock para posición válida
    when(mockMap.isValidPosition(3, 4)).thenReturn(true);
    game.placeTower(new CannonTower(new int[]{3,4}));
    // Verificar que la torre se haya colocado
    verify(mockMap).colocarTorre(3,4);
}

@Test
void testPlaceTower_InvalidPosition() {
    // declaramos un mock
    Map mockMap = mock(Map.class);
    // Configuramos un mock para una posicion invalida
    when(mockMap.isValidPosition(3, 4)).thenReturn(false);
    game.placeTower(new CannonTower(new int[]{3,4}));
    // Verificar que la torre no se haya colocado
    verify(mockMap, never()).colocarTorre(3,4);
}

```

**Cobertura de condiciones:**

• Ejercicio: Escribe pruebas unitarias para verificar todas las condiciones en el método attack
de la clase Tower.

```java
@Test
void towerAttackTest(){
    // declaramos los mocks
    Enemy enemy1 = mock(Enemy.class);
    Enemy enemy2 = mock(Enemy.class);

    // definimos su comportamiento (posicion)
    when(enemy1.getPosition()).thenReturn(new int[]{2,0}); // es alcanzado por la torre
    when(enemy2.getPosition()).thenReturn(new int[]{3,2}); // no es alcanzado por la torre

    // definimos su vida inicial
    when(enemy1.getHealth()).thenReturn(100);
    when(enemy2.getHealth()).thenReturn(100);

    Wave wave = new Wave(1,map.getCamino());

    HashMap<Enemy,Integer> enemies = new HashMap<>();
    enemies.put(enemy1,0);
    enemies.put(enemy2,1);

    wave.setEnemies(enemies);

    // Ejecutamos el ataque
    tower.attack(wave);

    // debe ser invoocado restandole 10 puntos por el ataque de la torre
    verify(enemy1).setHealth(90);
    // no debe ser invocado ya que se encuentra fuera del rango de la torre
    verify(enemy2,never()).setHealth(anyInt());

}
```

Usemos jacoco para determinar el porcentaje testeado :

![img_2.png](Imagenes%2Fimg_2.png)

Para el paquete org.example : 
```java
@Test
void oleadaGameTest(){

        Game game = new Game();
        int[][]camino = game.getMapa().getCamino();
        Wave wave = new Wave(1,camino);

        List<Enemy> enemies = wave.getEnemies();
        game.oleadaGame();

        // Verifiquemos los movimientos de los enemigos
        assertThat(enemies.get(0).getPosition()).isEqualTo(camino[0]);
        // La torre no ha sido dañada
        assertThat(game.getBaseHealth()).isEqualTo(95);

    }
```

![img_3.png](Imagenes%2Fimg_3.png)

Veamos que ahora todos tienen >80 %


**Mocking de dependencias:**

•  ¿Cómo usarías Mockito para crear un mock del Map y probar la colocación de torres en la
clase TowerDefenseGame?

```java
Map map = mock(Map.class);
when(map.isValid(3,2)).thenReturn(true);
```


**Stubbing de métodos:**

• ¿Cómo usarías stubs en Mockito para simular el comportamiento del método getEnemies en
la clase Wave durante las pruebas?


```java
Wave wave1 = mock(Wave.class);
Wave wave2 = mock(Wave.class);

List<Enemy> enemies1 = new ArrayList<>();
enemies1.add(new BasicEnemy(map.getCamino()[0]));
enemies1.add(new BasicEnemy(map.getCamino()[1]));
enemies1.add(new BasicEnemy(map.getCamino()[2]));

List<Enemy> enemies2 = new ArrayList<>();
enemies2.add(new BossEnemy(map.getCamino()[0]));

// el metodo getEnemies para este stub se encarga de de dolver 3 enemigos basicos
when(wave1.getEnemies()).thenReturn(enemies1);
// el metodo getEnemies para este stub se encarga de devolver un bossEnemy 
when(wave2.getEnemies()).thenReturn(enemies2);
```

Podriamos devolver los enemigos que querramos, es decir, que getEnemies
se comporte como queramos, podemos definir wave1 que se use en ciertos
escenarios y wave2 en otros escenarios.

