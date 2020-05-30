package com.webcoder.products.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudyProductsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyProductsAppApplication.class, args);
	}

}
