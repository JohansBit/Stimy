# GameHub (Stimy) - Backend de Microservicios

Este repositorio contiene la arquitectura backend de GameHub (Stimy), una plataforma de distribución digital de videojuegos. El sistema está estructurado bajo un modelo de arquitectura distribuida compuesto por 10 microservicios independientes y un componente de enrutamiento perimetral. Cada módulo funciona de manera autónoma con su propio ciclo de vida y archivo de dependencias Maven (pom.xml).

---

## 1. Requisitos Previos del Sistema

Para la correcta ejecución del ecosistema, el entorno local debe cumplir con las siguientes especificaciones:

* Java Development Kit (JDK): Versión 21.
* Gestor de Base de Datos: Laragon (Motor MariaDB o MySQL activo).
* Persistencia: Al abrir el proyecto es necesario tener prendida una base de datos como Laragon para permitir la conexión de los repositorios y la ejecución de los scripts data.sql de inicialización.

---

## 2. Matriz de Puertos y Enrutamiento Estático

Toda interacción externa proveniente de clientes o Postman se centraliza a través del API Gateway en el puerto 8080. Este componente redirige las peticiones de manera estática hacia los siguientes puertos locales:

* api-gateway -> Puerto 8080 (Puerta de enlace perimetral)
* videojuego-service -> Puerto 8091 (Ruta: /api/videojuegos/**)
* usuario-service -> Puerto 8092 (Ruta: /api/usuarios/**)
* carrito-service -> Puerto 8093 (Ruta: /api/carritos/**)
* deseo-service -> Puerto 8094 (Ruta: /api/deseos/**)
* logro-service -> Puerto 8095 (Ruta: /api/logros/**)
* resenia-service -> Puerto 8096 (Ruta: /api/resenias/**)
* soporte-service -> Puerto 8097 (Ruta: /api/soporte/**)
* pago-service -> Puerto 8098 (Ruta: /api/pagos/**)
* biblioteca-service -> Puerto 8099 (Ruta: /api/bibliotecas/**)

---

## 3. Guía de Ejecución y Despliegue

Siga este orden secuencial para levantar el ecosistema correctamente y evitar excepciones de red durante las pruebas de integración:

1. Paso 1: Inicie los servicios de Laragon y verifique que el motor de base de datos esté corriendo.
2. Paso 2: Primero iniciamos la App de Api Gateway (Puerto 8080) para habilitar el punto de entrada perimetral.
3. Paso 3: Consecutivamente se inician los demás microservicios de lógica de negocio desde sus respectivas clases principales.

---

## 4. Endpoints Críticos para Evidencia Técnica (Postman)

Las pruebas deben ejecutarse apuntando exclusivamente al puerto del Gateway (8080).

### A. Verificación de la Tienda (Catálogo de Videojuegos)
Método GET enviado a la ruta del servicio de videojuegos. Retorna una respuesta con la colección completa de los videojuegos disponibles en la plataforma.
* Método: GET
* URL: http://localhost:8080/api/videojuegos

### B. Consulta de Biblioteca por Usuario
Método GET enviado a la ruta del servicio de biblioteca. Retorna los juegos disponibles asociados de forma específica al usuario ID que se ingrese en la URL.
* Método: GET
* URL: http://localhost:8080/api/bibliotecas/{usuarioId}

---

## 5. Tecnologías Aprendidas

Durante el desarrollo y la reestructuración de este proyecto, se asimilaron e implementaron las siguientes tecnologías clave de arquitectura distribuida:

* **Eureka Server:** Entendimiento conceptual de los servidores de descubrimiento de instancias de red para el registro dinámico de microservicios.
* **API Gateway:** Implementación de una puerta de enlace perimetral centralizada para gestionar el enrutamiento estático de peticiones externas, control de rutas semánticas y abstracción de los puertos internos del ecosistema.
* **DTOs (Data Transfer Objects):** Diseño y uso de objetos de transferencia de datos independientes para desacoplar las entidades de la base de datos de la lógica expuesta en los endpoints, optimizando la comunicación inter-servicio.

---

## 6. Estándares de Diseño

* Arquitectura Interna: Implementación del patrón por capas CSR (Controller - Service - Repository) para asegurar la separación de responsabilidades.
* Validación y Consistencia: Uso de Jakarta Bean Validation (@Valid) en los controladores para la restricción de datos de entrada.
