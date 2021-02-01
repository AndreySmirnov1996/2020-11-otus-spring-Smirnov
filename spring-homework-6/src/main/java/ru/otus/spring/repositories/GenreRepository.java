package ru.otus.spring.repositories;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository {
    void save(Genre genre);

    List<Genre> findAll();
}
