# Practica Califcada 5

### Ejercicio 1: Configuración y uso de docker (3 puntos)

**Teoría:**

• Describe los principios fundamentales de los contenedores Docker y su arquitectura interna.
Explica cómo Docker maneja la seguridad y el aislamiento de contenedores.


Los namespaces en Linux permiten que Docker cree un entorno aislado en donde se pueden ejecutar las aplicaciones de manera independiente,
sin compartir recursos ni que existan problemas de dependencias con otros proyectos. Para esto se crea
un archivo dockerfile y este a  su vez crea una imagen de nuestra aplicacion que se correra en el contenedor.


• Compara y contrasta Docker con soluciones de virtualización tradicionales, como VMware y
VirtualBox. Discute las ventajas y desventajas de cada enfoque.

A diferencia de una maquina virtual, docker es mucho mas liviano, esto lo logra mediante los contenedores, sinembargo el nivel de aislamiento
que te da una maquina virtual es mucho mayor que la de docker. En si, docker se usa para aplicaciones basadas en microservicios.


---

**Práctico:**

• Escribe un Dockerfile para la aplicación Tower Defense que incluya la instalación de todas las
dependencias necesarias. Asegúrate de optimizar el Dockerfile para reducir el tamaño de la
imagen final.

    openjdk:17 es una imagen base que te permite ejecutar aplicaciones java  

```Dockerfile
FROM openjdk:17

WORKDIR /app

COPY src/main/java /app

RUN javac org/example/*.java

CMD ["java", "org.example.Main"]
```

• Construye y ejecuta el contenedor Docker utilizando el Dockerfile creado. Utiliza docker exec
para acceder al contenedor y verificar que la aplicación funcione correctamente.

    Como podemos ver, se encuentran todos los archivos del dirctorio especificado anteriormente
    en el dockerfile que gracias a la imagen base openjdk:17 podemos correr.

![img_7.png](Imagenes%2Fimg_7.png)

• Configura una red personalizada para la aplicación Tower Defense. Implementa múltiples
contenedores que interactúen entre sí a través de esta red personalizada.




```bash
docker network create network1 # primero creamos la imagen
docker exect -it --name containter --network network1 tower-defense-game
```

### Ejercicio 2: Redes y volúmenes en Docker (3 puntos)

Teoría:

• Explica en detalle cómo Docker maneja las redes y los volúmenes. Discute los diferentes
tipos de redes (bridge, host, overlay) y cuándo es apropiado usar cada una.

Docker crea volumenes y redes para que los contenedores se comuniquen y almacenen los datos de la aplicacion,
en cuanto a los tipos de redes que existen, la bridge permite que los contenedores que se conecten a esta red
puedan comunicarse entre si a traves de sus IPs cada una de estas redes esta asilada de las otras, en la red host se utiliza la misma que la del host, es decir,
no existe aislamiento de red esto se usa cuando se requiere para aplicaciones con baja latencia, el overlay por otro lado permite que cada contenedor que se agregue tenga su propio host, esto 
permite el escalado horizontal de la aplicacion cuando existen mucho trafico de red, esto se suele usar en microservicios.

• Describe los mecanismos de persistencia de datos en Docker, incluyendo volúmenes y bind
mounts. Explica las diferencias entre ellos y las mejores prácticas para su uso.

Los volumenes nos ayudan con la persistencia de datos, es decir, estos guardan los datos de una aplicacion contenerizada
despues de que haya dejado de correr. En cuanto a las diferencias entre los volumes y los bind mounds, podemos decir que
la configuracion de volumenes son gestionados por docker y los binds son gestionados por el mismo usuario, sin embargo docker
tiene optimizado los volumenes.


Práctico:

• Crea una red personalizada para el proyecto Tower Defense y configura los contenedores
para que utilicen esta red.

Creamos la red con el comando

```bash
docker network create networkName
```

![img_15.png](Imagenes%2Fimg_15.png)

• Implementa un volumen Docker para almacenar los datos del juego de forma persistente.
Asegúrate de que el volumen se monte correctamente y que los datos persistan después de
reiniciar el contenedor.

Creamos el volumen con el comando

```bash
docker volume create volumenName
```

![img_16.png](Imagenes%2Fimg_16.png)


• Utiliza docker-compose para definir los servicios de la aplicación Tower Defense, incluyendo
redes y volúmenes. Escribe un archivo docker-compose.yml que configure estos servicios y
despliega la aplicación utilizando Docker Compose.

```yaml
version: '3'
services:
  game:
    image: tower-defense-game
    networks:
      - game-network
    volumes:
      - game-data:/app/data

networks:
  game-network:
    driver: bridge

volumes:
  game-data:
    driver: local
```

Explicacion del archivo:

Nuestra aplicacion tomara como imagen la imagen creada anteriormente por el dockerfile.

```yaml
services:
  game:
    image: tower-defense-game
```

De manera equivalente tomaremos la red creada anteriormente y establecemos el tipo de red

```yaml
services:
  game:
    networks:
      - game-network
```

```yaml
networks:
  game-network:
    driver: bridge
```

y tambien tomaremos el volumen creado anteriormente y su tipo

```yaml
services:
  game:
    volumes:
      - game-data:/app/data
```

```yaml
volumes:
  game-data:
    driver: local
```

Con esto hemos definido una red a la cual podemos ir agreando contenedores que se
pueden comunicar entre si y comparten el mismo almacenamiento (volumen).

### Ejercicio 3: Orquestación con Kubernetes (4 puntos)

Teoría:

• Describe la arquitectura de Kubernetes y sus componentes principales, incluyendo el API
server, etcd, scheduler, y kubelet. Explica cómo estos componentes interactúan para
gestionar un clúster de Kubernetes.

El API server se encarga de mediar las comunicaciones externas con el cluster,
cada comando que hemos ejecutado se comunica con el API server, el etcd contiene datos
sobre el estado del cluster y los pods, es decir, cuando ejecutamos un comando como, por ejemplo:

```bash
kubectl get pods
```

lo que estamos haciendo es comunicarnos directamente con el API server y este consulta a etcd
para determinar el estado de los pods de nuestro de cluster de kubernetes.

El scheduler por otro lado, se encarga de asignarle un nodo a un pod que no tiene uno, esto ayuda
a mantener el estado de la aplicacion deseada y a contorlar el trafico de red.

Cada pod tiene un kubelet, que se asegura que cada contenedor se este ejecutando como se debe.




• Discute las estrategias de escalabilidad y alta disponibilidad en Kubernetes. Explica cómo
Kubernetes maneja la recuperación de fallos y la gestión de réplicas.

Kubernetes nos ayuda a que la escabilidad sea horizontal, ademas la disponibilidad de la aplicacion
es algo importante, es por esto que se crea un archivo deployment.yaml en donde especificamos cuantas
replicas queremos que esten corriendo en cada momento, entonces, kubernetes siempre intentara hacer 
que en todo momento existan n replicas corriendo, lo que permite la disponibilidad de los servicios
y en caso falle uno, kubernetes creara una nueva replica intentando llevar el estado de la aplicacion
al estado original.

En el archivo deployment.yaml se definen de esta forma las replicas:

```yaml
spec:
  replicas: 3
```



Práctico:

• Escribe un archivo deployment.yaml para la aplicación Tower Defense. Asegúrate de definir
los recursos necesarios (CPU, memoria) y las políticas de escalabilidad.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: tower-defense-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: tower-defense-game
  template:
    metadata:
      labels:
        app: tower-defense-game
    spec:
      containers:
        - name: tower-defense-game
          image: tower-defense-game
          ports:
            - containerPort: 8080
```

• Implementa un Service en Kubernetes para exponer la aplicación Tower Defense a través de
una IP pública. Utiliza un LoadBalancer para distribuir el tráfico entre múltiples réplicas de la
aplicación.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: tower-defense-service
spec:
  selector:
    app: tower-defense-game
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
```


• Despliega la aplicación Tower Defense en un clúster de Kubernetes. Utiliza kubectl para
gestionar el despliegue y verificar que la aplicación funcione correctamente en el clúster.


```bash
kubectl apply -f deployment.yaml

kubectl apply -f service.yaml
```

![img_8.png](Imagenes%2Fimg_8.png)

Verificamos el estado de nuestro despliegue

![img_9.png](Imagenes%2Fimg_9.png)



### Ejercicio 4: Pruebas unitarias y de integración con Mockito (4 puntos)


**Teoría:**

• Explica los conceptos de mocks, stubs y fakes. Discute cuándo y cómo se deben utilizar estos
patrones en las pruebas unitarias.

Los stubs solo proporcionan respuestas definidas, los mocks definen comportamientos, es decir, aparte de
devolver una respuesta definida se deben probar cual es el flujo en el que se da.

• Describe el proceso de creación de pruebas unitarias con Mockito. Explica cómo se pueden
simular dependencias y verificar comportamientos en las pruebas.

Las pruebas unitarias como su propio nombre lo dice se encarga de probar una cosa, un metodo de una clase por ejemplo,
el problema es que muchas veces este metodo depende de otras clases (DOCs) y si estas clases aun no estan implementada, tenemos un problema!
Es por esto que mockito resuelve esto, preocupandose unicamente en que deberia devolver esta clase aun no implementada para
que podamos probar satisfactoriamente la clase bajo prueba (Sut).

**Practica:**

Use mockito en las siguientes pruebas: 

**Stubs**

En este caso estamos utilizando stubs para poder crear distintas olas de enemigos.

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

**Mocks**

Aqui usamos mocks para simular y rastrear el compartamiento de la clase enemy,
en este caso queremos simular la posicion y la vida de este, para determinar
si un enemigo es alcanzado por la torre o no, es decir, estamos probando el compartimiento de la 
clase Tower, en especial, el metodo attack por lo que no nos interesa ahora la implementacion
de la clase Enemigo, ya que esta es una prueba unitaria, solo nos estamos centrando en el comportamiento
del metodo attack.

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


