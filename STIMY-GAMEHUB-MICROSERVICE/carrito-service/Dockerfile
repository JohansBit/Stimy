# 1. Partimos de la imagen ligera oficial de Java 21
FROM openjdk:21-slim

# 2. Definimos el directorio de trabajo interno
WORKDIR /app

# 3. Copiamos el JAR compilado por Maven al contenedor
COPY target/carrito-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Declaramos el puerto estático correspondiente al carrito (8093)
EXPOSE 8093

# 5. Comando ejecutor para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]