package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

@Service
@RequiredArgsConstructor
public class OutputFormatterImpl implements OutputFormatter {

    private final QuestionDao questionDao;

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
    public String formatResult(User user) {
        return "Dear " + user.getName() + " " + user.getSurName() +
                ", your result is " + user.getTestResult() + " right answers from " +
                questionDao.findAll().size() + " questions.";
    }
}
