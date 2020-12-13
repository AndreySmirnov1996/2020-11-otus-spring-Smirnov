package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.service.TestingService;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Main {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        TestingService testingService = context.getBean(TestingService.class);
        testingService.processing();
    }
}
