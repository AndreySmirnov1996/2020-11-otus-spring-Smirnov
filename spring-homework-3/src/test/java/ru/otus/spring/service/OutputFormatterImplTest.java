package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;
import ru.otus.spring.util.TestObjectFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_QUESTIONS_NUMBER;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_TEST_RESULT;

@ExtendWith(MockitoExtension.class)
class OutputFormatterImplTest {

    @InjectMocks
    private OutputFormatterImpl outputFormatter;

    @Test
    void formatQuestion() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("value1", true));
        answerList.add(new Answer("value2", false));
        Question question = new Question("question", answerList);
        assertEquals("question\n1)value1  2)value2  ", outputFormatter.formatQuestion(question));
    }

    @Test
    void formatResult() {
        TestResult testResult = TestResult.builder()
                .user(new User("name", "surName"))
                .questionsNumber(DEFAULT_QUESTIONS_NUMBER)
                .testResult(DEFAULT_TEST_RESULT)
                .build();

        assertEquals("Dear name surName, your result is 5 right answers from 5 questions.",
                outputFormatter.formatResult(testResult));
    }
}