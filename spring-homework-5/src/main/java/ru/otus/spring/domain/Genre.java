package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Genre {

    public Genre(long id) {
        this.id = id;
        this.name = null;
    }

    @Setter
    private long id;
    private final String name;
}
