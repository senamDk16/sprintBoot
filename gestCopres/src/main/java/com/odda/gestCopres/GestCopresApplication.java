package com.odda.gestCopres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestCopresApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestCopresApplication.class, args);
	}

}
