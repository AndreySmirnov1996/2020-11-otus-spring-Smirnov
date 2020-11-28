package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.service.CsvFileReader;
import ru.otus.spring.service.FileReader;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        FileReader fileReader = context.getBean(CsvFileReader.class);
        fileReader.readFile();

        QuestionDao questionDao = context.getBean(QuestionDao.class);
        questionDao.showQuestions();
    }
}
