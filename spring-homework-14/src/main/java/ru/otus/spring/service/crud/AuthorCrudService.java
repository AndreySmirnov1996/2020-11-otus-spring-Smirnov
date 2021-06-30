package ru.otus.spring.service.crud;

public interface AuthorCrudService {

    void saveAuthor(String name, String surName);

    void showAuthorById(String id);

    void showAll();

    void deleteAuthorById(String id);
}
