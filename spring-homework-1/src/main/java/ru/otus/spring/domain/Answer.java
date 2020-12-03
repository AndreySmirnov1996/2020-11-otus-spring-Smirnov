package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class Answer {
    private final String body;
    private final Boolean value;
}
