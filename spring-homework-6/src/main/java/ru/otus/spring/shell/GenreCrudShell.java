package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutputFormatter;


@ShellComponent
@RequiredArgsConstructor
public class GenreCrudShell {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final GenreRepository genreRepository;


    @ShellMethod(value = "Show all genres", key = {"sag", "show all genres"})
    public void showAllGenres() {
        genreRepository.findAll().forEach(genre -> ioService.printString(outputFormatter.formatGenre(genre)));
    }


    @ShellMethod(value = "Save genre (example: sg new_genre)", key = {"sg", "save genre"})
    public void saveGenre(@ShellOption String name) {
        genreRepository.save(Genre.builder()
                .name(name)
                .build());
    }
}
