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


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"));
    }


    @Bean
    @Lazy(false)
    public Set<SwaggerUrl> apis(RouteLocator locator, SwaggerUiConfigProperties swaggerUiProperties) {
        Set<SwaggerUrl> urls = new HashSet<>();

        // Primero: Forzamos la carga de rutas y las imprimimos
        locator.getRoutes().toStream().forEach(route -> {
            System.out.println("[DEBUG] Ruta encontrada: " + route.getId());

            String rawId = route.getId();
            if (rawId == null || !rawId.contains("-route")) return; // Solo procesamos las rutas que definimos

            String name = rawId.replace("-service", "").replace("-route", "").toUpperCase();

            SwaggerUrl swaggerUrl = new SwaggerUrl();
            swaggerUrl.setName(name);

            // CUIDADO AQUÍ: Si tu ruta es "biblioteca", aquí estás buscando "bibliotecas" (con s)
            // Asegúrate de que la URL sea EXACTAMENTE la que devuelve el JSON del servicio
            String url = "/api/" + name.toLowerCase() + "s/v3/api-docs";
            swaggerUrl.setUrl(url);

            System.out.println("[DEBUG] Agregando a Swagger: " + name + " -> " + url);
            urls.add(swaggerUrl);
        });

        if (urls.isEmpty()) {
            System.err.println("[ERROR] No se encontraron rutas para el Swagger.");
        }

        swaggerUiProperties.setUrls(urls);
        return urls;
    }
}