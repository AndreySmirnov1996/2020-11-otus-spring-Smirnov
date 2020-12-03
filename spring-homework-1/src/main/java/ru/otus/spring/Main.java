package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.TestingServiceImpl;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestingServiceImpl testingService = context.getBean(TestingServiceImpl.class);
        testingService.processing();
    }
}
