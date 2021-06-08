package com.cevher.ms.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServerApplication {
	// We used @EnableEurekaClient and @EnableConfigServer
	// See application.yml
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
