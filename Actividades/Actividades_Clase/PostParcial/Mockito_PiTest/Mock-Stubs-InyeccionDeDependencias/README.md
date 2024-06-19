# Actividad Mocks-Stubs-InyecciondeDependencia 

**Inyección de dependencias (DI)**
- Patron de diseño.
- Permite inyectar las dependencias de un objeto en lugar de crearlas por si mismo.
- Desacopla la creacion de dependencias de la logica de negocio del objeto.
- Facilita las pruebas y el mantenimiento del codigo.


**Inversión de Control (IoC)**

- Principio de diseño
- El flujo de control se invierte 
- En lugar de que el objeto controle su comportamiento y dependencias, el compartimiento se gestiona externamente

**Ventajas de DI y IOC**

1. **Desacoplamiento** : DI reduce el acoplamiento entre objetos, lo que facilita el cambio de 
implementaciones sin modificar las clases que dependen de ellas.
2. **Facilidad de prueba** : Al inyectar dependencias es facil reemplazarlas por mocks o stubs durante
las pruebas unitarias, lo que mejora la cobertura y calidad de las pruebas.
3. **Mantenibilidad** : Los sistemas diseñados por DI o IoC son mas faciles de mantener, ya que
las dependencias estan claramente definidas y gestionadas externamente.
4. **Flexibilidad** : Permite cambiar las implementaciones de las dependencias en tiempo de ejecucion 
o configuracion sin cambiar el codigo fuente, proporcionando mayor flexibilida y adaptabilidad.


### ACTIVIDAD ASTRONAUTAS

Supongamos que estamos probando un fragmento de código que extrae una lista de países de una
API externa y devuelve los que comienzan con una letra en particular. Si no controla lo que devuelve
la API, ¿cómo puede estar seguro de que el código de tus pruebas tendrá sentido?

```


```