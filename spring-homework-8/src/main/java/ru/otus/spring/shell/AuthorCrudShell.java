package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.crud.AuthorCrudService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCrudShell {

    private final AuthorCrudService authorCrudService;

    @ShellMethod(value = "Save author (example: sa Name1 Surname1)", key = {"sa"})
    void saveAuthor(String name, String surName) {
        authorCrudService.saveAuthor(name, surName);
    }

    @ShellMethod(value = "Show author by id (example: said 111)", key = {"said"})
    public void showAuthorById(String authorId) {
        authorCrudService.showAuthorById(authorId);
    }

    @ShellMethod(value = "Show authors (example: saa)", key = {"saa"})
    public void showAllAuthors() {
        authorCrudService.showAll();
    }

    @ShellMethod(value = "Delete author by id (example: da 111)", key = {"da"})
    public void deleteAuthorById(@ShellOption String authorId) {
        authorCrudService.deleteAuthorById(authorId);
    }

}
