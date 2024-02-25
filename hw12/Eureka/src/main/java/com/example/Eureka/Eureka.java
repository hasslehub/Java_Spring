package com.example.Eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Главный класс приложения Eureka-сервера.
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka {

	public static void main(String[] args) {
		SpringApplication.run(Eureka.class, args);
	}

}
