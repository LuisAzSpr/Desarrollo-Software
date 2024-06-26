# Actividad Docker-kubernetes-Microservicios

## version I

Creamos nuestro dockerfile a la altura de :

![img_4.png](Imagenes%2Fimg_4.png)

```Dockerfile
FROM openjdk:17

WORKDIR /app

COPY src/main/java /app

RUN javac org/example/*.java

CMD ["java", "org.example.Main"]

```

Creamos la imagen

![img_5.png](Imagenes%2Fimg_5.png)

Corremos el contenedor

![img_6.png](Imagenes%2Fimg_6.png)

En otro terminal ejecutamos exec, podemos ver
todos los archivos de nuestro proyecto aqui.

![img_7.png](Imagenes%2Fimg_7.png)

Creamos nuestro archivo docker-compose

Archivo : **docker-compose.yml**

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

Ahora hacemos un despliegue en kubernetes


Archivo :  **deployment.yaml**

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

Archivo :  **service.yaml**

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

Aplicamos los archivos de configuracion en kubernetes

![img_8.png](Imagenes%2Fimg_8.png)

Verificamos el estado de nuestro despliegue

![img_9.png](Imagenes%2Fimg_9.png)


## version II

Teoría de conceptos clave

```bash
docker exec
```

El comandodocker exec permite ejecutar comandos dentro de un contenedor Docker en ejecución. Esto
es útil para depuración, administración y mantenimiento. Por ejemplo:

![img_10.png](Imagenes%2Fimg_10.png)

Creamos la red y el volumen : 

![img_12.png](Imagenes%2Fimg_12.png)

Corremos nuestro contenedor: 

![img_14.png](Imagenes%2Fimg_14.png)


Creamos nuestro archivo docker-compose 

Archivo : **docker-compose.yml**

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

Archivo :  **Deployment.yaml**

Un Deployment asegura que una cantidad especificada de réplicas de una aplicación estén corriendo en
cualquier momento. Ejemplo dedeployment.yaml:

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

Archivo :  **service.yaml**

Un Service en Kubernetes expone una aplicación corriendo en uno o más Pods como un servicio de red.
Ejemplo deservice.yaml:

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

Aplicamos los archivos de configuracion en kubernetes

![img_8.png](Imagenes%2Fimg_8.png)

Verificamos el estado de nuestro despliegue

![img_9.png](Imagenes%2Fimg_9.png)