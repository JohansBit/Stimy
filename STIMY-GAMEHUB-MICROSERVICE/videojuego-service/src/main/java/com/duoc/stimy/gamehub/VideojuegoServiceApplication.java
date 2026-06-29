package com.duoc.stimy.gamehub;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class VideojuegoServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(VideojuegoServiceApplication.class, args);
	}
}