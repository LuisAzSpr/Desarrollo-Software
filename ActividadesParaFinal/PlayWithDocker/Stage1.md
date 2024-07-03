
# PLAY WITH DOCKER (Stage I)

## first alpine Linux Containers

En esta actividad aprenderemos los siguientes conceptos clave:

- Docker engine
- Contenedores e imagenes
- Registro de imagenes y Docker hub
- Isolacion de contenedores

### 1.0 Corriendo nuestro primer contenedor

Ejecutamos el siguiente comando: 

```bash
docker container run hello-world
```

![img.png](ImagenesStage1%2Fimg.png)

**Explicacion**

Podemos ver que queremos correr un contenedor con la imagen hello-world,
sin embargo esta imagen no se encuentra localmente ...

```
Unable to find image hello-world
```

por lo que **docker engine**
ira el registro por defecto de docker llamado **docker hub** a buscar la imagen
hello-world, en donde la encuentra y la descarga

```
latest: Pulling from library/hello-world
c1ec31eb5944: Pull complete
Digest: sha256:94323f3e5e09a8b9515d74337010375a456c909543e1ff1538f5116d38ab3989
Status: Downloaded newer image for hello-world:latest
```

Luego podemos ver que el contenedor se ejecuta con la imagen hello-world e imprime lo siguiente:

```
Hello from Docker!
This message shows that your installation appears to be working correctly.
.
.
.
```

Podemos ver esta imagen en la actividad que nos describe claramente como es que funciona

![img_1.png](ImagenesStage1%2Fimg_1.png)

1. Introducimos y ejecutamos el comando.
2. Consultamos en docker hub la imagen hello-world.
3. Encuentra la ultima version de la imagen en el repositorio library
4. Devuelve la imagen para que sea ejecutada en un contenedor

### 1.1 Imagenes de docker

En este caso correremos un contenedor basado en la imagen de Alpine. 
Alpine es una distribucion de Linux bastante ligera y es ideal para trabajar
en estos casos.

El comando pull trae la imagen desde docker-hub y la guarda localmente.

Ejecutamos el siguiente comando:
```bash
docker image pull alpine
```
![img_2.png](ImagenesStage1%2Fimg_2.png)

Ahora veamos las imagenes que tenemos en nuestro sistema, con el comando:
```bash
docker image ls
```
![img_4.png](ImagenesStage1%2Fimg_4.png)

Ahora vamos a correr un contenedor de docker basada en la imagen de alpine y ademas, agregaremos un
comando extra que se ejecutara cuando Alpine este corriendo dentro del contenedor. 

```bash
docker container run alpine ls -l
```

![img_5.png](ImagenesStage1%2Fimg_5.png)

**Explicacion:**

El comando anterior ejecuta un conjunto de tareas especificas para poder listar los 
directorios de alpine ejecutandose dentro del contenedor.

1. Primero corremos el contenedor de Alpine y mandamos el comando ls -l.
2. Alpine, ejecutandose en el contenedor, recibe el comando ls -l y lo ejecuta.
3. Por ultimo alpine da como salida la ejecucion del comando ls -l al sistema operativo host y se apaga.

En la actividad hay una imagen que hace referencia a esto:

![img_6.png](ImagenesStage1%2Fimg_6.png)

Como podemos ver, primero se ejecuta el comando en el localhost, luego
este comando crea un contenedor en donde comienza a correr Alpine, ahora
el comando **ls -l** es enviado a Alpine, el cual lo ejecuta y la salida es
retornada al SO host y el contenedor se apaga, posteriormente el SO host
muestra la salida por consola.

![img_7.png](ImagenesStage1%2Fimg_7.png)

Probemos el siguiente comando:

```bash
docker container run alpine /bin/sh
```

Al parecer no muestra ninguna salida, esto es porque no estamos ejecutando ningun comando,
se inicio una instancia del contenedor y ejecuto el comando /bin/sh luego se salio del shell y se apago el contenedor.

![img_50.png](ImagenesStage1%2Fimg_50.png)

En teoria se esperaba un shell interactivo en el que podamos escribir comandos, para esto
podemos ejecutar el siguiente comando, agregando la bandera -it:

```bash
docker container run -it alpine /bin/sh
```

Y una vez dentro podemos listar los directorios como vemos en la imagen inferior:

![img_51.png](ImagenesStage1%2Fimg_51.png)

El comando inferior solo sirve para ver los contenedores que estan corriendo.

```bash
docker container ls
```
Por lo que es mejor agregarle la bandera -a, esto mostrara todos los contenedores creados, aqui podemos ver 
que para el laboratorio de hoy creamos 4.

```bash
docker container ls -a
```

Lo que podemos ver es una lista de todos los contenedores que ejecutamos.
Observamos tambien que la columna ESTADO muestra que estos contenedores dejaron de correr hace algún tiempo.

![img_52.png](ImagenesStage1%2Fimg_52.png)

Como podemos ver en la imagen inferior cada vez que corrimos una imagen
se esta creando un contenedor, es decir, una instancia con una tarea especifica a ejecutar.

![img_53.png](ImagenesStage1%2Fimg_53.png)

### 1.2 Container Isolation

Ahora veamos un poco de la isolacion de los conetendores, esto indica que los contenedores son aislados. 

Veamos un ejemplo, corramos una imagen de alpine y dentro abramos una linea de comandos para
posteriormente crear un archivo llamado hello.txt. 

![img_10.png](ImagenesStage1%2Fimg_10.png)

Luego salimos y corremos de nuevo una imagen de alpine, como podemos ver que no se encuentra
el archivo hello.txt creado inicialmente, esto se debe a que cada instancia esta totalmente aislada
de las otras y del host.

![img_11.png](ImagenesStage1%2Fimg_11.png)

Ahora veamos cual fue la imagen que habiamos corrido con el comando /bin/ash con el que 
escribimos el archivo hello.txt, este tiene un ID 7656...

![img_12.png](ImagenesStage1%2Fimg_12.png)

Ahora volvamos  acorrer el contenedor listando los archivos...

Como podemos ver en la linea 4 se encuentra el archivo hello.txt, esto se debe a que 
los contenedores almacenan en cierto modo el ultimo estado de la imagen en ese contedor.

![img_13.png](ImagenesStage1%2Fimg_13.png)

Como podemos ver en la imagen inferior, cada contenedor creado realmente es como una
instancia de la imagen que esta totalmente aislada de las otras instancias creadas anteriormente.

![img_54.png](ImagenesStage1%2Fimg_54.png)

## Doing More With Docker Images

### Image creation from a container


Iniciamos un contenedor con la imagen base de ubuntu e iniciamos el shell bash en la imagen.

![img_14.png](ImagenesStage1%2Fimg_14.png)

Ejecutamos los comandos siguientes para poder instalar figlet con la cual imprimiremos 
la imagen que se muestra en consola.

```bash
apt-get update
apt-get install -y figlet
figlet "hello docker"
```

![img_15.png](ImagenesStage1%2Fimg_15.png)

Supongamos que queremos mostrarle esto a un amigo, tendria
que volver a realizar los pasos que hemos hecho, descragar una imagen base de ubuntu,
instalar figlet y ejecutar el comando figlet "hello world", esto parece ser sencillo
ya que solo estamos instalando un paquete, pero si queremos construir un proyecto 
en el que tenemos que instalar multiples dependencias, archivos, etc, esto es poco
viable, por lo que ahora veamos como podemos crear nuestra propia imagen, para que pueda
ser ejecutada en cualquier contenedor, asi nuestro amigo solo necesitara tener docker
en su maquina y correr nuestra imagen.

Veamos primero cual es el ID de nuestra contenedor :

![img_16.png](ImagenesStage1%2Fimg_16.png)

Podemos ver claramente que el ID del contenedor es **2853372a329d**.

Antes de crear nuestra propia imagen veamos cuales son los cambios que hemos hecho, es decir, cuales
son las diferencias con respecto a la imagen base de ubuntu.

![img_17.png](ImagenesStage1%2Fimg_17.png)

Ahora creremos nuestra propia imagen, para esto necesitamos comitear el contenedor 
en el que se aplicaron los cambios.

![img_18.png](ImagenesStage1%2Fimg_18.png)

Ahora listamos las imagenes para ver que, en efecto, hemos creado nuestra primera imagen!!

![img_19.png](ImagenesStage1%2Fimg_19.png)

Sin embargo, vemos que en el campo repository y tag existe un valor de none para
la imagen creada, esto es porque no le hemos dado un nombre a la hora de crearla, pero
no hay problema ya que esto puede hacerse despues creada la imagen.

```bash
docker image tag <IMAGE_ID> ourfiglet
```

Podemos ver que ahora le hemos dado un nombre a nuestra imagen, por lo que 
ahora podemos llamarlo por su nombre.

![img_20.png](ImagenesStage1%2Fimg_20.png)

En la actividad tenemos una imagen que ejemplifica de manera clara que es lo que
acabamos de hacer.

![img_21.png](ImagenesStage1%2Fimg_21.png)

Primero se ha creado un contenedor en donde se ejecuto la imagen base
de ubuntu, luego hemos instalado un paquete y hemos apagado el contenedor. 
Por ultimo hemos comiteado los cambios del contenedor apagado, esto crea una copia del ultimo estado
de la imagen que estuvo corriendo en ese contenedor.

Ahora podemos ejecutar el siguietne comando:

```bash
docker container run ourfiglet figlet hello
```

Como podemos ver en la imagen inferior, el "hello" se
imprime de manera correcta ya que en nuestra imagen ourfiglet
habiamos instalado el paquete figlet, por lo que no deberia
haber problemas con ejectar el comando figlet hello.

![img_22.png](ImagenesStage1%2Fimg_22.png)

Lo mismo no pasaria si ejecutamos el siguiente comando:

```bash
docker container run ubuntu figlet hello
```

Como podemos ver nos da un error de que no se encontro
figlet, esto se debe a que estamos ejecutando ese comando
sobre la imagen base de ubuntu que no contiene ese paquete.

![img_23.png](ImagenesStage1%2Fimg_23.png)

Como pudimos ver en el ejemplo podemos instalar librerias,
crear archivos y hacer todo lo que queramos con la imagen base
y posteriormente podemos crear una imagen apartir del contenedor
en el cual esta corriendo y ejecutando todos esos cambios, posteriormente
podemos subir esta imagen a un repositorio de docker como Docker Hub,

### Image creation using a Dockerfile

En lugar de crear una imagen a base de puro comando, podemos
hacerlo en un archivo llamado Dockerfile, esto lo hace mucho
mas optimo permitiendo abarca mas cambios y configuraciones con
respecto a la imagen base, ademas nos permite "documentar" 
todos los cambios, instalaciones, versionamientos en un mismo archivo.

Ahora realizaremos un ejemplo con un "hola host" en node.js.
Para esto creamos un archivo index.js .

![img_24.png](ImagenesStage1%2Fimg_24.png)

Y creamos el respectivo archivo Dockerfile, el cual contiene 5 lineas:

1. **FROM alpine** : Indica la imagen base sobre la cual estamos construyendo nuestra
imagen, en este caso es una distribucion de linux ligera(alpine).

2. **RUN apk update && apk add nodejs** : Corremos los comandos apk update (actualiza los paquetes) y  apk add nodejs
(instala nodejs) sobre la imagen de alpine corriendo en el contenedor.

3. **COPY . /app** : Copiamos todo lo que se encuentra en el directorio actual (index.js) en la ruta /app
de Alpine.

4. **WORKDIR /app** : Establecemos el directorio de trabajo en /app, es decir, cualquier comando se ejecutara
en este directorio.

5. **CMD ["node","index.js]** :Y por ultimo corremos nuestra aplicacion node pasandole una lista con el comando y el argumento.

![img_25.png](ImagenesStage1%2Fimg_25.png)

Ahora podemos ejecutar el siguiente comando indicando como **hello** el nombre de la imagen
**v0.1** la version de la imagen y el **.** referenciando el directorio actual como el contexto en el cual se esta construyendo,
que para este ejemplo es en donde se encuentra el Dockerfile.

```bash
$ docker image build -t hello:v0.1 .
```

En la ejecucion del comando podemos ver las siguientes salidas en el log:

Estas salidas hacen referencia a los pasos que se estan siguiendo para la creacion de la imagen
que como podemos ver, son las lineas escritas en el dockerfile, sin embargo, falta la ultima
que se ejecutara cuando el contenedor este corriendo.

```
=> [1/4] FROM docker.io/library/alpine@sha256:b89d9c93e9ed3597455c90a0b88a8bbb5cb7188438f70953fede212a  0.4s
=> [2/4] RUN apk update && apk add nodejs                                                               2.7s
=> [3/4] COPY . /app                                                                                    0.1s
=> [4/4] WORKDIR /app                                                                                   0.0s
```
![img_26.png](ImagenesStage1%2Fimg_26.png)

Por ultimo al correr el contenedor con nuestra imagen creada, podemos ver
que se ejecuta la ultima instruccion que ejecuta nuestro archivo index.js.

![img_27.png](ImagenesStage1%2Fimg_27.png)

Al final podemos ver que nuestra imagen docker es la combinacion de 4 cosas, como se muestra en la imagen inferior.

1. La imagen base Alpine.
2. Actualizacion de los paquetes e instalacion del paquete nodejs
3. Un directorio app con el archivo index.js
4. Configuracion para que el directorio de trabajo sea app y se ejecute index.js cada que el contenedor corra esta imagen.

![img_29.png](ImagenesStage1%2Fimg_29.png)


Podemos resumir que los dockerfiles son importantes porque estamos documentando 
toda la creacion de la imagen, es decir, estamos especificando los comandos
especificos para crear la imagen y asi podemos repetir este proceso de manera sencilla en caso
se requiera.

### Image layers

Ahora entraremos a un concepto interesante relacionado a lo que vimos en la 
imagen anterior, supongamos que queremos realizar un cambio a nuestro archivo 
node.js, entonces tendriamos que crear una nueva imagen, descargar la imagen base
alpine, actualizar e instalar node, etc. Cuando lo unico que deberia cambiar es 
el archivo index.js, es decir, la ultima linea. Si habria alguna forma de guardar
los estados para que solo se altere lo que ha cambiado...
Y aqui surge la siguiente pregunta:

**Se pueden guardar los estados intermedios de una imagen en el proceso en el que pasa de la imagen base
a la imagen final?**

Y es aqui donde entra el concepto de capas, para esto veamos el historial de la imagen creada anteriormente.
Como podemos ver en el screenshoot inferior, tenemos 6 imagenes en donde cada una de estas imagenes fue creada por una instruccion
Dockerfile. Esto nos permite reutilizar las imagenes de las capas que no han cambiado, lo que a su vez hace
que la actualizacion de imagenes sea mas rapida y eficiente.

![img_30.png](ImagenesStage1%2Fimg_30.png)

Veamos un ejemplo, actualicemos el archivo index.js

![img_31.png](ImagenesStage1%2Fimg_31.png)

Ahora crearemos una nueva imagen con la etiqueta v0.2 indicando que 
es la version 2, esto es importante para las personas que reutilicen nuestra imagen despues.

![img_32.png](ImagenesStage1%2Fimg_32.png)

Podemos ver que los tiempos son ceros para las capas 1 y 2. **(v0.2)**

```
=> [1/4] FROM docker.io/library/alpine@sha256:b89d9c93e9ed3597455c90a0b88a8bbb5cb7188438f70953fede212a  0.0s                                                                   0.0s
=> CACHED [2/4] RUN apk update && apk add nodejs                                                        0.0s
=> [3/4] COPY . /app                                                                                    0.1s
=> [4/4] WORKDIR /app                                                                                   0.1s
```

A diferencia de cuando construimos la primera version de la imagen. **(v0.1)**

```
=> [1/4] FROM docker.io/library/alpine@sha256:b89d9c93e9ed3597455c90a0b88a8bbb5cb7188438f70953fede212a  0.4s
=> [2/4] RUN apk update && apk add nodejs                                                               2.7s
=> [3/4] COPY . /app                                                                                    0.1s
=> [4/4] WORKDIR /app                                                                                   0.0s
```

### Layers & Cache

Docker es capaz de identificar que existen capas que ya han sido creadas
anteriormente y usa el cache de estas capas, en lugar de descargar la imagen alpine de nuevo de docker hub
e instalar nuevamente los paquetes nuevamente. 

![img_33.png](ImagenesStage1%2Fimg_33.png)

### Image Inspection

Ahora veamos la lista de capas de cada imagen.


- Alpine es solo una pequeña imagen base de SO, por lo que hay solo una capa.

    ![img_34.png](ImagenesStage1%2Fimg_34.png)
    
    ```
    ["sha256:94e5f06ff8e3d4441dc3cd8b090ff38dc911bfa8ebdb0dc28395bc98f82f983f"]
    ```

- Ahora podemos ver que tenemos una lista de 4 capas para nuestra imagenpersonalizada, esto se
debe a que justamente habian 4 instrucciones que creaban a esta imagen. 

    ![img_35.png](ImagenesStage1%2Fimg_35.png)
    
    ```
    ["sha256:94e5f06ff8e3d4441dc3cd8b090ff38dc911bfa8ebdb0dc28395bc98f82f983f",
    "sha256:ec8f189859a936f059c49f99759ef76e5bb5a278dd7fd59f6f8918b776ffd142",
    "sha256:79257cc8465f3db969c98a93a0e29e90b56b1135dee3b1e455576a1918cac8f7",
    "sha256:5f70bf18a086007016e948b04aed3b82103a36bea41755b6cddfaf10ace3c6ef"]
    ```


## Swarm Mode Introduction for IT Pros

**Compose:** Gestiona múltiples contenedores en un solo host, ideal para desarrollo y pruebas, pero no adecuado para producción a gran escala.

**Swarm:** Gestiona y coordina múltiples contenedores a través de múltiples hosts, proporcionando alta disponibilidad y escalabilidad para entornos de producción.


Al iniciar el dower swarm, el nodo actual se convierte en un nodo manager (en este caso el nodo1).

En la salida de la inicializacion del docker swarm, podemos apreciar 
un comando que usaremos despues para añadir nodos workers al swarm.

```bash
docker swarm init --advertise-addr $(hostname -i)
```

![img_36.png](ImagenesStage1%2Fimg_36.png)

En otro nodo (nodo2) podemos ejecutar el comando anteriormente mostrado en la inicializacion
para añadir este nodo al swarm.

```bash
docker swarm join --token SWMTKN-1-5ywzxsjvglej...
```

![img_37.png](ImagenesStage1%2Fimg_37.png)

Desde la primera ventana del terminal (nodo 1), vamos a verificar el numero de nodos
que existen, es necesario hacerlo desde el nodo 1 ya que este es un nodo manager.

Podemos ver en la imagen inferior, que tenemos un nodo leader(nodo1) que fue creado al inicializar
el swarm y otro nodo worker que fue agregado al swarm con el comando anterior.
```
docker node ls
```
![img_38.png](ImagenesStage1%2Fimg_38.png)

Clonamos el proyecto que vamos a deployar

![img_39.png](ImagenesStage1%2Fimg_39.png)


![img_40.png](ImagenesStage1%2Fimg_40.png)

En el archivo docker-stack.yml, podemos ver que existen 2 redes en las cuales existen 5 servicios.

**Red Frontend**

En esta red tenemos los siguientes servicios:
- **redis** : Especifica la imagen base redis:alpine con la cual construiremos este contenedor. 
```
redis:
    image: redis:alpine 
    networks:
      - frontend
```

- **vote** : Especifica la imagen base exampleveotingapp_vote, el puerto en el cual estara escuchando y el
numero de replicas creadas para este servicio.

```
  vote:
    image: dockersamples/examplevotingapp_vote
    ports:
      - 5000:80
    networks:
      - frontend
    deploy:
      replicas: 2
```

**Red Backend**

En esta red tenemos los siguientes servicios:

- **db** : En el caso de la base de datos estamos usando la imagen postgres version 15 para alpine,
proporcionamos las credenciales para conectarnos en la base de datos, definimos el volumen el cual
nos ayudara a persistir los datos.

```
  db:
    image: postgres:15-alpine
    environment:
      POSTGRES_USER: "postgres"
      POSTGRES_PASSWORD: "postgres"
    volumes:
      - db-data:/var/lib/postgresql/data
    networks:
      - backend
```
- **result** : En el caso de este servicio, especificamos su imagen y el puerto en el que estara escuchando.
```
  result:
    image: dockersamples/examplevotingapp_result
    ports:
      - 5001:80
    networks:
      - backend
```
- **worker** : Especificamos la imagen, en este caso este servicio pertenecera tanto a la red 
frontend como backend, ademas el numero de replicas sera 2.

```
  worker:
    image: dockersamples/examplevotingapp_worker
    networks:
      - frontend
      - backend
    deploy:
      replicas: 2
```

Ahora desplegamos el stack de servicios que hemos definido en nuestro docker-stack

Como podemos ver en la imagen inferior, primero se crean las redes frontend y backend, posteriormente
se empiezan a crear los servicios y a cada uno se les asigna la red que ha sido declarada.

```bash
docker stack deploy --compose-file=docker-stack.yml voting_stack
```

![img_41.png](ImagenesStage1%2Fimg_41.png)

Como podemos ver en la imagen inferior existen 5 servicios  desplegados, estos son los que detallamos
en la parte superior.

```bash
docker stack ls
```

![img_42.png](ImagenesStage1%2Fimg_42.png)

Podemos ver informacion mas detallada de los servicios con el comando siguiente:

```bash
docker stack services voting_stack
```

Como podemos ver en la imagen inferior, podemos ver principalmente un id, el nombre del servicio,
el numero de replicas que tiene cada servicio y la imagen con la cual fue creada.

![img_43.png](ImagenesStage1%2Fimg_43.png)

Ahora usamos el siguiente comando para listar las tareas (contenedores en ejecucion) asociadas un
servicio en especifico.

```bash
docker stack services <serviceName>
```

Para el caso de voting_stack_vote teniamos 2 replicas, por lo que si corremos el comando
notaremos que existen 2 contenedores corriendo este mismo servicio. 

```bash
docker stack services voting_stack_vote
```
![img_45.png](ImagenesStage1%2Fimg_45.png)

Podemos probar para otro servicio como voting_stack_db, como podemos ver en la imagen inferior, solo tenemos
una replica corriendo en el nodo1 esto es de esperarse ya que no se declararon cuantas replicas tendria este servicio
y por defecto se le asigna 1.

```bash
docker stack services voting_stack_db
```

![img_46.png](ImagenesStage1%2Fimg_46.png)

En la imagen inferior nos representan la arquitectura de la aplicacion, de aqui podemos sacar las siguientes conclusiones:

1. **Stack** : Es el conjunto de servicios que son desplegados conjuntamente y dependientes para que la aplicaciones
funcione como se esperaba.
2. **Tasks** : Cada contenedor puede ejecutar una unica tarea, en este caso si creamos 2 replicas de un servicio, cada una de
estas replicas sera una tarea ejecutada por un contenedor.
3. **Service** : Es un componente de un stack que incluye su imagen, numero de replicas y configuraciones extras propias del serivico.

![img_48.png](ImagenesStage1%2Fimg_48.png)

### Scaling An Application

Supongamos ahora que nuestra aplicacion ha comenzado a recibir mas solicitudes que 
nuestros servidores en la parte del frontend no soportan la carga, como podemos agregar mas 
replicas para que este servicio aguante la carga? 

Pese a que existen otras formas de implementar automaticamente, aqui usaremos el comando:

```bash
docker service scale voting_stack_vote = 5
```

![img_47.png](ImagenesStage1%2Fimg_47.png)

Por utlimo podemos verificar que existen 5 replicas corriendo en nuestros nodos:

2 de estas replicas fueron inicializadas hace 48 minutos, que fueron las que creamos al inicio, las otras 3 fueron inicializadas
recientemente.

![img_49.png](ImagenesStage1%2Fimg_49.png)

En conclusion podemos ver lo facil que fue escalar nuestra aplicacion para que soporte mas carga y orquestar
nuestros servicios con docker swarm con algunos comandos.

