package com.searchManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class SearchManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchManagementServiceApplication.class, args);
	}

}
