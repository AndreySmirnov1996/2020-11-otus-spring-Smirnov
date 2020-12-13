package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;

@Service
public class OutputFormatterImpl implements OutputFormatter {

    @Override
    public String formatQuestion(Question question) {
        StringBuilder sbAnswers = new StringBuilder();
        sbAnswers.append(question.getQuestion())
                .append("\n");

        int i = 1;
        for (Answer answer : question.getAnswers()) {
            sbAnswers.append(i)
                    .append(")")
                    .append(answer.getValue())
                    .append("  ");
            i++;
        }
        return sbAnswers.toString();
    }

    @Override
    public String formatResult(TestResult restResult) {
        return "Dear " + restResult.getUser().getName() + " " + restResult.getUser().getSurName() +
                ", your result is " + restResult.getTestResult() + " right answers from " +
                restResult.getQuestionsNumber() + " questions.";
    }
}
