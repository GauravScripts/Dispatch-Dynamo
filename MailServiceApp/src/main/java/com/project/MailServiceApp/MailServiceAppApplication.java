package com.project.MailServiceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MailServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailServiceAppApplication.class, args);
	}

}
