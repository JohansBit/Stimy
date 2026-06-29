package com.duoc.stimy.gamehub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerGatewayConfig {

    // 1. EL TRUCO MAESTRO: Este Bean obliga a Swagger a usar la URL del Gateway para los botones
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"));
    }

    // 2. Tu método dinámico limpio (sin métodos raros que tiren error)
    @Bean
    @Lazy(false)
    public Set<SwaggerUrl> apis(RouteLocator locator, SwaggerUiConfigProperties swaggerUiProperties) {
        Set<SwaggerUrl> urls = new HashSet<>();

        try {
            locator.getRoutes()
                    .filter(route -> route.getId() != null)
                    .subscribe(route -> {
                        String rawId = route.getId();
                        System.out.println("[SWAGGER-DETECTIVE] Ruta original: -> " + rawId);

                        String cleanName = rawId.replace("-service", "")
                                .replace("-route", "")
                                .toUpperCase()
                                .trim();

                        String apiEndpoint = cleanName.toLowerCase();

                        // Forzamos los plurales exactos de tus controladores
                        if (apiEndpoint.contains("usuario")) apiEndpoint = "usuarios";
                        else if (apiEndpoint.contains("videojuego")) apiEndpoint = "videojuegos";
                        else if (apiEndpoint.contains("carrito")) apiEndpoint = "carritos";
                        else if (apiEndpoint.contains("resenia")) apiEndpoint = "resenias";
                        else if (apiEndpoint.contains("biblioteca")) apiEndpoint = "bibliotecas";
                        else if (apiEndpoint.contains("deseo")) apiEndpoint = "deseos";
                        else if (apiEndpoint.contains("logro")) apiEndpoint = "logros";
                        else if (apiEndpoint.contains("pago")) apiEndpoint = "pagos";

                        SwaggerUrl swaggerUrl = new SwaggerUrl();
                        swaggerUrl.setName(cleanName);

                        String finalUrl = "/api/" + apiEndpoint + "/v3/api-docs";
                        swaggerUrl.setUrl(finalUrl);

                        System.out.println("[SWAGGER-DETECTIVE] URL Limpia inyectada: -> " + finalUrl);
                        urls.add(swaggerUrl);
                    });

            swaggerUiProperties.setUrls(urls);
        } catch (Exception e) {
            System.err.println("[SWAGGER-GATEWAY] Error inicializando rutas de Swagger: " + e.getMessage());
        }

        return urls;
    }
}