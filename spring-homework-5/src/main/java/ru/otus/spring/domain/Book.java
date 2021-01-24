package ru.otus.spring.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Book {
    @Setter
    private long id;

    private final String title;
    private final Genre genre;
    private final List<Author> authors;
}
