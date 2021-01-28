package com.java.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class RateManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateManagmentSystemApplication.class, args);
	}

}
