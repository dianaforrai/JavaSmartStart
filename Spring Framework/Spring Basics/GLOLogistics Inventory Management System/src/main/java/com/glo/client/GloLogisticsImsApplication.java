package com.glo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.glo")
@EntityScan("com.glo.model")
@EnableJpaRepositories("com.glo.repository")
public class GloLogisticsImsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GloLogisticsImsApplication.class, args);
    }
}