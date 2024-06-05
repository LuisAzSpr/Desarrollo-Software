
# Actividad Refactorizacion

**Ejercicio 1:** Refactorización para mejorar la cohesión y reducir el acoplamiento. Sea la siguiente aplicación Java con clases excesivamente acopladas y con baja cohesión. Debes identificar problemas específicos de cohesión y acoplamiento y refactorizar el código para mejorar estas métricas. Deberán proporcionar un breve informe explicando qué cambios se realizaron y por qué.

**EmployeManager**


![alt text](Imagenes/image.png)

**Completando el codigo**

**EmployeManager**

A diferencia del codigo inicial, en este caso estamos creando una clase para los departamentos (Department) y para los empleados (Employe) en lugar de representarlos unicamente con strings, esto nos ayudara a interpretar mejor la cohesion y el acoplamiento.

En este caso aqui es donde se relacionan los empleados con sus respectivos departamentos, se puede agregar, eliminar o cambiar de departamento a un empleado, por lo que esta clase necesita conocer a todos los departamentos existentes, es por esto que se crea una lista de departamentos.

Ademas la responsabilidad de imprimir los departamentos mediante el metodo printAllDepartments() fue delegado a otra clase, esto lo hicimos para que la clase actual tenga una unica responsabilidad

![alt text](Imagenes/image-1.png)
![alt text](Imagenes/image-2.png)

**Employe**

La clase Employe tiene un atributo nombre que identifica al empleado

![alt text](Imagenes/image-3.png)

**Department**
La clase Department necesita tener un nombre de departamento y necesita tambien concer a sus empleados, es decir, tener una lista de Employe con todos los empleados que pertenecen a este departamento, ademas debe ser responsable de administrar los empleados, es decir, agregar un empleado, remover un empleado o verificar si un empleado se encuentra o no.

Cabe mencionar que si usamos una metrica como LCOM, este puede darnos un valor de 2, haciendo referencia a que la clase tiene una baja cohesion, es decir, de que existen un grupo de metodos que usan un atributo (nameDepartment) y otro grupo de metodos que utiliza otro atributo (employeList), sin embargo cabe resaltar que nameDepartment es un atributo que identifica a la clase mas no se hacen operaciones con este, por lo que podemos pasar por alto esto.

![alt text](Imagenes/image-4.png


**Problemas**

- La clase realiza 2 tareas a la vez, la de gestionar al empleado con su departamento y la de imprimir los deparamentos, hacer reportes. Esto no es correcto porque reduce la cohesion de una clase, ya que los metodos de esta clase realizan 2 tareas a la vez.

- En cuanto a la acoplacion de clases, podemos ver que no existe una dependencia innecesaria entre las clases ademas el acoplamiento entre estas es pequeño, ya que:

    1. La clase Employe no depende de ninguna clase
    2. La clase Department solo depende de la clase Employe (Cada departamento tiene la tarea de conecer a sus empleados).
    3. La clase EmployeManager depende de las clases Employe y Department, las cuales son necesarias para su correcta implementacion y funcionamiento.


**Soluciones**

1. Separar la clase imprimir reportes de la de gestion del empleado y su departamento.

![alt text](Imagenes/image-5.png)

2. Podemos ver que podemos refactorizar un poco este codigo, ya que consideramos que el departamento al conocer los empleados, debe ser este el que verifique si un empleado se encuentra o no y en caso se encuentre eliminarlo de la lista en el metodo removeEmploye()

![alt text](Imagenes/image-6.png)


Aqui podemos ver la clase Department

![alt text](Imagenes/image-7.png)

**Ejercicio3:** Imaginemos que tenemos una clase en un sistema de gestión de contenido que maneja tanto la lógica de usuario como la lógica de la base de datos para artículos de blog.

![alt text](Imagenes/image-8.png)


Hemos agregado los metodos añadir articulo y agregar articulo

![alt text](Imagenes/image-11.png)

![alt text](Imagenes/image-12.png)

**Este codigo viola el principio de responsabilidad unica (SRP).**

La clase BlogManager tiene muchas tareas:

- **Gestion de articulos:** Añade y elimina articulos.
- **Gestion de articulos en la base de datos :** Si cambiamos de base de datos o la conexion a esta tendremos que cambiar el codigo de esta clase, o agregar nuevos metodos en los que se apoye saveArticleToDatabase, haciendo que la clase se haga mas grande.
- **Imprimir los articulos:** Si queremos cambiar el reporte que hacemos de los articulos como la cantidad de visitas, fechas, etc. entonces tambien tendremos que cambiar este metodo y posiblemente añadir nuevos metodos en la que printAllArticles se apoye.

Teniendo en cuenta estos problemas, podemos separar las clases en 3:

- La primera se encargara de gestionar los articulos : 

![alt text](Imagenes/image-15.png)

- La segunda sera nuestra conexion con la base de datos:

![alt text](Imagenes/image-13.png)

- La tercera sera el informe de los reportes:

![alt text](Imagenes/image-14.png)

Ahora podemos ver que la clase BlogManager depende de implementaciones, en este caso de DBConnection, esto viola el principio solid de inversion de dependencias que nos dice **Las clases no deben de depender de detalles si no de abstracciones**

**Interfaz**
![alt text](Imagenes/image-16.png)


**Implementaciones**
![alt text](Imagenes/image-17.png)
![alt text](Imagenes/image-18.png)

