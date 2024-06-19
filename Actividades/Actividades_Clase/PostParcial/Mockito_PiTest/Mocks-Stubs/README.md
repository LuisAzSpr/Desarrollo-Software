# ACTIVIDAD : Stubs y Mocks

### Lanzamiento de dados

Descripción de la solución propuesta:

- Paso 1 : Crear una interfaz NumerosAleatorios que defina un método para obtener números
  aleatorios dentro de un rango específico.

```java
public interface NumerosAleatorios {
    public int getNumero(int a,int b);
}
```

- Paso 2 : Modificar la clase LanzamientoDados para que utilice la interfaz NumerosAleatorios,
inyectando la dependencia a través del constructor.


```java
public class LanzamientoDados {
    private NumerosAleatorios numerosAleatorios;
    public LanzamientoDados(NumerosAleatorios numAleat){
        this.numerosAleatorios = numAleat;
    }
    public int lanzarDado(){
        return numerosAleatorios.getNumero(1,6);
    }
}
```

- Paso 3 :  Desarrollar un stub de NumerosAleatorios para usar en pruebas unitarias, permitiendo
  controlar los resultados de los lanzamientos.
```java
@ExtendWith(MockitoExtension.class)
class LanzamientoDadosTest {
  @Mock
  private NumerosAleatorios numerosAleatorios;
  @InjectMocks
  private LanzamientoDados lanzamientoDados;

  @BeforeEach
  void setUp(){
    when(numerosAleatorios.getNumero(1.6)).thenReturn(3);
  }
}
```

- Paso 4 : Escribir pruebas unitarias para LanzamientoDados utilizando el stub para asegurar que la
  lógica del lanzamiento funciona como se espera bajo condiciones controladas.

```java
@ExtendWith(MockitoExtension.class)
class LanzamientoDadosTest {
  @Mock
  private NumerosAleatorios numerosAleatorios;
  @InjectMocks
  private LanzamientoDados lanzamientoDados;

  @BeforeEach
  void setUp(){
    when(numerosAleatorios.getNumero(1,6)).thenReturn(3);
  }
  @Test
  void verificarNumero3(){
    assertThat(lanzamientoDados.lanzarDado()).isEqualTo(3);
  }
}
```
- Paso 5 : Implementar una clase NumerosGeneradosAleatoriamente que utilice un generador de
números aleatorios real y que cumpla con la interfaz NumerosAleatorios.

```java
public class NumerosGeneradosAleatoriamente implements NumerosAleatorios{
  // Usamos random para generar numeros aleatorios reales
  Random random = new Random();
  
  @Override
  public int getNumero(int a, int b) {
    return random.nextInt(b)+a;
  }
}
```

- Paso 6 : Integrar y probar la clase LanzamientoDados en una aplicación de producción,
inyectando la implementación real de NumerosAleatorios.

```java
public class Main {
    public static void main(String[] args) {
        // inyectando la implementacion real
        LanzamientoDados lanzamientoDados = new LanzamientoDados(new NumerosGeneradosAleatoriamente());
        // Veamos 10 lanzamientos y en que rango caen
        for(int i=1;i<=10;i++){
            System.out.println("lanzamiento "+i+" : "+lanzamientoDados.lanzarDado());
        }
    }
}   
```

