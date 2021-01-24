package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceImpl;

@Configuration
public class BeansConfig {
    @Bean
    IOService ioService(){
        return new IOServiceImpl(System.in, System.out);
    }
}
