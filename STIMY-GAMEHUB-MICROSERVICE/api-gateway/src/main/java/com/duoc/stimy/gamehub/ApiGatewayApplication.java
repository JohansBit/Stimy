package com.duoc.stimy.gamehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
		try {
			SpringApplication.run(ApiGatewayApplication.class, args);
		} catch (Exception e) {
			System.err.println("Error de Gateway: ");
			e.printStackTrace();
			Throwable cause = e.getCause();
			while (cause != null) {
				System.err.println("Causado por: " + cause.getMessage());
				cause = cause.getCause();
			}
		}
	}
}