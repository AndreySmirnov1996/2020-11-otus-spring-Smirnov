package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.spring.dao.QuestionDao;

@AllArgsConstructor
public class QuestionOutputService {
    private final QuestionDao questionDao;

    void showQuestions() {
        questionDao.getQuestionList().forEach(question -> {
            System.out.println(question.getQuestion());
            final int[] i = {1};

            question.getAnswers().forEach((s, aBoolean) -> {
                System.out.print(i[0] + ")" + s + "  ");
                i[0]++;
            });
            System.out.println();
        });
    }

}
