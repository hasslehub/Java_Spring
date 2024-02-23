package com.example.ApiGateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Главный класс приложения API-шлюза.
 */
@SpringBootApplication
//@EnableDiscoveryClient // настроили Spring Cloud Gateway для работы с Eureka
public class ApiGateWay {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateWay.class, args);
	}

	/**
	 * Маршруты для маршрутизатора.
	 *
	 * @param builder построитель маршрутов
	 * @return объект RouteLocator набор маршрутов
	 */
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				//.route("tasks", r -> r.path("/**").uri("http://localhost:8080/"))
				.route("rest", r -> r.path("/**").uri("http://localhost:8081/"))
				.build();
	}

}
