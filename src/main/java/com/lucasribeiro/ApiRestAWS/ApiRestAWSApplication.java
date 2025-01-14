package com.lucasribeiro.ApiRestAWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ApiRestAWSApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestAWSApplication.class, args);
	}

}
