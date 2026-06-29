# 🎮 Stimy GameHub - Plataforma de Microservicios

¡Bienvenido al repositorio oficial de **Stimy GameHub**! Este sistema ha sido diseñado y estructurado bajo una arquitectura de microservicios robusta utilizando **Spring Boot (Java)**. En esta versión final, el ecosistema se encuentra centralizado mediante un **POM Padre** y su despliegue ha sido completamente automatizado utilizando **Docker**.

---

## 📋 Requisitos del Sistema
Antes de ejecutar el proyecto, asegúrate de contar con las siguientes herramientas instaladas en tu entorno local:
* **Java Development Kit (JDK):** Versión 21 (Obligatorio).
* **Docker Desktop:** Esencial para el levantamiento automatizado con contenedores (asegúrate de tenerlo abierto antes de iniciar).
* **Git:** Para la gestión de ramas y sincronización del código.
* **IDE Recomendado:** IntelliJ IDEA (con soporte para plugins de Maven y Lombok).
* *Nota: No se requiere Laragon ni bases de datos PostgreSQL nativas en Windows si se utiliza el despliegue por Docker.*

---

## 🏗️ Estándares de Diseño y Arquitectura
El desarrollo de Stimy GameHub se rige bajo patrones de diseño modernos para asegurar scalabilidad y desacoplamiento:
* **Arquitectura Orientada a Microservicios (SOA):** Cada dominio de la aplicación cuenta con su propia lógica y persistencia aislada.
* **Patrón API Gateway:** Centraliza el acceso perimetral al ecosistema, gestionando la seguridad y el ruteo.
* **Service Discovery (Descubrimiento Dinámico):** Permite que los servicios se localicen entre sí sin necesidad de hardcodear direcciones IP.
* **DTO (Data Transfer Objects):** Encapsulamiento estricto de los datos en tránsito entre las capas de controlador y servicio.
* **Patrón Repositorio:** Soportado por Spring Data JPA para la abstracción de consultas hacia la base de datos PostgreSQL.

---

## 📊 Matriz de Puertos y Servicios

El ecosistema distribuye sus responsabilidades y contenedores a través de la siguiente asignación de red local:

| Componente / Servicio | Puerto Local / Rango | Descripción |
| :--- | :--- | :--- |
| **gamehub-postgres** | `5432` | Base de datos PostgreSQL relacional centralizada. |
| **gamehub-eureka** | `8761` | Servidor de Descubrimiento (Eureka Server). |
| **gamehub-gateway** | `8080 - 8089` | API Gateway Perimetral (Asignación elástica mediante `random.int`). |
| **videojuego-service** | `8091` | Microservicio Núcleo: Catálogo central de videojuegos. |
| **usuario-service** | `8092` | Microservicio Núcleo: Registro y gestión de perfiles de usuario. |
| **carrito-service** | `8093` | Microservicio Satélite: Gestión temporal de compras. |
| **deseo-service** | `8094` | Microservicio Satélite: Lista de deseos y seguimiento. |
| **logro-service** | `8095` | Microservicio Satélite: Sistema de recompensas y trofeos. |
| **resenia-service** | `8096` | Microservicio Satélite: Sistema de calificaciones y comunidad. |
| **soporte-service** | `8097` | Microservicio Satélite: Postventa y tickets de ayuda. |
| **pago-service** | `8098` | Microservicio Satélite: Pasarela transaccional. |
| **biblioteca-service** | `8099` | Microservicio Satélite: Biblioteca de juegos adquiridos. |

---Tecnologías Aprendidas e Implementadas
A lo largo del desarrollo de esta plataforma se han consolidado competencias clave en el desarrollo de software empresarial:

Ecosistema Java 21 y Spring Cloud: Uso de características modernas de LTS de Java junto con Eureka Server y Spring Cloud Gateway.

Contenedorización con Docker: Creación de entornos replicables y aislados mediante multi-staging y Docker Compose con healthchecks avanzados.

Maven Multimódulo: Configuración de proyectos complejos a través de un archivo POM jerárquico que hereda dependencias y automatiza compilaciones.

Ruteo y Balanceo Elástico: Manejo de propiedades dinámicas en Spring Boot para flexibilizar la infraestructura de red en tiempo de ejecución.

Integración con Bases de Datos en Red: Sincronización de variables de entorno para la comunicación persistente entre contenedores Docker aislados.

## 🚀 Guía de Ejecución y Despliegue

### 🐳 Opción 1: Despliegue Automatizado (Recomendado)
Para levantar el ecosistema completo en fila india sin preocuparse por configuraciones manuales, abre tu terminal (CMD, PowerShell o Git Bash) en la raíz del proyecto y ejecuta:

```bash
docker compose up --build