package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Builder
@Getter
@RequiredArgsConstructor
public class Question {
    private final String question;
    private final List<Answer> answers;
}
