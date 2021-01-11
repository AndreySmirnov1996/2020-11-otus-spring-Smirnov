package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
@Builder
public class Author {

    private final long id;

    private final String name;
    private final String surname;
    private final String phone;
    private final List<Book> books;
}
