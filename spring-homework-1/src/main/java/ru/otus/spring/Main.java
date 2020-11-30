package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.FileProcessingService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        FileProcessingService fileProcessingService = context.getBean(FileProcessingService.class);
        fileProcessingService.processing();
    }
}
