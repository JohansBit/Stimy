# 1. Usamos la imagen ligera oficial de Java 21 para el contenedor
FROM openjdk:21-slim

# 2. Definimos el directorio de trabajo interno en el contenedor
WORKDIR /app

# 3. Copiamos el archivo JAR compilado por Maven dentro de la imagen
COPY target/soporte-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Exponemos el puerto oficial asignado a este microservicio (8097)
EXPOSE 8097

# 5. Comando definitivo para arrancar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]