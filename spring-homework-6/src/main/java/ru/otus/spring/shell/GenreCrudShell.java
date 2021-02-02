package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.crud.GenreCrudService;


@ShellComponent
@RequiredArgsConstructor
public class GenreCrudShell {

    private final GenreCrudService genreCrudService;

    @ShellMethod(value = "Show all genres", key = {"sag", "show all genres"})
    public void showAllGenres() {
        genreCrudService.showAllGenres();
    }


    @ShellMethod(value = "Save genre (example: sg new_genre)", key = {"sg", "save genre"})
    public void saveGenre(@ShellOption String name) {
        genreCrudService.saveGenre(name);
    }
}
