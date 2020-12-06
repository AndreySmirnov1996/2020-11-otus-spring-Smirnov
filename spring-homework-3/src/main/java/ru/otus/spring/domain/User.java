package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class User {
    private final String name;
    private final String surName;
    @Setter
    private Integer testResult;
}
