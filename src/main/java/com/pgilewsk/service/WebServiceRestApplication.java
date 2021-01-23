package com.pgilewsk.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories("com.pgilewsk.service.repository.PersonRepository")
@SpringBootApplication
public class WebServiceRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceRestApplication.class, args);
    }

}
