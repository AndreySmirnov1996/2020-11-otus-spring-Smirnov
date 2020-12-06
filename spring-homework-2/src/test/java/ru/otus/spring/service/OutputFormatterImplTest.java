package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;
import ru.otus.spring.ustil.TestObjectFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutputFormatterImplTest {

    @InjectMocks
    private OutputFormatterImpl outputFormatter;
    @Mock
    private QuestionDao questionDao;

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
        when(questionDao.findAll()).thenReturn(TestObjectFactory.createQuestionList(5, 2));
        User user = new User("name", "surName");
        user.setTestResult(5);
        assertEquals("Dear name surName, your result is 5 right answers from 5 questions.",
                outputFormatter.formatResult(user));
    }
}