package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CsvFileReaderTest {
    private static final int EXPECTED_NUMBER_OF_QUESTIONS = 5;

    @Captor
    ArgumentCaptor<List<Question>> questionsCaptor;
    @Mock
    private QuestionDao questionDao;

    @Test
    void readFile() {
        FileReader fileReader = new CsvFileReader(questionDao, "questions.csv");
        fileReader.readFile();
        Mockito.verify(questionDao, Mockito.times(1)).setQuestionList(questionsCaptor.capture());

        List<Question> questions = questionsCaptor.getValue();

        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, questions.size());
        questions.forEach(f -> assertNotNull(f.getQuestion()));
    }
}