# 🎮 Proyecto Stimy - Arquitectura de Microservicios

Bienvenido al repositorio oficial del proyecto **Stimy**. Esta aplicación está construida bajo una arquitectura de microservicios utilizando **Spring Boot** y **Spring Cloud Netflix Eureka** para el descubrimiento de servicios.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.x
* **Descubrimiento de Servicios:** Spring Cloud Netflix Eureka
* **Base de Datos:** MySQL
* **Gestor de Dependencias:** Maven
* **Otras herramientas:** Spring Data JPA, OpenFeign, Lombok.

---

## 📁 Estructura del Proyecto

El repositorio está dividido en los siguientes módulos principales:

* **`eureka-server/`**: Actúa como el servidor principal de registro y descubrimiento. Todos los microservicios se conectan a él para hacerse visibles en la red. Corre en el puerto `8761`.
* **`gamehub-service/`** (o tu carpeta de servicio principal): Microservicio encargado de la lógica de negocio, controladores de amigos y conexión directa con la base de datos MySQL. Corre como cliente de Eureka.

---

## 🚀 Instrucciones de Ejecución

Para correr este proyecto en un entorno local, sigue estos pasos en orden:

### 1. Pre-requisitos
* Tener instalado **Java 21** y **Maven**.
* Tener un servidor **MySQL** corriendo localmente (XAMPP, Workbench o Docker).
* Asegurarte de que las credenciales de la base de datos coincidan con las de tu archivo `application.properties` del servicio.

### 2. Levantar el Servidor Eureka
Es obligatorio encender el servidor antes que los microservicios.
1. Abre la carpeta `eureka-server` en tu IDE (IntelliJ, Eclipse, etc.).
2. Ejecuta la clase `EurekaServerApplication`.
3. Verifica que esté funcionando ingresando a: `http://localhost:8761`.

### 3. Levantar los Microservicios (Clientes)
1. Abre el proyecto principal de tu servicio (ej. `gamehub-service`).
2. Ejecuta la clase principal de Spring Boot.
3. Actualiza el panel de Eureka (`http://localhost:8761`) y verifica que el servicio aparezca registrado en la lista de instancias.

---

## 👥 Autores y Contribuciones

* Proyecto desarrollado por Johans Sepulveda - Javier Valtierra - Francisco Bustos
* Mediante IntelliJ IDEA con Spring boot y Github
