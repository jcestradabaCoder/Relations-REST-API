package com.jc.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReviewRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewRestApiApplication.class, args);
	}
}