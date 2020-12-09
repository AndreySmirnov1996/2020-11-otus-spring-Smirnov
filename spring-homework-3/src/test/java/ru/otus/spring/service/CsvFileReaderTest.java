package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_QUESTIONS_NUMBER;

@ExtendWith(MockitoExtension.class)
class CsvFileReaderTest {

    @InjectMocks
    CsvFileReader fileReader;
    @Mock
    private MessageSource messageSource;
    @Mock
    private AppProps props;

    @Test
    void readFile() {
        when(messageSource.getMessage("filename", null, props.getLocale())).thenReturn("questions_en.csv");
        List<Question> questions = fileReader.readFile();
        assertEquals(DEFAULT_QUESTIONS_NUMBER, questions.size());
        questions.forEach(f -> assertNotNull(f.getQuestion()));
    }
}