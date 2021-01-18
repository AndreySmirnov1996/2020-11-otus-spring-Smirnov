package ru.otus.spring.repositories;

import ru.otus.spring.domain.GenreEntity;

import java.util.List;

public interface GenreRepository {
    void save(GenreEntity genre);

    List<GenreEntity> findAll();
}
