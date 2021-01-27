package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Author {

    private long id;

    private final String name;
    private final String surname;
}
