package ru.otus.spring.service.crud;

public interface AuthorCrudService {

    void saveAuthor(String name, String surName);

    void deleteAuthorById(String id);
}
