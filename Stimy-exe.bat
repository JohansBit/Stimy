@echo off
@echo =================================================
@echo        INICIANDO ECOSISTEMA STIMY
@echo =================================================

cd /d "C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE"

set "JAVA_HOME=C:\Users\ACER\.jdks\ms-21.0.11"

set "BASE_DIR=C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE"

echo [1/3] Despertando al jefe (Eureka Server)
taskkill /F /IM java.exe /T >nul 2>&1
timeout /t 3 /nobreak
start "Eureka Server" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\eureka-server && mvnw spring-boot:run"
timeout /t 15 /nobreak

echo [2/3] Levantando el puente (Api Gateway)...
start "Api Gateway" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\api-gateway && mvnw spring-boot:run"
timeout /t 15 /nobreak

echo [3/3] Levantando el ecosistema (Microservicios de Negocio)...

start "Usuario Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\usuario-service && mvnw spring-boot:run"
start "Videojuego Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\videojuego-service && mvnw spring-boot:run"
start "Carrito Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\carrito-service && mvnw spring-boot:run"
start "Resenia Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\resenia-service && mvnw spring-boot:run"
start "Soporte Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\soporte-service && mvnw spring-boot:run"
start "Pago Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\pago-service && mvnw spring-boot:run"
start "Biblioteca Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\biblioteca-service && mvnw spring-boot:run "
start "Deseo Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\deseo-service && mvnw spring-boot:run"
start "Logro Service" cmd /k "cd /d C:\Users\ACER\IdeaProjects\Stimy\STIMY-GAMEHUB-MICROSERVICE\logro-service && mvnw spring-boot:run"

echo ===================================================
echo  ¡TODOS LOS MODULOS ESTAN CORRIENDO! 
echo  Levantando el panel de Eureka en el Navegador
echo ===================================================

start http://localhost:8761
timeout /t 45 /nobreak

echo Abriendo Schazenigger...
start http://localhost:8081/webjars/swagger-ui/index.html

echo Todo listo. Ecosistema levantado. 