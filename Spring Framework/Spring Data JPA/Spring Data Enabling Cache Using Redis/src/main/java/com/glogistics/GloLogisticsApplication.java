package com.glogistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GloLogisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GloLogisticsApplication.class, args);
    }
}
