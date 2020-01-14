package com.nbenja.springboot.resilience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResilienceRatelimiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResilienceRatelimiterApplication.class, args);
	}

}
