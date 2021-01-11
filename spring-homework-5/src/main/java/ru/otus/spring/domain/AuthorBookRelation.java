package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AuthorBookRelation {

    private final long authorId;
    private final long bookId;
}
