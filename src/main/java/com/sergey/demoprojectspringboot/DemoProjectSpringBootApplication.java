package com.sergey.demoprojectspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoProjectSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectSpringBootApplication.class, args);
    }

}
