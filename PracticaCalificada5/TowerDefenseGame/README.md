# Docker-Kubernetes-Microservicios

Agregamos a nuestro build.gradle 

```
jar {
    manifest {
        attributes(
            'Main-Class': 'org.example.Main'  
        )
    }
}
```

Creamos nuestro dockerfile a la altura de :

![img.png](Imagenes%2Fimg.png)

Veamos nuestro archivo dockerfile

```
FROM openjdk:17

WORKDIR /app

COPY . /app

RUN ./gradlew build

CMD ["java","-jar","build/libs/TowerDefenseGame-1.0-SNAPSHOT.jar"]
```


```
FROM openjdk:17

WORKDIR /app

COPY . /app

RUN ./gradlew build

CMD ["java","-jar","build/libs/TowerDefenseGame-1.0-SNAPSHOT.jar"]
```

Creamos nuestra imagen docker

![img_1.png](Imagenes%2Fimg_1.png)

Confirmamos que la imagen docker fue creada:

![img_2.png](Imagenes%2Fimg_2.png)

Corremos el contenedor

![img_3.png](Imagenes%2Fimg_3.png)

