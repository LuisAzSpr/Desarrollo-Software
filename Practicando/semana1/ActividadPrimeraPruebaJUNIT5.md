# Curso de desarrollo de software

## Primera Prueba con JUnit 5

- Azaña Vega, Luis Angel

### Solucion al cuestionario

#### Solución 1:
La clase Test usa directamente el SUT (la clase que estamos testeando), el SUT u
sa a su vez a los DOCs(clases necesarias para que el SUT cumpla con las funciona
lidades que se estan testeando), podemos decir que si bien el test se realiza di
rectamente sobre una clase, esta clase puede depender de otras, por lo que el te
st tambien depende de esas otras clases indirectamente.
#### Solución 2:
- SUT : Si se requieren testear otras funcionalidades de FinancialService, esta 
clase seria el SUT, si solo nos queremos centrar en calcular el bonus, entonces 
el metodo calculateBouns seria el SUT.

- DOC : Podemos ver que para que el metodo calculateBonus funcione correctamente
 tambien depende de que el metodo getClientType, calculateBonus de la clase calc
ulator y saveBonusHistory funcionen correctamente, por lo que podemos decir que 
los DOCs depende del nivel de granularidad que hayamos tomado en el SUT, si el S
UT es toda la clase, entonces los DOCs serian clientDAO y calculator, si el SUT 
Es el metodo entonces los DOCs serian los metodos mencionados anteriormente.

#### Solución 3:

La comunicacion indirecta entre los workers y el cliente mediante el manager pue
de no ser la mejor forma de comunicacion, ya que puede haber fugas o errores en 
la captura de requisitos, la cual es una parte fundamental en el resultado del p
roducto o servicio entregado.

#### Solución 3: Actividad1-Testing 


#### Solución 5:

- Pruebas unitarias: Se centran en probar unidades individuales de codigo, como 
funciones metodos o clases. El objetivo de 
esta prueba es de que cada unidad de codigo funciones correctamente de manera ai
slada.

- Pruebas de integracion: Se centran en probar como las unidades individuales de
 codigo se combinan y funcionan juntas. El objetivo de esta prueba es en verific
ar la interaccion entre diferentes componentes del sistema.

- Prueba de aceptacion: Se centran en verificar si el sistema cumplen con el req
uisito del cliente.
