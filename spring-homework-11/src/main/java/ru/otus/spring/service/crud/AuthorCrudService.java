package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Author;

import java.util.Optional;

public interface AuthorCrudService {

    void saveAuthor(String name, String surName);

    Optional<Author> findById(String id);

    void deleteById(String id);
}
