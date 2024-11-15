# PROYECTO SERVIDOR: PORTAFOLIO



**Denominación del Ciclo**: Desarrollo de Aplicaciones Web (DAW).- 2º Curso
**Alumno**: [Diana Mª Pascual García]



## ÍNDICE


1. [INTRODUCCION](#introduccion)
2. [FUNCIONALIDADES DEL PROYECTO Y TECNOLOGÍAS UTILIZADAS](#funcionalidades-del-proyecto-y-tecnologías-utilizadas)
3. [GUÍA DE INSTALACIÓN](#guía-de-instalación)
4. [GUíA DE USO](#guía-de-uso)
5. [ENLACE A FIGMA](#enlace-a-figma)
6. [CONCLUSIÓN](#conclusión)
7. [CONTRIBUCIONES, AGRADECIMIENTOS Y REFERENCIAS](#contribuciones-agradecimientos-y-referencias)
8. [LICENCIAS](#licencias)
9. [CONTACTO](#contacto)

---



## INTRODUCCION



### Descripción del Proyecto

El proyecto **Portafolio** es una API REST que permite a un desarrollador/a gestionar su portafolio de proyectos personales. El objetivo principal es ayudar a mostrar su trayectoria profesional, las tecnologías que maneja, y los proyectos en los que ha trabajado. Esta herramienta facilita a los empleadores ver de forma rápida y organizada los logros de un posible candidato/a, lo que puede ayudar en el proceso de búsqueda de empleo.



### Justificación  

Este proyecto tiene como objetivo aplicar los conocimientos adquiridos en el ciclo de **Desarrollo de Aplicaciones Web (DAW)**, integrando conceptos de backend (Spring Boot, JPA), bases de datos, y diseño de API RESTful, así como implementar validaciones e incorporar algunas pruebas para asegurar la calidad del código.

Del mismo modo, tiene una motivación práctica. En el contexto actual, tener un portafolio online es crucial para los desarrolladores, ya que muestra de manera tangible sus habilidades y experiencia. Este proyecto facilita la transmisión de toda esa información que puede resultar de interés para empleadores, pero que no siempre puede transmitirse a través de un simple curriculum vitae o en una primera entrevista de trabajo.



### Objetivos

- Crear una plataforma que permita gestionar los proyectos de forma eficiente.
- Aplicar conceptos de backend (Spring Boot, JPA), bases de datos, y diseño de API RESTful.
- Integrar cada uno de los endpoints para la gestión de proyectos, desarrolladores y tecnologías sugeridos en el proyecto.
- Implementar validaciones de datos, también personalizadas.
- Documentar la API mediante Swagger para facilitar su uso.
- Aplicar técnicas de paginación para la gestión de datos.
- Incorporar algunas pruebas unitarias para asegurar la calidad del código en relacion a una validación personalizada.



### Motivación  

La motivación de este proyecto surge de la necesidad de tener una herramienta que facilite la presentación de proyectos personales y logros, con la posibilidad de ser usada como carta de presentación ante empleadores.

---



## FUNCIONALIDADES DEL PROYECTO Y TECNOLOGÍAS UTILIZADAS 




### Funcionalidades

- **Gestión de Proyectos**: Permite crear, leer, actualizar y eliminar proyectos que forman parte del portafolio. También se puede buscar proyectos por palabra clave en su nombre.
- **Gestión de Tecnologías**: Administra las tecnologías que se han utilizado en cada proyecto, permitiendo agregar, eliminar tecnologías y buscar proyectos por tecnología.
- **Paginación**: Facilita la visualización de proyectos, especialmente cuando su número crece, mostrando los resultados por páginas.En las peticiones para obtener proyectos, se puede especificar el número de resultados y el número de la página que se desea ver.
- **Validaciones**: Validaciones respetando las reglas de negocio y la base de datos de partida. Implementación de validaciones personalizadas para campos como fechas de proyectos y URLs, asegurando que la información ingresada sea válida.
- **Swagger API**: La plataforma incluye una documentación interactiva de la API a través de Swagger, permitiendo probar los endpoints directamente desde el navegador, definiendo cada uno de los mismos, sus parámetros y respuestas. También se detallan cada uno de los DTOs empleados.
- **Pruebas**: Se ha implementado una clase de test de pruebas unitarias con JUNIT 5, para asegurar la calidad del código de una de las clases de validación personalizadas (URLValidator).



### Tecnologías Utilizadas:

- **Spring Boot**: Framework principal para el desarrollo de la aplicación.
- **Spring Data JPA**: Para la gestión de la base de datos y las entidades.
- **Hibernate Validator**: Para la validación de los datos a través de anotaciones personalizadas.
- **Swagger/OpenAPI**: Para generar y documentar la API RESTful.
- **JUnit**: Para la ejecución de pruebas unitarias y de integración.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **Java**: Lenguaje de programación utilizado para el desarrollo de la aplicación.
- **MySQL**: Base de datos utilizada para almacenar los datos.
- **Git**: Gestor de control de versiones.
- **GitHub**: Plataforma de alojamiento de repositorios.
- **Visual Studio Code**: Editor de código utilizado para el desarrollo.
- **Thunder Client**: Herramienta de prueba de API RESTful.

---



## GUíA DE INSTALACIÓN



### Requisitos Previos

- **JDK 17** o superior.
- **Maven** para gestionar las dependencias y la construcción del proyecto.
- **MySQL** instalado y funcionando.



### Pasos para instalar

**1.** Clona el repositorio:

   ```bash
   git clone https://github.com/DiMaPaGa/servidorporfolio.git
   ```

**2.** Navega al directorio del proyecto:

   ```bash
   cd servidorporfolio
   ```

**3.** Instala las dependencias y compila el proyecto con Maven :
 **ATENCION**: Asegurate de tener Maven instalado en tu sistema.
   ```bash
   mvn clean install
   ```
**4.** Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

---



## GUÍA DE USO



### Ejecución del Script de Base de Datos

Una vez que la aplicación está lista para ejecutarse, se proporciona un archivo **schema.sql** ubicado en la carpeta **src/main/resources** del proyecto. Este archivo contiene las instrucciones necesarias para crear las tablas de la base de datos y poblarlas con los datos de prueba.

**1.** Abrir MySQL Workbench:

    Asegúrate de haber configurado una conexión a tu servidor de base de datos MySQL.

**2.** Cargar el script **schema.sql**:

    Abre MySQL Workbench y selecciona la opción **File > Open SQL Script**.
    
    Carga el archivo schema.sql.

**3.** Ejecutar el script:

    Una vez que el archivo esté cargado en el editor, selecciona toda su contenido o simplemente haz clic en el botón Run (ícono del rayo) para ejecutarlo. Esto creará automáticamente las tablas necesarias y las poblará con datos de prueba.

**4.** Verificar la base de datos:

    Ve a la pestaña Schemas en MySQL Workbench y selecciona la base de datos donde ejecutaste el script.
    
    Navega por las tablas para verificar que se crearon correctamente y que los datos de prueba están disponibles.



### Acceso a la Documentación de la API

Con la aplicación esté en ejecución, se puede acceder a la API documentada mediante Swagger. Abre el navegador y accede a la documentación interactiva en:

```bash
http://localhost:8080/swagger-ui.html
```

Aquí puedes explorar y probar los endpoints de la API, así como ver los DTOs y sus respuestas. Aunque la documentación se encuentra en inglés, la información proporcionada es clara y comprensible.



### Endpoints realizados



#### Proyectos

    1. PUT /api/v1/projects/{id}: Actualizar un proyecto por su ID.
    2. DELETE /api/v1/projects/{id}: Eliminar un proyecto por su ID.
    3. GET /api/v1/projects: Obtener todos los proyectos.
    4. POST /api/v1/projects: Crear un nuevo proyecto.
    5. POST /api/v1/projects/{projectId}/technologies/{techId}: Asociar una tecnología a un proyecto.
    6. PATCH /api/v1/projects/{id}/totesting: Cambiar el estado de un proyecto a "toTesting".
    7. PATCH /api/v1/projects/{id}/toprod: Cambiar el estado de un proyecto a "In production".
    8. GET /api/v1/projects/{word}: Obtener un proyecto por la palabra clave en su nombre.
    9. GET /api/v1/projects/tec/{tech}: Obtener proyectos por tecnología.




#### Tecnologías

    1. POST /api/v1/technologies: Crear una nueva tecnología.
    2. POST /api/v1/technologies/used: Asociar una tecnología a un proyecto.
    3. DELETE /api/v1/technologies/{techId}: Eliminar una tecnología por su ID.




#### Desarrolladores

    1. POST /api/v1/developers: Crear un nuevo desarrollador.
    2. POST /api/v1/developers/worked: Asociar un proyecto a un desarrollador.
    3. DELETE /api/v1/developers/{devId}: Eliminar un desarrollador por su ID.



## ENLACE A FIGMA

***NOTA:*** Este proyecto no tiene una interfaz visual creada en Figma. Sin embargo, como se ha indicado, puede acceder a la interfaz de desarrollo de la API en Swagger para explorar los endpoints y sus respuestas.



## CONCLUSIÓN

El proyecto proporciona a los desarrolladores una plataforma eficiente para gestionar su portafolio de proyectos y tecnologías. A través de esta herramienta, cualquier desarrollador pueden organizar y presentar de manera clara y profesional sus logros, lo que facilita su búsqueda de empleo y mejora su visibilidad frente a posibles empleadores.

Incluso esta idea de proyecto podría extenderse a ser el portafolio de proyectos de una empresa de desarrollo de software. Este sistema permite almacenar, consultar, actualizar y eliminar información relacionada con proyectos, desarrolladores y tecnologías utilizadas. Se podrían ampliar la funcionalidad con los endpoints que puedan resultar necesarios, trabajar en la seguridad de los datos, mejorar la calidad del código, su despliegue en la nube con una interfaz de usuario amigable, etc. 

Esta iniciativa puede emplearse como un punto de partida para crear una plataforma completa de gestión. En este caso, quizás apostaría por el uso de WebClient en el caso de que estemos tratando con más volumen de datos, bases de datos externas e incluso con microservicios (para proyectos, usuarios o tecnologías).



## CONTRIBUCIONES, AGRADECIMIENTOS Y REFERENCIAS

### Contribuciones:

Este proyecto fue desarrollado de manera individual, aunque cuenta con la influencia y el aprendizaje obtenido durante las clases y actividades a lo largo del curso.
    
Me lanzo ya a ir incorporando **mappers** en mis tareas y proyectos gracias a las explicaciones de **Moisés Pastrana**. Es programador además de compañero de vida, y aunque reconozco que no soy fácil a la hora de dejarme orientar, este descubrimiento me ha resultado de gran ayuda en esta tarea.

### Agradecimientos:

Agradezco a mi profesor **Joaquín Borrego** la oportunidad de presentarnos este reto, su paciencia para orientarnos y su comprensión.
A **Jorge Juan**, por creer en mi capacidad cuando me fallaron las fuerzas a finales del curso pasado.
A **Ramón Galdón**, que siempre dejaba sus mensajes de ánimo en sus tareas y boletines de notas, tanto... que me acabé creyendo que valía para esto.
Y de este sector,me reservo algunos agradecimientos para el siguiente proyecto.

A mis compañeros de clase, **Alejandro Navarro** porque siempre se presta a ser mi compañero de fatigas y parece que aún no se arrepiente.
A **Laura Padilla**, que siempre escucha mis explicaciones aunque me enrolle y si me nota agobiada, le falta tiempo para ofrecerme ayuda.
A **Luna García-Arcicóllar**, que sabe cómo subir los ánimos hasta en los momentos más difíciles.


### Referencias:

https://programandoenjava.com/junit-5-pruebas-unitarias/

https://www.baeldung.com/junit-5

https://bell-sw.com/blog/documenting-rest-api-with-swagger-in-spring-boot-3/#mcetoc_1heq9ft3o1j

https://www.bezkoder.com/spring-boot-restcontrolleradvice/#google_vignette

https://medium.com/@himani.prasad016/validations-in-spring-boot-e9948aa6286b




## LICENCIAS

This project is licensed under the **GNU General Public License (GPL) v2** - see the [LICENSE](LICENSE) file for details.




## CONTACTO

Desarrollado por: Diana Mª Pascual García
Correo: [dianamariapascual@gmail.com[](mailto:dianamariapascual@gmail.com)]
Linkedin: https://www.linkedin.com/in/diana-pascual-garc%C3%ADa-47209431/
GitHub: https://github.com/DiMaPaGa
