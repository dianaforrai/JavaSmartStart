package com.glo.config;

import com.glo.client.Client;
import com.glo.repository.FastFreightRepository;
import com.glo.repository.FastFreightRepositoryImpl;
import com.glo.service.FastFreightService;
import com.glo.service.FastFreightServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.glo")
public class AppConfig {

    @Bean
    public FastFreightRepository fastFreightRepository() {
        return new FastFreightRepositoryImpl();
    }

    @Bean
    public FastFreightService fastFreightService(FastFreightRepository fastFreightRepository) {
        return new FastFreightServiceImpl(fastFreightRepository);
    }

    @Bean
    public Client client(FastFreightService freightService) {
        return new Client(freightService);
    }
}
