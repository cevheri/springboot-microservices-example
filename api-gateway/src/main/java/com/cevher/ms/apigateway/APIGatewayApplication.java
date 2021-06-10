package com.cevher.ms.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class APIGatewayApplication {
	// We used @EnableEurekaServer and @EnableHystrix
	// See application.yml and bootstrap.yml
	public static void main(String[] args) {
		SpringApplication.run(APIGatewayApplication.class, args);
	}

}
