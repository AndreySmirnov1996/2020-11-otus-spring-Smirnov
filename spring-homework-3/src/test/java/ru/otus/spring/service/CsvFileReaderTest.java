package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CsvFileReaderTest {
    private static final int EXPECTED_NUMBER_OF_QUESTIONS = 5;

    @Test
    void readFile() {
        AppProps appProps = new AppProps();
        appProps.setFileName("questions.csv");
        FileReader fileReader = new CsvFileReader(appProps);
        List<Question> questions = fileReader.readFile();
        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, questions.size());
        questions.forEach(f -> assertNotNull(f.getQuestion()));
    }
}