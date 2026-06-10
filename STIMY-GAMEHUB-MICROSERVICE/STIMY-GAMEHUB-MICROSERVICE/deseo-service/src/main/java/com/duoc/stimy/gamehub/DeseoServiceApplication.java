package com.duoc.stimy.gamehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DeseoServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeseoServiceApplication.class, args);
	}
}