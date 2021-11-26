package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreCrudService {

    void saveGenre(String name);

    List<Genre> findAllGenres();
}
