package com.Application2.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@SpringBootApplication
@EnableFeignClients
public class Application2Application {

	public static void main(String[] args) {
		SpringApplication.run(Application2Application.class, args);
	}

}
