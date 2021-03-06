package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_QUESTIONS_NUMBER;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_TEST_RESULT;

@DisplayName("Сервис OutputFormatter должен")
@SpringBootTest
class OutputFormatterTest {
    private static final String DEFAULT_ANSWER_VALUE_1 = "value1";
    private static final String DEFAULT_ANSWER_VALUE_2 = "value2";
    private static final Boolean DEFAULT_ANSWER_IS_RIGHT = true;

    @Autowired
    private OutputFormatter outputFormatter;

    @DisplayName("форматировать вопрос.")
    @Test
    void formatQuestion() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer(DEFAULT_ANSWER_VALUE_1, DEFAULT_ANSWER_IS_RIGHT));
        answerList.add(new Answer(DEFAULT_ANSWER_VALUE_2, !DEFAULT_ANSWER_IS_RIGHT));
        Question question = new Question("question", answerList);
        assertEquals("question\n1)value1  2)value2  ", outputFormatter.formatQuestion(question));
    }
}