# API Registro de Vacunas para Empleados

A continuación se detalla la arquitectura de la aplicación


## Proceso de Construcción

Se utilizaron las siguientes herramientas:

 - SpringBoot con apache por default
 - JpaRepository para manejo de operaciones a la BDD.
 - Arquitectura Hexagonal apoyado por DDD dividiendo la aplicación en Domian, Infraestructure y Application.
 - Uso del patrón de comportamiento **Mediator** para delegar la ejecución de los **Commands** desacoplando las capas.
 - Autenticación a través de usuario y contraseña con un endpoint dedicado.
 - **JWT** para autorización a cada endpoint.
 - Seguridad con SpringSecurity reforzada con Roles **ROLE_ADMIN** y **ROLE_USER** 

## Proceso de ejecución
### Prerequisitos

 - Tener instalado PostgreSQL (version 14 recomendada)
 - Crear una base de datos con el nombre  **kruger** (el nombre es opcional, se puede configurar en application.yml)

### Instalación y Ejecución
 1. Clonar el repositorio.
 2. Modificar el archivo `src\main\resources\application.yml` con los parámetros de conexión a la base de datos (opcional):
 `datasource:`
 `url: jdbc:postgresql://localhost:5432/kruger`
 `username: postgres`
 `password: postgres`
 `driverClassName: org.postgresql.Driver`
 
 3. En la raíz del proyecto ejecutar el comando `mvnw spring-boot:run`

## Accesos

El proyecto tiene por defecto un usuario Administrador con las siguientes credenciales
Identification: 1715849731
Password: 1715849731
El endpoint de autenticación es: http://localhost:8001/api/auth/login el cual devolverá un access_token (válido por 3 minutos, configurable desde application.yml) para enviarlo como Authorization de tipo Bearer en el Header para el resto de requests.

Para añadir un empleado se debe usar el endpoint http://localhost:8001/api/admin/employee el cual creará un usuario para el mismo con su identificación como usuario y contraseña. Además al crear el empleado, se debe especificar el conjunto de ROLES que tendrá (ROLE_ADMIN o ROLE_USER).

Cada empleado es reconocido en el sistema con su "identification".

## Documentación API

Una vez ejecutada la aplicación, se encontrará disponible un **Swagger** en la ruta http://localhost:8001/swagger-ui.html#/
