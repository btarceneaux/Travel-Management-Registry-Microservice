package com.travel.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TravelRegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelRegistryServiceApplication.class, args);
	}

}
