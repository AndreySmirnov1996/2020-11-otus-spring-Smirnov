package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_QUESTIONS_NUMBER;

@DisplayName("Сервис CsvFileReader должен")
@SpringBootTest
class CsvFileReaderTest {

    @Autowired
    private CsvFileReader fileReader;

    @DisplayName("считать вопросы из csv файла.")
    @Test
    void readFile() {
        List<Question> questions = fileReader.readFile();
        assertEquals(DEFAULT_QUESTIONS_NUMBER, questions.size());
        questions.forEach(f -> assertNotNull(f.getQuestion()));
    }
}