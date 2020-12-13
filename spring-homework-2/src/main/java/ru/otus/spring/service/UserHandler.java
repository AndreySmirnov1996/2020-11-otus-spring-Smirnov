package ru.otus.spring.service;

import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

public interface UserHandler {
    User readUserData();

    void showTestResult(TestResult restResult);
}
