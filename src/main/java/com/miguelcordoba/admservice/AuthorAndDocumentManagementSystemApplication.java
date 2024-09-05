package com.miguelcordoba.admservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(basePackages = "com.miguelcordoba.admservice.*")
@EnableJpaRepositories(basePackages = "com.miguelcordoba.admservice.persistence.repository")
@EnableKafka
public class AuthorAndDocumentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorAndDocumentManagementSystemApplication.class, args);
    }

}
