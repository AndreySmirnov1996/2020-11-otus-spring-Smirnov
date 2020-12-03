package ru.otus.spring.service;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

public class QuestionPrinterImpl implements QuestionPrinter{

    @Override
    public String printQuestion(Question question) {
        int i = 1;
        StringBuilder sbAnswers = new StringBuilder();
        sbAnswers.append(question.getQuestion())
                .append("\n");

        for (Answer answer : question.getAnswers()) {
            sbAnswers.append(i)
                    .append(")")
                    .append(answer.getBody())
                    .append("  ");
            i++;
        }

        return sbAnswers.toString();
    }
}
