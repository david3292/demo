//******************************************************************//
Frameworks y dependencias:
    * Spring boot v2.1.6.RELEASE
    * Spring web
    * Hibernate
    * jQuery
    * Semantc UI
//******************************************************************//
El presente proyecto usa una base de datos MYSql
nombre de la base de datos "demo"

Para cambiar las configuracones de conexión a la base de 
datos abrir el archivo "application.properties" que se 
encuentra en la carpeta "resources".

modificar las siguientes propiedades para la conexión
    spring.datasource.username=USUARIO_BASE_DATOS
    spring.datasource.password=PASSWORD_BASE_DATOS
=========================================================
|                                                       |
| SI SE DESEA EJECUTAR CON OTRO MOTOR DE BASE DE datos  |
| SE DEBE CAMBIAR EL "DIALECTO DE HIBERNATE" Y AGREGAR  |
| LA DEPENDENCIA DEL CORRESPONDIENTE .JAR PARA LA       |
| CONEXIÓN JDBC.                                        |
|                                                       |
=========================================================

//******************************************************************//
Se recomienda abrir el proyecto con el programa SpringTool siguientes
* Primero de debe crear la base de datos "demo" 
* Extraer la cerpeta "demo" del comprimido en .zip
* Abrir la aplicación con SpringTool Suite
* Una vez abierto la aplicacion, se deben descargar las dependencias del repositorio maven
* Una vez descargada las dependencias, click derecho sobre el proyecto / Run As / Spring Boot App
* Cuando el programa inicie, creará las tablas en base al mapeo de clases realizado

En el navegador acceder a la url http://localhost:8085/