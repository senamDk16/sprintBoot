package com.davibilapps.learnsmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LearnSmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSmartApplication.class, args);
    }

}
