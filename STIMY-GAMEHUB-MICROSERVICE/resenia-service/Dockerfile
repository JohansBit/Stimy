# Etapa 1: Compilación
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
# Copiamos el código fuente y el pom.xml
COPY pom.xml .
COPY src ./src
# Compilamos saltando los tests para ir más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución (ligera)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el JAR generado desde la etapa de compilación
COPY --from=build /app/target/resenia-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8096
ENTRYPOINT ["java", "-jar", "app.jar"]