package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface OutputFormatter {
    String formatQuestion(Question question);
}
