# Play with kubernetes


## Getting Started

### Start the cluster

En este caso tenemos 2 nodos, ambas con sus IPs.

![img_1.png](Imagenes%2Fimg_1.png)


El primer paso es inicializar el cluster en el nodo1, esto hara que el nodo1 sea un 
nodo master. El comando ejecutado anteriormente esta intentando instalar imagenes necesarias para que los
componentes del cluster de Kubernetes puedan funcionar de manera adecuada y asi poder inicializar el cluster.


```bash
kubeadm init --apiserver-advertise-address $(hostname -i)
```

![img.png](Imagenes%2Fimg.png)
![img_3.png](Imagenes%2Fimg_3.png)

En la linea 7 contando de abajo hacia arriba podemos ver que existe un error de almacenamiento en el nodo
que se nos brinda para hacer la actividad, esto no nos permite descargar las imagenes necesarias para que el cluster de
kubernetes sea incializado.

```
rpc error: code = Unknown desc = failed to pull and unpack image "registry.k8s.io/kube-apiserver:v1.27.15": 
write /var/lib/docker/containerd/daemon/io.containerd.snapshotter.v1.overlayfs/snapshots/...:
no space left on device
```

Debido a esto la actividad se hara con las salidas dadas en la misma actividad, lamentablemente no podremos
ejecutar un nodo.

En teoria, el comando anterior:

```bash
kubeadm init --apiserver-advertise-address $(hostname -i)
```

Debe darnos la siguiente salida:

```
output:

    Your Kubernetes master has initialized successfully!
    
    To start using your cluster, you need to run (as a regular user):
    
        mkdir -p $HOME/.kube
        sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
        sudo chown $(id -u):$(id -g) $HOME/.kube/config
    
    You should now deploy a pod network to the cluster.
    Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
        http://kubernetes.io/docs/admin/addons/
    
    You can now join any number of machines by running the following on each node
    as root:
    
    kubeadm join --token SOMETOKEN SOMEIPADDRESS --discovery-token-ca-cert-hash SOMESHAHASH

```

**Explicacion :**

Indica que el nodo actual (nodo 1) ha sido inicializado exitosamente como un nodo master.

```w
Your Kubernetes master has initialized successfully!
```

Se utiliza para ejecutar comandos con **kubectl** sin necesidad de otorgar privilegios de root.

```bash
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

Nos dicen tambien como podemos desplegar una red de pods.

```bash
kubectl apply -f [podnetwork].yaml
```

Por ultimo nos dan un comando para que otros nodos se unan al cluster como nodos trabajadores.

```bash
kubeadm join --token SOMETOKEN SOMEIPADDRESS --discovery-token-ca-cert-hash SOMESHAHASH
```

Ahora podemos copiar el comando anterior en el segundo terminal (nodo2) para que este nodo
se una al cluster como nodo trabajador.

```bash
kubeadm join --token a146c9.942... 172.26.0.2:6443 --discovery-token-ca-cert-hash sha256:9a4d...
```


```
output:

    Initializing machine ID from random generator.
    [kubeadm] WARNING: kubeadm is in beta, please do not use it for production clusters.
    [preflight] Skipping pre-flight checks
    [discovery] Trying to connect to API Server "172.26.0.2:6443"
    [discovery] Created cluster-info discovery client, requesting info from "https://172.26.0.2:6443"
    [discovery] Requesting info from "https://172.26.0.2:6443" again to validate TLS against the pinned public key
    [discovery] Cluster info signature and contents are valid and TLS certificate validates against pinned roots, will use API Server "172.26.0.2:6443"
    [discovery] Successfully established connection with API Server "172.26.0.2:6443"
    [bootstrap] Detected server version: v1.8.11
    [bootstrap] The server supports the Certificates API (certificates.k8s.io/v1beta1)
    
    Node join complete:
    * Certificate signing request sent to master and response
      received.
    * Kubelet informed of new secure connection details.
    
    Run 'kubectl get nodes' on the master to see this machine join.
    
```
**Explicacion:**


Podemos ver que al final nos recomiendan ejecutar el siguiente comando en el nodo1 para ver
a este nodo unido al cluster como un nodo trabajador:

```bash
kubectl get nodes
```

Una vez tengamos 1 nodo trabajador y 1 nodo master ya estamos casi listos para inicializar la red de nuestro cluster.

```bash
kubectl apply -n kube-system -f \
"https://cloud.weave.works/k8s/net?k8s-version=$(kubectl version | base64 |tr -d '\n')"
```

```
output:

    serviceaccount "weave-net" created
    clusterrole "weave-net" created
    clusterrolebinding "weave-net" created
    role "weave-net" created
    rolebinding "weave-net" created
    daemonset "weave-net" created
```

**Explicacion:**

Como podemos ver se han creado varios recursos en nuestro cluster. Asociados a weave-net.

Weave net nos permite crear una red virtual entre los contenedores en los diferentes nodos que tengamos 
en nuestro cluster de kubernetes. Esto permite la comunicacion entre los contenedores.

### What’s this application?

Ahora veamos detalladamente como podria funcionar Docker coins:

- **rng** : Genera algunos bytes aleatorios.
- **hasher** : Hashea bytes.
- **woker** : Funciona como un controlador.
- **redis** : Almacenamiento de datos que funciona como una cache
- **webui** : Muestra al usuario la velocidad del hasheo.

El worker consulta a rng por una cadena de bytes aleatoria, luego el worker entrega
esta cadena al hasher que la hashea y repite esto indefinidamente.

El numero de veces que se ejecuta el bucle es guardado en redis.

Cada segundo el webui hace una consulta a redis para determinar el numero de bucles que se han hecho
consiguiendo asi la velocidad promedio del hasheo.

### Getting the application source code

En este caso el nodo de la actividad si nos permitio clonar el repositorio

![img_5.png](Imagenes%2Fimg_5.png)

Entramos al directorio dockercoins y listamos ...

Podemos ver en la imagen inferior que existen directorios para cada una de las tareas
mencionadas anteriormente, cada una con su respectivo dockerfile y archivo python.


### Running the application

Ademas podemos ver un archivo docker-compes.yml . Intentamos correr el archivo.

Docker Compose nos ayuda a construir todas las imagenes necesarias 
(descargando las imágenes base correspondientes de cada dockerfile), luego inicia todos los 
contenedores y muestra los logs de todos ellos.

```bash
docker-compose up
```


![img_6.png](Imagenes%2Fimg_6.png)

El error sigue siendo el mismo que al inicio **"No space left on device"**.


![img_7.png](Imagenes%2Fimg_7.png)

Si lo corremos de manera local:

Podemos ver que los logs se combinan en la salida una vez las imagenes han sido creadas y corridas en un contenedor.

![img_11.png](Imagenes%2Fimg_11.png)


**webui**

![img_12.png](Imagenes%2Fimg_12.png)

#### CleanUp

**ctrl+c** para parar los contenedores.

![img_13.png](Imagenes%2Fimg_13.png)

### Kubernetes concepts

- Kubernetes es un sistema de gestión de contenedores.
- Ejecuta y gestiona aplicaciones contenerizadas en un clúster.

Que podemos hacer con kubernetes?

- Kubernetes puede iniciar y gestionar múltiples contenedores que se ejecutan con una imagen específica
- Nos permite colocar balanceadores de carga para que el trafico (numero de solitudes del cliente) sean
distribuidas equitativamnte a cada nodo.
- Podemos agregar mas nodos a nuestro cluster de kubernetes pudiendo correr mas contenedores y manejar la carga
adicional que puede presentarse en dias festivos o cierta temporada del año por ejemplo.
- Si tenemos una nueva version, podemos ir actualizando cada contenedor uno a uno de tal forma que la disponibilidad
del servicio que brindamos a los clientes no se vea afectada.


### Kubernetes architecture

**Master de Kubernetes**

El "master" de Kubernetes coordina todo el cluster y consta de tres principales componentes:

- API Server: Punto de entrada para administrar el cluster.

- Scheduler y Controller Manager: Toman decisiones sobre la ubicación de los pods y gestionan el estado del cluster.
  
- etcd: Almacena de manera segura la configuracion y el estado del cluster.

**Nodos de Kubernetes**

Los nodos ejecutan los contenedores y consisten en:

- Container Engine: Ejecuta los contenedores (por ejemplo, Docker).

- kubelet: Gestiona los pods y asegura la ejecución de los contenedores.

- kube-proxy: Gestiona la red a nivel de nodo.

### Kubernetes resources

La API de kubernetes define como **resources** a muchos conceptos.
Entre estos encontramos:

- node (maquina fisica o virtual en nuestro cluster)
- pod (grupo de contenedores corriendo conjuntamente en un nodo)
- service (endpoint en donde podemos conectarnos con 1 o muchos contenedores)
- namespace (grupo de cosas aisaladas parcialmente)

### Declarative vs imperative in Kubernetes


**Sistema imperativo** : Se hace enfasis en un conjunto de pasos para llegar a un estado deseado, si una
tarea se interrumpe durante la ejecucion, generalmente se necesita reiniciar desde el principio para asegurarse de que se complete correctamente.

**Sistema Declarativo** : Se hace enfasis en que nos falta para llegar al estado deseado. Se describe el estado de los recursos sin especificar los pasos individuales para llegar
al estado deseado.

Ahora supongamos que tenemos nuestra aplicacion corriendo, es necesario que esta aplicacion tenga 6 replicas corriendo 
de una imagen, si alguno de los contenedores que corre una replica falla lo que hace kubernetes por detras es ver que podemos
hacer para tener nuevamente 6 replicas corriendo, en este caso kubernetes tendria que agregar un contenedor mas corriendo esta imagen
en algun nodo (enfoque declarativo), si estuvieramos en un enfoque imperativo entonces 
tendriamos que reiniciar e interrumpir el servicio de los clientes para tener las 6 replicas corriendo nuevamente lo cual
no tiene ningun sentido.


### Kubernetes network model


#### the good

#### the less good
