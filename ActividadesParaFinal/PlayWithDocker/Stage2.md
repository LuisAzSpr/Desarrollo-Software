# PLAY WITH DOCKER (Stage II)


## Security Lab: Seccomp



## Security Lab: Capabilities
Antes de empezar este Laboratorio me gustaria aclarar que no soy tan bueno en seguridad, por lo
que especificare mas a detalle ciertos conceptos no tan claros para mi :).

**Empecemos:**

- root : Es el usuario que tiene todos los permisos dentro de un SO.
- Capacidad : Una capacidad nos permite dividir los privilegios del superusuario(root) en unidades mas pequeñas, es decir, 
a un usuario podemos agregarle ciertas capacidades especificas sobre el sistema sin otorgarles control total sobre el mismo.

El objetivo de este laboratorio es justamente añadir y eliminar capacidades en los contenedores. 


### Step 1: Introduction to capabilities
En este paso aprenderemos los conceptos básicos de las capacidades.

Como habiamos mencionado anteriormente se pueden desglosar los privilegios del usuario root
en unidades distintas llamadas capacidades.

Ejemplo de estos son:

- **CAP_CHOWN** : permite al usuario root cambiar arbitrariamente los UID y GID de los archivos
- **CAP_DAC_OVERRIDE**: permite al usuario root ignorar las verificaciones de permisos del kernel en las operaciones de lectura, escritura y ejecución de archivos.

En general se entiende que cualquier permiso (accion que puede realizar un usuario sobre los recursos del sistema) es una capacidad.

**Ahora la pregunta es que beneficios nos da esto?**

Desglosar los privilegios de root en capacidades nos permite eliminar ciertas capacidades del usuario root haciendolo 
menos poderoso y añadir capacidades a un usuario root de manera completamente controlada tal que estemos seguros que permisos le estamos
dando a este usuario.

**En donde se aplican las capacidades?**

1. **Capacidades de archivos**: Otorgar privilegios elevados a programas. 
2. **Capacidades de hilos**: Gestionar los privilegios de procesos.

Docker establece un conjunto límites de capacidades antes de iniciar un contenedor.
Esto quiere decir que cualquier proceso que se ejecute dentro del contenedor no podra
obtener capacidad mas alla de las establecidas en el conjunto limite.
Se puede usar comandos de Docker para añadir o eliminar capacidades del conjunto de límites

Por defecto, Docker elimina todas las capacidades excepto las necesarias, utilizando un enfoque de lista blanca.

### Step 2: Working with Docker and capabilities

Ahora toca trabajar con docker y sus capacidades, existen 3 opciones principalmente en docker para usar capacidades:


1. Ejecutar contenedores como root con un gran conjunto de capacidades y tratar de gestionar las capacidades dentro de nuestro contenedor manualmente. (NO RECOMENDABLE)
2. Ejecutar contenedores como root con capacidades limitadas y nunca camb iarlas dentro de un contenedor. (RECOMENDABLE)
3. Ejecutar contenedores como un usuario no privilegiado sin capacidades. (RECOMENDABLE PERO POCO REALISTA)


**Sea $CAP una o mas capacidades individuales**, tenemos los siguientes comandos para poder gestionar capacidades dentro de un contenedor:

Para eliminar capacidades de la cuenta root de un contenedor, ejecutamos el siguiente comando:

```bash
docker run --rm -it --cap-drop $CAP alpine sh
```

Para añadir capacidades a la cuenta root de un contenedor, ejecutamos el siguiente comando.


```bash
docker run --rm -it --cap-add $CAP alpine sh
```


Para eliminar todas las capacidades y luego añadir explícitamente capacidades individuales a la cuenta root de un contenedor, ejecutamos el siguiente comando:

```bash
docker run --rm -it --cap-drop ALL --cap-add $CAP alpine sh
```

### Step 3: Testing Docker capabilities

Ahora toca probar los comandos 

Primero vamos a iniciar un contenedor y probar que la cuenta root (superusuario) del contenedor puede cambiar la
propiedad de los archivos.

```bash
docker run --rm -it alpine chown nobody /
```

Como podemos ver no nos arroja ningun error, por lo que el comando se ejecuto correctamente.

![img_50.png](ImagenesStage2%2Fimg_50.png)

Ahora vamos a iniciar otro contenedor nuevo y eliminar todas las capacidades de la cuenta root del contenedor
(**--cap-drop ALL**),  excepto la capacidad CAP_CHOWN (**--cap-add CHOWN**) que nos permite poder cambiar la propiedade de los archivos.

```bash
docker run --rm -it --cap-drop ALL --cap-add CHOWN alpine chown nobody /
```

Como podemos ver tampoco nos sale ningun error, ya que el usuario root tiene la capacidad de CHOWN lo
que le permite cambiar la propiedad de los archivos a nobody.

![img_52.png](ImagenesStage2%2Fimg_52.png)

Ahora vamos a inicializar otro contenedor nuevo y eliminar solo la capacidad CHOWN(**--cap-drop CHOWN**) de su cuenta root.

```bash
docker run --rm -it --cap-drop CHOWN alpine chown nobody /
```

Como podemos ver ahora nos sale un error que nos indica que el usuario root no tiene el permiso
para cambiar la propiedad de los archivos ya que apesar que el usuario root tiene todos los permisos, le hemos
quitado el unico que le hacia falta para ejecutar el contenedor de manera exitosa(CHOWN).


![img_51.png](ImagenesStage2%2Fimg_51.png)


Ahora crearemo otro contenedor nuevo e intenaremos añadir la capacidad CHOWN (**--cap-add chown**) al usuario no-root llamado 
nobody(**-u nobody**). Como parte del mismo comando intentaremos cambiar la propiedad de un archivo o carpeta.

```bash
docker run --rm -it --cap-add chown -u nobody alpine chown nobody /
```

![img_53.png](ImagenesStage2%2Fimg_53.png)


### Step 4: Extra for experts

#### Listando todas las capacidades

El siguiente comando iniciara un nuevo contenedor usando Alpine, instalará el paquete libcap y luego listara las capacidades.

![img_55.png](ImagenesStage2%2Fimg_55.png)



## Docker Networking Hands-on Lab


### Section #1 - Networking Basics

**Step 1: The Docker Network Command**

El comando inferior nos ayuda a configurar y gestionar redes de contenedores.

```bash
docker network
```

Como podemos ver en la imagen inferior  este comando nos ayuda a crear ,
listar, inspeccionar, eliminar y desconectar contenedores de redes.

![img.png](ImagenesStage2%2Fimg.png)


**Step 2: List networks**

El comando inferior nos lista las redes existentes.

```bash
docker network ls 
```

como podemos ver en la imagen inferior tenemos 3 redes de contenedores en nuestro local.
Estas 3 redes son redes estandares creadas en la instalacion de docker.

![img_1.png](ImagenesStage2%2Fimg_1.png)

**Step 3: Inspect a network**

Cuando queremos ver informacion mas detallada de una red ejecutamos el siguiente comando: 

```bash
docker network inspect <networkName>
```

La salida es considerablemente mayor por lo que la copiaremos directamente en el readme.


```
output : 

[
    {
        "Name": "bridge",
        "Id": "25322b70dacb113a81b6ddb3a48b5f26b24fd772432c58a89e457abb0c4e4b9f",
        "Created": "2024-06-30T22:27:37.156176262Z",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "172.17.0.0/16",
                    "Gateway": "172.17.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {},
        "Options": {
            "com.docker.network.bridge.default_bridge": "true",
            "com.docker.network.bridge.enable_icc": "true",
            "com.docker.network.bridge.enable_ip_masquerade": "true",
            "com.docker.network.bridge.host_binding_ipv4": "0.0.0.0",
            "com.docker.network.bridge.name": "docker0",
            "com.docker.network.driver.mtu": "1500"
        },
        "Labels": {}
    }
]
```

Como podemos ver existe informacion relevante de la red.

- Nombre de la red:

    ```
    "Name": "bridge",
    ```
- Informacion de la gestion de direcciones IP.

    ```
    "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "172.17.0.0/16",
                    "Gateway": "172.17.0.1"
                }
            ]
        }
    ```

- Como podemos ver no existen contenedores conectados:
    ```
    "Containers": {},
    ```
  
Entre otros diferentes campos que describen detalladamente a la red. 

**Step 4: List network driver plugins**

Ahora el siguiente comando nos ayudara a ver informacion sobre la instalacion de docker, incluyendo los
plugins de red disponibles.

```bash
docker info 
```

Vamos a extraer unicamente la parte que nos interesa.

![img_2.png](ImagenesStage2%2Fimg_2.png)


**Nota** : Un plugin de red proporciona funcionalidades adicionales para la gestion de nuestras redes.

### Section #2 - Bridge Networking

**Step 1: The Basics**

Como habiamos visto antes, docker viene con una red predefinida, la red Bridge, verifiquemos que este ahi.

```bash
docker network ls 
```

![img_1.png](ImagenesStage2%2Fimg_1.png)

**Nota** : Una red bridge permite que los contenedores se comuniquen entre si y puedan comunicarse con el host.

Ahora instalaremos brctl para listar las redes bridge existentes.

![img_3.png](ImagenesStage2%2Fimg_3.png)

Podemos ver que existe un unico puente con el nombre de docker0 que es el que se crea
por defecto para la red bridge, tambien notamos que no tenemos ninguna interfaz conectada.

![img_4.png](ImagenesStage2%2Fimg_4.png)

**Step 2: Connect a container**

La red bridge, por defecto se conecta a los contenedores cuando no especificamos 
una red.

Corramos una imagen base de ubuntu y dejemosla corriendo con el comando sleep infinity.

![img_5.png](ImagenesStage2%2Fimg_5.png)

Verifiquemos el contenedor esta corriendo.

![img_6.png](ImagenesStage2%2Fimg_6.png)

Podemos ver que ahora tenemos una interfaz conectada.

![img_7.png](ImagenesStage2%2Fimg_7.png)

Ademas podemos verificar ejecutando el comando anterior, los contenedores 
conectados a esta red.

```bash
docker network inspect bridge
```

Podemos ver que existe un contenedor cuyo id coincide con el contenedor que estaba
corriendo.

![img_8.png](ImagenesStage2%2Fimg_8.png)

**Step 3: Test network connectivity**

Podemos ver que la IP del contenedor dentro de la red es

```
"IPv4Address": "172.17.0.2"
```

Ahora usamos el siguiente comando para enviar 5 paquetes y asi verificar la conectividad con el contenedor.

```
ping -c5 172.17.0.2
```
Como podemos ver en la imagen inferior los 5 paquetes fueron recibidos exitosamente.
Con esto confirmamos que el host Docker puede comunicarse con el contenedor a traves de la red bridge.

![img_9.png](ImagenesStage2%2Fimg_9.png)

Ahora obtengamos el ID del contenedor, veamos cuales son los contenedores en marcha.

```
docker ps
```

Podemos ver en la imagen inferior el id del contenedor es c1401...

![img_10.png](ImagenesStage2%2Fimg_10.png)

Accedemos al contenedor e instalamos ping.

![img_11.png](ImagenesStage2%2Fimg_11.png)

Y hacemos ping a un sitio web, para verificar la conectividad con internet de la red.

Como podemos ver en la imagen inferior, desde el contendor podemos acceder a internet gracias a 
que estamos en la red Bridge.

![img_12.png](ImagenesStage2%2Fimg_12.png)



**Step 4: Configure NAT for external connectivity**

Ejecutamos un contenedor con una aplicacion web llamada web1, **-d** ejecuta el contenedor en segundo plano y **-p
8080:80** sera el mapeo del puerto del host con el puerto del contenedor 8080 -> 80. 

```bash
docker run --name web1 -d -p 8080:80 nginx
```

![img_13.png](ImagenesStage2%2Fimg_13.png)

Ahora mediante el comando **docker ps** veamos cuales son los contenedores que estan corriendo, como podemos ver,
se encuentra la imagen base **nginx**, el mapeo de los puertos, nombre del contedor, etc.

![img_14.png](ImagenesStage2%2Fimg_14.png)

Hacemos  una solicitud HTTP al localhost en el puerto 8080 y podemos ver como salida un archivo html, del servicio web nginx.

![img_15.png](ImagenesStage2%2Fimg_15.png)

Podemos que el servicio web nginx dentro del contenedor es accesible desde fuera.

### Section #3 - Overlay Networking

**Step 1: The Basics**

Inicializamos un swarm y agregamos un nodo worker al swarm, esto ya lo vimos anteriormente
asi que no vamos a ser muy detallistas.

Comando para inicializar:

```bash
docker swarm init --advertise-addr $(hostname -i)
```

![img_16.png](ImagenesStage2%2Fimg_16.png)

Comando para unir un nodo worker al swarm.

```bash
docker swarm join --token SWMTKN-1-5zo35tw63e1svy..
```

![img_17.png](ImagenesStage2%2Fimg_17.png)

Al final mostramos los nodos existentes en nuestro swarm.

```bash
docker node ls
```

![img_18.png](ImagenesStage2%2Fimg_18.png)

**Step 2: Create an overlay network**

Creamos una red overlay y le damos el nombre de overnet.

```bash
docker network create -d overlay overnet
```

![img_19.png](ImagenesStage2%2Fimg_19.png)

Listamos las redes con el siguiente comando en el nodo1 y en el nodo2.

```bash
docker network ls
```

Podemos ver que el nodo1 se encuentra en la red overlay

![img_20.png](ImagenesStage2%2Fimg_20.png)

Podemos ver que en el nodo2 NO se encuentra la red overlay

![img_21.png](ImagenesStage2%2Fimg_21.png)

Ahora veamos en detalle la configuracion de la red con el siguiente comando:

```bash
docker network inspect overnet
```


```
output:

[
    {
        "Name": "overnet",
        "Id": "t2x7txfxujya8n2tae7oncf0g",
        "Created": "2024-07-01T13:06:21.125708961Z",
        "Scope": "swarm",
        "Driver": "overlay",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "10.0.1.0/24",
                    "Gateway": "10.0.1.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": null,
        "Options": {
            "com.docker.network.driver.overlay.vxlanid_list": "4097"
        },
        "Labels": null
    }
]
```

**Step 3: Create a service**

Ahora que ya hemos inicializado el swarm y creado la red, podemos agregar servicios que usen esta red.

Creamos el servicio con el nombre de myservice, la red sera la red overlay recientemente creada, este servicio
tendra 2 replicas corriendo el comando sleep infinity en la imagen base de ubuntu.

```bash
docker service create --name myservice \
--network overnet \
--replicas 2 \
ubuntu sleep infinity
```

![img_22.png](ImagenesStage2%2Fimg_22.png)

Podemos ver que el servicio ha sido creado exitosamente y las replicas estan funcionando.

```bash
docker service ls
```

![img_23.png](ImagenesStage2%2Fimg_23.png)

Ademas podemos apreciar que cada replica de este servicio esta corriendo en un nodo
diferente.

```bash
docker service ps myservice
```

![img_24.png](ImagenesStage2%2Fimg_24.png)

Una vez hemos corrido un servicio en la red overnet con 2 replicas, cada replica se ejecutara en un nodo
por lo que si ahora listamos las redes existentes en el nodo2, podemos ver que se encuentra la red overnet al final.

![img_32.png](ImagenesStage2%2Fimg_32.png)

Ahora en el segundo terminal podemos correr el comando para inspeccionar mas a detalle la red overnet.

```bash
docker network inspect overnet
```

```
[
    {
        "Name": "overnet",
        "Id": "ulxcye5xfq4la8mo1oovcakbm",
        "Created": "2024-07-01T14:52:46.987496028Z",
        "Scope": "swarm",
        "Driver": "overlay",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "10.0.1.0/24",
                    "Gateway": "10.0.1.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {
            "00dc467b96c02ace3181b050d6dd2bf862c748aa7ca236261ff16b9f6ff5b2cf": {
                "Name": "myservice.1.9ss6f0lg5yooad51ko1cuagmg",
                "EndpointID": "0c1234fa0b6f0902ad00bda194919b79100beb5e339fd511d5092f5a0b97ce35",
                "MacAddress": "02:42:0a:00:01:03",
                "IPv4Address": "10.0.1.3/24",
                "IPv6Address": ""
            },
            "lb-overnet": {
                "Name": "overnet-endpoint",
                "EndpointID": "eaee3f051b06a9cc05920bdbf90c6dd1be5776a5051edbd5e0289ebe632aedbb",
                "MacAddress": "02:42:0a:00:01:05",
                "IPv4Address": "10.0.1.5/24",
                "IPv6Address": ""
            }
        },
        "Options": {
            "com.docker.network.driver.overlay.vxlanid_list": "4097"
        },
        "Labels": {},
        "Peers": [
            {
                "Name": "52f8aa2fbb45",
                "IP": "192.168.0.12"
            },
            {
                "Name": "4469ce411386",
                "IP": "192.168.0.13"
            }
        ]
    }
]
```

Como podemos ver, solo nos muestra un contenedor ejecutandose cuando en esta red deberian
existir 2 contenedores(replicas) y es que el comando anterior solo muestra 
los contenedores que se estan ejecutando en ese nodo, en este caso, el nodo2.

```
"Containers": {
    "00dc467b96c02ace3181b050d6dd2bf862c748aa7ca236261ff16b9f6ff5b2cf": {
        "Name": "myservice.1.9ss6f0lg5yooad51ko1cuagmg",
        "EndpointID": "0c1234fa0b6f0902ad00bda194919b79100beb5e339fd511d5092f5a0b97ce35",
        "MacAddress": "02:42:0a:00:01:03",
        "IPv4Address": "10.0.1.3/24",
        "IPv6Address": ""
    },
    "lb-overnet": {
        "Name": "overnet-endpoint",
        "EndpointID": "eaee3f051b06a9cc05920bdbf90c6dd1be5776a5051edbd5e0289ebe632aedbb",
        "MacAddress": "02:42:0a:00:01:05",
        "IPv4Address": "10.0.1.5/24",
        "IPv6Address": ""
    }
}
```

**Nota**: El segundo "contenedor" lb-overnet es un balanceador de carga.

**Step 4: Test the network**

Ahora tomemos la IP del servicio corriendo en el nodo2. En la anterior salida podemos
encontrar la Ip del contenedor, que es igual a **10.0.1.3**.

```
"00dc467b96c02ace3181b050d6dd2bf862c748aa7ca236261ff16b9f6ff5b2cf": {
    "Name": "myservice.1.9ss6f0lg5yooad51ko1cuagmg",
    "EndpointID": "0c1234fa0b6f0902ad00bda194919b79100beb5e339fd511d5092f5a0b97ce35",
    "MacAddress": "02:42:0a:00:01:03",
    "IPv4Address": "10.0.1.3/24",
    "IPv6Address": ""
}
```

Ahora ejecutamos el mismo comando con el que hallamos la IP en el nodo2 pero esta vez en el nodo1 y encontramos que la IP
del servicio corriendo en el nodo1 es **10.0.1.4**.

```
"7c518bc42b5c8e7213ffb46b7201d832113a4cb63aec677c2a1afdc5795ddd1e": {
"Name": "myservice.2.t9zkfuv8bg2d1sxqw03z5wy6c",
"EndpointID": "bad71c0d5d8454ad756064f75679bd38755c298e71fe9f031d4630d37f150d7c",
"MacAddress": "02:42:0a:00:01:04",
"IPv4Address": "10.0.1.4/24",
"IPv6Address": ""
```


```bash
docker ps
```

Nos lista todos los contenedores  ejecutandose como podemos ver en la imagen inferior.

![img_25.png](ImagenesStage2%2Fimg_25.png)

Podemos tomar los 4 primeros caracteres del ID del contenedor para que sea distinguible **c7ac**.

Ahora ejecutamos un shell para entrar dentro del contenedor, actualizamos los paquetes e instalamos ping
para poder enviar mensajes y asi comprobar que la conexion entre el host y los contenedores es la correcta.

![img_28.png](ImagenesStage2%2Fimg_28.png)

![img_27.png](ImagenesStage2%2Fimg_27.png)


Como estamos dentro del contenedor del nodo1 la idea esta en poder comunicarnos con el contenedor que esta corriendo en el nodo2
por lo que desde el nodo1 enviaremos 5 paquetes a la direccion IP del nodo2 usando iputils-ping.
Al principio de este paso habiamos hallado que la IP del service corriendo en el contenedor del nodo2 que era **10.0.1.3**

Una vez hallado la IP del contenedor usamos el siguiente comando para poder enviar 5 paquetes hacia la IP 10.0.1.3 (que es la de nuestro contenedor).

```bash
ping -c5 10.0.1.3
```

Como podemos ver en la imagen inferior se reciben los 5 paquetes de manera exitosa.

![img_33.png](ImagenesStage2%2Fimg_33.png)


Como podemos ver que cada tarea del servicio myservice se ejecuta en nodos diferentes, tienen diferentes IPs
y necesitan ponerse a prueba para asegurarnos que cada replica esta trabajando como debe.
Ademas cada una de estas tareas en diferentes nodos pueden comunicarse entre si usando la red overnet creada incialmente.


**Step 5: Test service discovery**

Ahora veamos que contiene el archivo resolv.conf, este archivo se usa para configurar los servidores DNS 
que nos retornan una direccion IP apartir de un nombre de dominio, en este caso el nombre de una red.

```bash
cat /etc/resolv.conf
```

![img_30.png](ImagenesStage2%2Fimg_30.png)

Veamos como funciona el DNS, para esto en lugar de pasar una IP pasamos el nombre de dominio 
al comando que envia 5 paquetes a esa direccion, DNS debe  encargarse de transformar este nombre de la red a una direccion IP.

```bash
ping -c5 myservice
```

En la imagen inferior vemos que obtenemos una respuesta de una direccion IP **10.0.1.2**, quedemosnos con esta
direccion porque la veremos en el siguiente paso...

![img_31.png](ImagenesStage2%2Fimg_31.png)

Ahora salimos del contenedor y vemos con el siguiente comando la configuracion de myservice : 

```bash
docker service inspect myservice
```

En la imagen inferior vemos que la direccion IP virtual es la misma a la que enviamos los paquetes usando el nombre de la red, es decir, DNS se encargo
de resolver el nombre de la red (nombre de dominio) a una direccion IP.

![img_35.png](ImagenesStage2%2Fimg_35.png)

### Cleaning Up

Ahora que ya hemos terminado podemos eliminar el servicio creado, los contenedores que iniciamos
y desahibilitar el swarm.

**Eliminamos el servicio myservice.**

```bash
docker service rm myservice
```

![img_36.png](ImagenesStage2%2Fimg_36.png)


Veamos que ya no existe ese servicio, de hecho ya no tenemos ningun servicio.

![img_38.png](ImagenesStage2%2Fimg_38.png)

Por utimo eliminemos el nodo1 y el nodo2 ejecutando el siguiente comando en cada terminal.


```bash
docker swarm leave --force
```

## Docker Orchestration Hands-on Lab

### Section 1: What is Orchestration

Supongamos que tenemos una aplicacion desplegada, si no usamos Orquestacion esto sera muy costoso y propenso a errores, ya que
tendriamos que hacerlo todo manualmente, estar supervisando que nada falle, etc.

La orquestacion nos permite automatizar este trabajo de tal forma que no nos tengamos que preocupar por la carga
que existe en nuestra aplicacion o por si un servidor se cae, ya que se solucionara de manera automatizada.

### Section 2: Configure Swarm Mode


Ahora creemos un servicio corriendo en un unico nodo con el siguiente comando.

```bash
docker run -dt ubuntu sleep infinity
```

![img_40.png](ImagenesStage2%2Fimg_40.png)


Podemos ver que existe nuestra aplicacion esta corriendo unicamente en un contenedor en nuestro nodo.

```bash
docker ps
```

![img_41.png](ImagenesStage2%2Fimg_41.png)


El problema es el siguiente: 

**Que pasa si nuestro nodo falla?** -> **nuestra aplicacion dejaria de funcionar ....**

**Hay alguna forma de solucionar esto?** -> Podriamos hacer que nuestra aplicacion este corriendo en multiples maquinas, de esta
forma si una falla, las otras podrian tomar las solicitudes (distribuir la carga de este nodo) y nuestra
aplicacion seguiria corriendo.

**Swarm mode:** Es un modo en el que multiples hosts forman un grupo orquestado de maquinas 
llamadas swarm. Nos ayuda a gestionar multiples contenedores corriendo nuestra aplicacion en multiples hosts. 


**Step 2.1 - Create a Manager node**

Primero crearemos un nodo manager con el comando ya visto anteriormente

```bash 
docker swarm init --advertise-addr $(hostname -i)
```

![img_42.png](ImagenesStage2%2Fimg_42.png)

Veamos que, en efecto, el nodo1 es un nodo manager con el comando.

```bash 
docker info
```

Podemos ver que tenemos un unico nodo y este es un nodo manager con el nombre node1.

```
Managers: 1
Nodes: 1
Name: node1
```

**Step 2.2 - Join Worker nodes to the Swarm**

Ahora unamos nodos workers al swarm con el comando en cada terminal del nodo:

```bash
docker swarm join --token SWMTKN-1 -3v1f6... 192.168.0.6:2377
```

![img_43.png](ImagenesStage2%2Fimg_43.png)

Luego en el nodo1 (nodo manager) podemos ver que existen 3 nodos, el nodo actual y los otros 2 nodos workers
agregados.

![img_44.png](ImagenesStage2%2Fimg_44.png)

### Section 3: Deploy applications across multiple hosts

**Step 3.1 - Deploy the application components as Docker services**

Usaremos el concepto de servicios para escalar la aplicacion de manera sencilla y manejar todos los conetenedores asociados a 
nuestra aplicacion como una unica entidad.

**Nota:** Los servicios trabajan con swarms, que son clústeres de contenedores Docker que colaboran para distribuir la carga.

Al crear el servicio con el siguiente comando, le estamos asociando 1 nodo cualquiera de los 3 existentes en el swarm.

```bash
docker service create --name sleep-app ubuntu sleep infinity
```

![img_45.png](ImagenesStage2%2Fimg_45.png)

Listamos los servicios ejecutandose en este nodo.

![img_46.png](ImagenesStage2%2Fimg_46.png)

Hasta el momento parece ser un poco mas de lo mismo, solo estamos corriendo nuestra aplicacion en un nodo, pero
la idea esta en que este nodo  es parte de un swarm esto quiere decir que nuestra aplicacion esta corriendo
sobre un swarm, por consiguiente, podemos escalar esta aplicacion, es decir, asociarle mas nodos y mas contenedores para que la aplicacion pueda soportar
mas carga.

### Section 4: Scale the application

Una de las mejores cosas de los servicios es que se pueden ampliar o reducir segun la demanda, esto quiere decir, supongamos que tengamos
una aplicacion web muy conocida que vende helados, esta aplicacion tendra muchas mas carga en verano que en invierno, por lo que podemos ampliar
o reducir la cantidad de replicas de nuestra aplicacion entre estas 2 temporadas.

Veamos ahora que tenemos corriendo una unica replica de nuestro contenedor con el siguiente comando.

```bash
docker service create --name sleep-app ubuntu sleep infinity
```

como podemos ver en la imagen inferior, solo existe una replica corriendo en el nodo2.

![img_47.png](ImagenesStage2%2Fimg_47.png)

Supongamos que ahora estamos en verano y el numero de solicitudes en nuestra aplicacion web
comienza a ser cada vez mayor por lo que queremos escalar  nuestra aplicacion de tal forma que soporte mucho mas carga, podemos 
hacer que la aplicacion tenga 7 replicas con el siguiente comando:

```bash
docker service update --replicas 7 sleep-app
```

Como podemos ver en la imagen inferior existen 7 replicas distribuidas en los 3 nodos del swarm.

**Falta imagen**
![img_48.png](ImagenesStage2%2Fimg_48.png)
```
ID            NAME         IMAGE          NODE     DESIRED STATE  CURRENT STATE          ERROR  PORTS
7k0flfh2wpt1  sleep-app.1  ubuntu:latest  node1  Running        Running 9 minutes ago
wol6bzq7xf0v  sleep-app.2  ubuntu:latest  node3  Running        Running 2 minutes ago
id50tzzk1qbm  sleep-app.3  ubuntu:latest  node2  Running        Running 2 minutes ago
ozj2itmio16q  sleep-app.4  ubuntu:latest  node3  Running        Running 2 minutes ago
o4rk5aiely2o  sleep-app.5  ubuntu:latest  node2  Running        Running 2 minutes ago
35t0eamu0rue  sleep-app.6  ubuntu:latest  node2  Running        Running 2 minutes ago
44s8d59vr4a8  sleep-app.7  ubuntu:latest  node1  Running        Running 2 minutes ago
```

Ahora supongamos que la temporada de verano ha pasado por lo que el numero de solicitudes
cada vez es menor lo que conduce a una menor carga, por lo que decidimos reducir el numero de replicas de 7 a 4, esto lo
logramos con el siguiente comando:


```bash
docker service update --replicas 4 sleep-app
```

Ahora verificamos que justamente existen 4 replicas de la aplicacion ejecutandose...


**Falta imagen**
```
ID            NAME         IMAGE          NODE     DESIRED STATE  CURRENT STATE           ERROR  PORTS
7k0flfh2wpt1  sleep-app.1  ubuntu:latest  node1  Running        Running 13 minutes ago
wol6bzq7xf0v  sleep-app.2  ubuntu:latest  node3  Running        Running 5 minutes ago
35t0eamu0rue  sleep-app.6  ubuntu:latest  node2  Running        Running 5 minutes ago
44s8d59vr4a8  sleep-app.7  ubuntu:latest  node1  Running        Running 5 minutes ago
```

Acabamos de ver como podemos aumentar y disminuir el numero de tareas que 
se distribuyen en un swarm segun los requerimientos de una aplicacion dependiendo de la temporada.

### Section 5: Drain a node and reschedule the containers

Ahora supongamos que necesitamos dar mantenimiento a un nodo del swarm, evidentemente no 
queremos interrumpir el servicio que estamos brindando a los clientes, por lo que 
el numero de replicas corriendo debe ser el mismo,solo que ahora estas replicas deben distribuirse
en los otros 2 nodos.

Verificamos en el nodo manager el estado de los nodos, para esto corremos el siguiente comando:

```bash
docker node ls
```

A lo cual vemos que los 3 nodos existentes estan corriendo :

**Falta imagen**
```
ID                           HOSTNAME  STATUS  AVAILABILITY  MANAGER STATUS
6dlewb50pj2y66q4zi3egnwbi *  node1     Ready   Active        Leader
ym6sdzrcm08s6ohqmjx9mk3dv    node3     Ready   Active
yu3hbegvwsdpy9esh9t2lr431    node2     Ready   Active
```

Ahora veamos cuales son los contenedores que se encuentran en el nodo2 "nodo a drenar".

Para esto nos vamos al terminal 2 y ejecutamos el siguiente comando para ver los contenedores corriendo en este nodo:

```bash
docker ps 
```

Como podemos ver existe un contenedor con el ID 4e7e... corriendo en este contenedor.

```
CONTAINER ID        IMAGE                                                                            COMMAND             CREATED             STATUS              PORTS               NAMES
4e7ea1154ea4        ubuntu@sha256:dd7808d8792c9841d0b460122f1acf0a2dd1f56404f8d1e56298048885e45535   "sleep infinity"    9 minutes ago       Up 9 minutes                            sleep-app.6.35t0eamu0rueeozz0pj2xaesi
```

Ahora regresamos al terminal1 (nodo1) para ver cual es el ID del nodo2 (nodo que vamos a drenar).
Ejecutamos nuevamente el comando :

```
docker node ls
```

Y en la salida podemos ver que el nodo2 tiene un ID igual a yu3h...


**Falta imagen**
```
ID                           HOSTNAME  STATUS  AVAILABILITY  MANAGER STATUS
6dlewb50pj2y66q4zi3egnwbi *  node1   Ready   Active        Leader
ym6sdzrcm08s6ohqmjx9mk3dv    node3   Ready   Active
yu3hbegvwsdpy9esh9t2lr431    node2   Ready   Active
```

Con el ID del nodo2 ejecutamos el siguiente comando para drenar este nodo

```bash
docker node update --availability drain yu3h
```

Ahora verificamos el campo "Availability" del nodo2, este es igual a Drain:

**Falta imagen**
```
ID                           HOSTNAME  STATUS  AVAILABILITY  MANAGER STATUS
6dlewb50pj2y66q4zi3egnwbi *  node1   Ready   Active        Leader
ym6sdzrcm08s6ohqmjx9mk3dv    node3   Ready   Active
yu3hbegvwsdpy9esh9t2lr431    node2   Ready   Drain
```

Ahora vamos al terminal2 y ejecutamos el siguiente comando para determinar los contenedores corriendo en este nodo:

```bash
docker ps
```

Podemos ver que no hay ningun contenedor corriendo en este nodo:


**Falta imagen**
```
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```

Como podemos ver el numero de replicas corriendo se mantiene en 4, ya que la replica que se estaba ejecutando
en el nodo2 esta apagada.

```
ID            NAME             IMAGE          NODE     DESIRED STATE  CURRENT STATE           ERROR  PORTS
7k0flfh2wpt1  sleep-app.1      ubuntu:latest  node1  Running        Running 25 minutes ago
wol6bzq7xf0v  sleep-app.2      ubuntu:latest  node3  Running        Running 18 minutes ago
s3548wki7rlk  sleep-app.6      ubuntu:latest  node3  Running        Running 3 minutes ago
35t0eamu0rue   \_ sleep-app.6  ubuntu:latest  node2  Shutdown       Shutdown 3 minutes ago
44s8d59vr4a8  sleep-app.7      ubuntu:latest  node1  Running        Running 18 minutes ago
```
Al igual que hicimos en la acitivdad anterior vamos a limpiar el swarm.

Eliminamos el servicio...

```bash
docker service rm sleep-app
```

Eliminamos el contenedor del nodo1 con el que iniciamos

```bash
docker kill containerId1
```

Y en cada terminal de cada nodo ejecutamos lo siguiente para eliminar todos los nodos del swarm.

```bash
docker swarm leave --force
```












