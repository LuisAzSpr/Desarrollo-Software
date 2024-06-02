# Metricas de calidad

### 1. metricas de acoplamiento

1. **Acoplaimiento eferente (Ce):**

    Ce = numero de clases externas utilizadas por la clase actual

- Mayor Ce **=>** Un modulo depende de otros **=>** Complica las pruebas y el mantenimiento.

- Si una clase A utiliza metodos o varialbes de las clases B, C y D entonces **Ce(A) = 3**.

2. **Acoplamiento aferente (Ca):**

    Ca = numero de clases externas que utilizan la clase actual

- Mayor Ca **=>** el modulo es mas importante **=>** su modificacion puede tener amplias repercusiones.

- Si las clases X,Y,Z utilizan la clase A, entonces **CA(A) = 3**.

3. **Factor de acoplamiento**

    CF = Numero de enlaces / Numero maximo de enlaces posibles.

- Menor CF **=>** Los modulos estan menos acoplados **=>** Menor dependencia entre modulos.

- Es una metrica a nivel de sistema que evalua que tan acoplado esta.

**CF = e/(n.(n-1))**

- e : numero de enlaces totales
- n : numero de modulos

Ejemplo : 

- Supongamos un sistema con 5 modulos n = 5 y el numero total de enlaces reales entre estos 5 modulos es 10 (e=10) , entonces :
**CF = 10/(5.4) = 10/20 = 0.5**


#### Ejemplo de un sistema en Java con cuatro módulos

- UsuarioModulo : Depende de **(Usuario, GrupoContactoModulo)**
- GrupoContactoModulo :  Depende de **(Usuario)**.
- ContactoOperacionesModulo : Depende de **(Usuario)**.
- Usuario : Independiente.

Apartir de esto, podemos decir que:

e = 4 **(** UsuarioModulo **->** Usuario **|** UsuarioModulo  **->** GrupoContactoModulo **|** GrupoContactoModulo  **->** Usuario **|** ContactoOperacionesModulo  **->** Usuario **)**

n = 4 **(** UsuarioModulo **|** GrupoContactoModulo **|** ContactoOpracionesModulo **|** Usuario **)**

Por lo que el factor de acoplamiento seria

**CF = 4/(4*3) = 4/12 = 1/3 = 0.33**

El factor acoplamiento es relativamente bajo, por lo que podemos considerar que las clases del sistema estan ligeramente acopladas por lo que deberia funcionar bien.

4. **Inestabilidad (I):**

I = Ce/(Ce+Ca).

Volatilidad potencial de un modulo dentro de la arquitectura de software. Indica de que un modulo se vea afectado por cambios en otros modulos.

- 0 : El modulo no depende de ningun otro modulo.
- 1 : Ningun otro modulo depnede de este modulo.

Ejemplo:

Supongamos una clase A con:
- Ce (acoplamiento eferente): 3 (usa clases B,C y D).
- Ca (acoplamiento aferente): 2 (usada por clases X,Y).

**I = 3/(3+2)=3/5=0.6**

### 2. metricas de Cohesion

1. Lack of Cohesion Methods (LCOM)

    **LCOM = |P| - |Q|**

- P : numero de pares de metodos que no comparten atributos de instancia.

- Q : numero de pares de metodos que si comparten atributos de instancia.






