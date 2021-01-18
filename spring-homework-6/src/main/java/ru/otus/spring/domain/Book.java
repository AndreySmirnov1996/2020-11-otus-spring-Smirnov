package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
@Builder
public class Book {
    private final long id;

    private final String title;
    private final Genre genre;
    private final List<Author> authors;
}
