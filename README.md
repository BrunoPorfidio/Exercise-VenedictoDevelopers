---

## Índice

1. **[Introducción](#introducción)**
   - [Bienvenida](#bienvenida) 
   - [¿Qué es un microservicio?](#qué-es-un-microservicio)
   - [Propósito del Microservicio](#propósito-del-microservicio)

2. **[Requisitos Previos](#requisitos-previos)**
   - [Software necesario](#software-necesario)
   - [Instalación de herramientas](#instalación-de-herramientas)

3. **[Configuración del Microservicio](#configuración-del-microservicio)**
   - [Clonar el Repositorio](#clonar-el-repositorio)
   - [Configuración del Proyecto](#configuración-del-proyecto)
   - [Dependencias necesarias](#dependencias-necesarias)

4. **[Base de Datos](#base-de-datos)**
   - [Configuración de la base de datos](#configuración-de-la-base-de-datos)
   - [Importar base de datos desde el archivo DB](#importar-base-de-datos-desde-el-archivo-db)

5. **[Ejecución del Microservicio](#ejecución-del-microservicio)**
   - [Ejecutar el microservicio](#ejecutar-el-microservicio)
   - [Probar los Endpoints](#probar-los-endpoints)

6. **[Descripción de los Endpoints](#descripción-de-los-endpoints)**
   - [/auth/signup](#authsignup)
   - [/auth/login](#authlogin)
   - [/auth/users](#authusers)
     (Extra)

7. **[Tests](#tests)**
   - [Ejecucion de Tests](#ejecucion-de-tests)

---


# Introducción

En el mundo del desarrollo de software moderno, los **microservicios** han emergido como una arquitectura clave para construir aplicaciones escalables, flexibles y fácilmente mantenibles. Un **microservicio** es una pequeña aplicación independiente que se enfoca en realizar una tarea específica dentro de un sistema más grande. A diferencia de las aplicaciones monolíticas tradicionales, los microservicios permiten dividir las funcionalidades en unidades más manejables, lo que facilita su desarrollo, despliegue y escalabilidad.

# Bienvenid@!

¡Bienvenidos a la documentación oficial del microservicio de **Autenticación de Usuarios** para **Venedicto Developers**!  
Gracias por la oportunidad de presentar este proyecto. Estoy emocionado de compartir con ustedes este microservicio desarrollado en  **Java** <img src="https://www.vectorlogo.zone/logos/java/java-icon.svg"  alt="Java Icon"  height="30"  width="30"> con el framework **Spring Boot** <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg"  alt="Spring Boot Icon"  height="30"  width="30">  , utilizando  **JWT** <img src="https://img.icons8.com/?size=256&id=rHpveptSuwDz&format=png"  alt="JWT Icon"  height="30"  width="30"> para la autenticación y **MySQL** <img src="https://www.vectorlogo.zone/logos/mysql/mysql-icon.svg"  alt="JWT Icon"  height="30"  width="30"> como base de datos.

## ¿Qué es un Microservicio?

Un microservicio es una arquitectura de software que divide grandes aplicaciones en componentes más pequeños y desacoplados que pueden ser implementados, mantenidos y escalados de forma independiente. Cada componente o "servicio" se encarga de una funcionalidad específica. Son útiles porque permiten:
- **Escalabilidad**: Los servicios pueden escalarse de manera independiente según la demanda.
- **Mantenibilidad**: Es más fácil de entender, probar y mantener.
- **Despliegue independiente**: Se pueden desplegar sin afectar a otros servicios.

## Propósito del Microservicio

El propósito de este microservicio es gestionar la **creación** y **consulta** de usuarios, asegurando que los usuarios estén autenticados de forma segura utilizando **JWT (JSON Web Token)**. Al crear un usuario, se almacena un **UUID** como identificador único, su fecha de **creación**, la fecha de su **última sesión**, su estado de **actividad**, **nombre**, **correo electrónico**, **contraseña** encriptada, y una entidad relacionada que guarda su **teléfono** (número, código de ciudad, y código de país).


# Requisitos Previos

Antes de ejecutar el microservicio, asegúrate de tener instalados los siguientes programas:

- **Java JDK 17 o superior**
- **Spring Boot 3.0 o superior**
- **Maven 3.6+**  
- **MySQL 8.0 o superior**  
- **NetBeans**, **IntelliJ IDEA**, o cualquier otro **IDE** compatible con **Java Spring Boot**.

---

# Configuración del Microservicio

## 1. Clonar el Repositorio

Para ejecutar este microservicio, primero debes clonar el repositorio o descargar el archivo **ZIP**. Para clonar el repositorio, ejecuta el siguiente comando en tu terminal:

```bash
https://github.com/BrunoPorfidio/Exercise-VenedictoDevelopers.git
```

Si prefieres descargar el **ZIP**, puedes extraerlo en cualquier directorio de tu elección.

## 2. Abrir el Proyecto en tu IDE

Una vez clonado o descargado el repositorio, abre tu IDE favorito. Si utilizas NetBeans, sigue estos pasos:

* File → Open Project → Selecciona la carpeta del proyecto.
  
Espera a que el IDE descargue todas las dependencias y construya el proyecto.

## 3. Dependencias Necesarias
Antes de proceder, verifica que todas las dependencias en el archivo **pom.xml** estén correctamente instaladas. A continuación te dejo todas las dependencias necesarias:

```xml
<dependencies>
    <!-- Dependencia para Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Dependencia para Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Dependencia para Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Dependencia para JWT -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
    </dependency>

    <!-- Conector MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Dependencia de Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>

    <!-- Dependencias de Pruebas -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</artifactId>
    </dependency>
</dependencies>
```

# Base de Datos
Para almacenar los datos del microservicio, debes configurar una base de datos MySQL. Puedes hacer esto de dos maneras:

## 1. Configurar la Base de Datos en el archivo application.properties
Dentro de la carpeta del proyecto, abre el archivo **src/main/resources/application.properties** y añade tus credenciales de MySQL. Con la primera ejecucion del Microservicio, se crearan la migracion a la base de datos, y estara listo para usarse.
Este es un ejemplo de cómo deberías configurarlo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/venedictoDB
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

##2. Importar la Base de Datos desde un Archivo
También puedes importar la base de datos desde el archivo SQL que se encuentra en la carpeta DB del proyecto. Aquí te muestro cómo puedes navegar hasta dicha carpeta:

```
📁 Venedicto Microservicio
├── 📁 src
├── 📁 target
├── 📁 DB
|    └──> venedictodevelopersdb.sql
├── 📁 pom.xml
└── ...
```

Para importar el archivo venedictoDB.sql en MySQL, usa el siguiente comando:

```bash
mysql -u root -p venedictoDB < /DB/venedictoDB.sql
```

# Ejecución del Microservicio

Una vez configurada la base de datos y revisadas las dependencias, puedes ejecutar el microservicio utilizando el comando:

```bash
mvn spring-boot:run
```

# Descripción de los Endpoints

## 1. /auth/signup - Registro de Usuarios

Método: POST

Descripción: Crea un nuevo usuario y genera un token JWT.

Cuerpo de la Solicitud (JSON):

```json
{
    "name": "string",
    "email": "string",
    "password": "string",
    "phones": [
        {
            "number": "long",
            "cityCode": "int",
            "countryCode": "string"
        }
    ]
}
```

* Respuesta Exitosa (201 - Created):
  
```json
{
    "id": "UUID",
    "created": "2024-09-26T12:00:00",
    "lastLogin": "2024-09-26T12:05:00",
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "isActive": true
}
```

## 2. /auth/login - Inicio de Sesión

Método: POST

Descripción: Autentica al usuario y devuelve un nuevo token JWT.

Cuerpo de la Solicitud (JSON):

```json
{
    bearer token: token
}
```

* Respuesta Exitosa (200 - OK):

```json

    {
      "id": "UUID",
      "created": "DataTime",
      "lastLogin": "DataTime",
      "name": "string",
      "email": "string",
      "password": "string",
      "phones": [
            {
              "number": "long",
              "cityCode": "int",
              "countryCode": "string"
            }
              ]
    }   
```

## 3. /auth/user - Llamar todos Usuarios

Método: POST

Descripción: Realiza una llamda genereal a todos los Usuasios creados.

Cuerpo de la Solicitud (JSON):

```URL
{
    localhost:8080/auth/users
}
```

* Respuesta Exitosa (200 - OK):


```json
{
    {
      "id": "UUID",
      "created": "DataTime",
      "lastLogin": "DataTime",
      "name": "string",
      "email": "string",
      "password": "string",
      "phones": [
            {
              "number": "long",
              "cityCode": "int",
              "countryCode": "string"
            }
              ]
    }
    {
      "id": "UUID",
      "created": "DataTime",
      "lastLogin": "DataTime",
      "name": "string",
      "email": "string",
      "password": "string",
      "phones": [
          {
          "number": "long",
          "cityCode": "int",
          "countryCode": "string"
          }
              ]
    }
}
```

# 7. Tests

## Ejecucion de Tests

* Para realizar los tests de este Microservicio les adjuntare la ubicacion del archivo JSON para ser importado a su Postman:

```
📁 Venedicto Microservicio
├── 📁 src
├── 📁 target
├── 📁 DB
├── 📁 PostmanTests
|    └──> VenedictoDevelopersTest.postman_collection.json
├── 📁 pom.xml
└── ...
```
una vez en Postman, dirigase a la siguiente ruta:

* File → Import → Seleccione el archivo Json.
  
# Les dejo unas imagenes de como bederian tener sus Postman:

* /auth/sign-up
<img src="https://i.postimg.cc/jSfL0QVc/image-2024-09-27-060837346.png" alt="Auth/sign-up Image" width="600">

---

* /auth/login 
<img src="https://i.postimg.cc/mrm6y9d3/image-2024-09-27-054824128.png" alt="Auth/login Image"  width="700">

---

* /auth/user
<img src="https://i.postimg.cc/tTFvQ6rx/image-2024-09-27-054622335.png" alt="Auth/user Image" width="700">
