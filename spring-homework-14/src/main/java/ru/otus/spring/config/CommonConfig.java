package ru.otus.spring.config;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceImpl;

@EnableMongock
@Configuration
public class CommonConfig {
    @Bean
    IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }
}
