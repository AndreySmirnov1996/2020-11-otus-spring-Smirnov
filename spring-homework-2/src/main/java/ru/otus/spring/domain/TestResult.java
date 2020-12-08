package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class TestResult {
    private final User user;
    private final int questionsNumber;
    private final int testResult;
}
