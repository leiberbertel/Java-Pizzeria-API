package com.leiber.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class LeiberPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeiberPizzeriaApplication.class, args);
	}

}
