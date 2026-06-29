# 1. Usamos la imagen ligera oficial de Java 21
FROM openjdk:21-slim

# 2. Definimos el directorio de trabajo interno en el contenedor
WORKDIR /app

# 3. Copiamos el JAR que genera Maven al compilar el gateway
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# 4. El Gateway corre en el puerto 8080, así que lo exponemos
EXPOSE 8080

# 5. Comando para iniciar el enrutador
ENTRYPOINT ["java", "-jar", "app.jar"]