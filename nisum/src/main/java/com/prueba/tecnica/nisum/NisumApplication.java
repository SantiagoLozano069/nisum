package com.prueba.tecnica.nisum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NisumApplication {

	public static void main(String[] args) {
		SpringApplication.run(NisumApplication.class, args);
	}

}
