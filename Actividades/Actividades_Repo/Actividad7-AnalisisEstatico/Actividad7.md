# Actividad 7
En esta actividad veremos le uso de sonarqube para el analisis estatico del codigo.

Para empezar veremos el reporte que tenemos de sonarqube y arreglaremos los que tienen un estado **Critical o Major**.

La idea en general es reducir nuestros **Code Smells**, para tener un codigo mas limpio.

--------------------

### Reporte Sonarqube

![alt text](image-6.png)

![alt text](image-1.png)
![alt text](image-2.png)
![alt text](image-3.png)
![alt text](image-4.png)

---------------------------

#### 1. Primer olor de codigo
![alt text](image-5.png)
![alt text](image-7.png)

#### Segundo olor de codigo
![alt text](image-9.png)
![alt text](image-8.png)

La solucion que nos brinda es la siguiente

![alt text](image-11.png)

Podemos ver que la solucion que nos brinda es eliminar la clase enumerable y cambiar cada uno por una variable, pero podemos considerar que esta clase brindan una mejor forma de referirse a los modos como 

- Mode.Cooking
- Mode.Suspended
- Mode.Setup

Por lo que para este caso considero que podrian ignorarse estos olores de codigo.
    
#### Tercer olor de codigo

