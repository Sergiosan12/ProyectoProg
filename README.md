# Proyecto Java Keyla y Sergio  

Este proyecto de programación tiene como objetivo desarrollar un programa de calendario menstrual que también incluya funciones relacionadas con el ocio, deporte, embarazo y rutinas normales. 

---

## 5/05/2024

## Estructuración del Proyecto:

Creacion de paquetes organizados segun funciones siguiendo la estructura MVC (Model, Controller, Vista) : 

### MODEL 

1. **Calendario:** Este paquete contendrá las clases y funciones necesarias para gestionar el calendario menstrual.

2. **Fases:** Aquí se implementarán las funciones relacionadas con las diferentes fases del ciclo menstrual, como la menstruación, la ovulación, la fase lútea.
   
3. **Periodo:** Este paquete gestionará la información relacionada con el periodo menstrual de cada usuario, incluyendo la duración del ciclo, la fecha de inicio y la intensidad de los síntomas.

4. **Usuario:** Por último, este paquete se encargará de gestionar los datos de los usuarios, como su información personal, preferencias y configuraciones.

### CONTROLLER 

1. **Funciones:** En este paquete se incluirán diversas funciones en las que podremos relacionar la parte de Modelo y la Vista


### VIEW 

1. **Interfaces:** Aquí se implementarán las interfaces de usuario para interactuar con el programa, tanto en línea de comandos como gráficamente.
   
2. **Informe:** Este paquete contendrá funciones para generar informes y estadísticas sobre el ciclo menstrual y las actividades relacionadas.


---

Este README se actualizará conforme avance el desarrollo del proyecto, proporcionando información adicional sobre las funcionalidades implementadas y cómo utilizarlas.


## 6/05/2024

##  Creación de la interfaz de inicio de sesión

1. Creación del paquete `InicioSesion` dentro de `Interfaz`.

2. A continuación, se crea toda la interfaz en la que se puede inicar sesión o darle al botón de registrarse.

## 8/05/2024

1. Creación de la clase `Informe` con todos los atributos necesarios para generar un informe de los datos del usuario.

2. Creación de la clase `GenerarInforme` con los métodos necesarios para generar un informe en formato txt.

3. Ahora añado una API para poder escribir el informe en formato PDF

4. Modifico lo necesario en el código para hacerlo PDF.

5. Creación de la clase `CuestionarioSwing` para pedir los datos de media del Ciclo y del Sangrado

6. Creación de la clase de `Duración` para utilizar su objeto como paso de referencia entre `Cuestionario` y `CuestionarioSwing`

7. Creación algoritmo de la `FaseFolicular`

8. Creación algoritmo de la `FaseLutea`

9. Creación algoritmo de la `FaseMenstrual`

10. Creación algoritmo `FaseOvulacion`

## 10/05/2024

1. Creación de la interfaz gráfica del inicio de sesión y registro de usuario.

2. Creación de la estructura Singleton para `Duración` y `Cuestionario`.

3. Optimización de la lógica de las clases  `Duración` y `Calendario`.

## 13/05/2024

1. Creación una base de datos llamada Proyecto en la que vamos a trabajar.

2. Realización la conexión a la base de datos en el proyecto para poder trabajar con ella.

3. Creación de las tablas Usuario y Menstruación con sus respectivos campos.

## 15/05/2024

1. Enlace entre interfaces.

2. Implementación de la base de datos en las interfaces 

3. Creación del Selector del `Calendario` con JDatePicker

## 20/05/2024

1. Implementación de los datos de la base de datos en la generación del informe en formato PDF.

2. Creaciónde la clase `Deportes` y generación de un array de deportes con diferentes intensidades asociados a una fase.

## 21/05/2024

1. Implementación de la tabla menstruación en el programa.
 
## 22/05/2024

1. Creación de las tablas deporte, fases y su relación en las bases de datos.

2. Metidos deportes y las fases en la base de datos.

3. Creación del javadoc para todas las clases.

4. Organizado en estructura MVC.

5. Refactorización de códigp de `GenerarInforme` y `SelectorFecha`.

6. Separación de métodos en `InsertDatabase` y `HandleDatabase`.