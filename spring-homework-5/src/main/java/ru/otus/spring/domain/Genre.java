package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Genre {

    public Genre(long id) {
        this.id = id;
        this.name = null;
    }

    private final long id;
    private final String name;
}
