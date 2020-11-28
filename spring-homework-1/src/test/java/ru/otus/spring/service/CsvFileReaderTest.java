package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.QuestionDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvFileReaderTest {

    @Test
    void readFile() {
        QuestionDao questionDao = new QuestionDao();
        FileReader fileReader = new CsvFileReader(questionDao, "questions.csv");
        fileReader.readFile();
        assertEquals(5, questionDao.getQuestionList().size());
        questionDao.getQuestionList().forEach(f -> assertNotNull(f.getQuestion()));
    }
}