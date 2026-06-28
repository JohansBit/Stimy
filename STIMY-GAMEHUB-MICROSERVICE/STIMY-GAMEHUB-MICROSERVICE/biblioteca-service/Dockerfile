# 1. Partimos de la imagen ligera oficial de Java 21
FROM openjdk:21-slim

# 2. Definimos el directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos el JAR compilado por Maven desde tu máquina al contenedor
COPY target/biblioteca-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Declaramos el puerto específico que usa este microservicio (8099)
EXPOSE 8099

# 5. Comando ejecutor para iniciar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]