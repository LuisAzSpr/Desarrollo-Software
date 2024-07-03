# Docker - Kubernetes - Microservicios

Lo primero que debemos preguntarnos para esta actividad es
como y en donde creamos los archivos, al principio intente
crear unicamente un directorio, esto puede funcionar si 
usamos un dockerfile que solo corra con java, sin embargo,
al estar trabajando en gradle y aprovechar las librerias
que podemos importar y todo lo que podemos implementar en
nuestro proyecto es mejor usar modulos, porque esto puede comportarse
como un subproyecto gradle y utilizar los comando conocidos, en cada dockerfile.


- **./gradlew build**

- **./gradlew test**


Como puedo estar viendo a la izquierda o en la imagen inferior, es como crear un subproyecto
con sus propios archivos como el dockerfile, builgradle, build, etc...

En este caso es el microservicio GameService

![img.png](Imagenes%2Fimg.png)

Ademas el Dockerfile de cada uno de estos microservicios se hara como sigue:

Gracias a que esto es un modulo que se comportar como un subproyecto gradle, podemos usar
el comando ./gradlew build.

```Dockerfile
FROM openjdk:17

WORKDIR /app

COPY ../GameService /app

RUN chmod +x ./gradlew build

CMD ["java", "-cp", "build/classes/java/GameService", "org.example.GameService"]
```

**Nota:** Se recomiendan que los nombres de los modulos esten en minuscula.

Ahora se crea el docker-compose, en este caso se crea la imagen de cada microservicio y se le asigna una red para poder comunicarse(game-network)
y un volumen de datos para poder persistir los datos(game-data).

```yaml
version: '3'
services:
  game:
    build: ./game-service
    networks:
      - game-network
    volumes:
      - game-data:/app/data

  map:
    build: ./map-service
    networks:
      - game-network

  player:
    build: ./player-service
    networks:
      - game-network

  tower:
    build: ./tower-service
    networks:
      - game-network

  wave:
    build: ./wave-service
    networks:
      - game-network

networks:
  game-network:
    driver: bridge

volumes:
  game-data:
    driver: local

```