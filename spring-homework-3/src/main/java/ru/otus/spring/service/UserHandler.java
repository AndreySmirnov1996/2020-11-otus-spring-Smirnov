package ru.otus.spring.service;

import ru.otus.spring.domain.User;

public interface UserHandler {
    void startInteraction();

    void endInteraction();

    User getUser();
}
