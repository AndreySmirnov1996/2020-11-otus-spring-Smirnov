package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

public interface OutputFormatter {
    String formatQuestion(Question question);

    String formatResult(User user);
}
