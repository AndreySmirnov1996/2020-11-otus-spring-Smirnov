package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.spring.dao.QuestionDao;

import java.util.Map;

@AllArgsConstructor
public class QuestionOutputService {
    private final QuestionDao questionDao;

    void showQuestions() {
        questionDao.getQuestionList().forEach(question -> {
            System.out.println(question.getQuestion());

            int i = 1;
            for (Map.Entry<String, Boolean> answer : question.getAnswers().entrySet()) {
               System.out.print(i + ")" + answer.getKey() + "  ");
                i++;
            }
            System.out.println();
        });
    }

}
