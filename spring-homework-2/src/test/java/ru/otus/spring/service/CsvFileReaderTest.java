package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_QUESTIONS_NUMBER;

@ExtendWith(MockitoExtension.class)
class CsvFileReaderTest {

    @Test
    void readFile() {
        FileReader fileReader = new CsvFileReader("questions.csv");
        List<Question> questions = fileReader.readFile();
        assertEquals(DEFAULT_QUESTIONS_NUMBER, questions.size());
        questions.forEach(f -> assertNotNull(f.getQuestion()));
    }
}