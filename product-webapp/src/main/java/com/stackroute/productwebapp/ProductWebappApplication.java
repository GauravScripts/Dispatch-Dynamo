package com.stackroute.productwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductWebappApplication.class, args);
	}

}
