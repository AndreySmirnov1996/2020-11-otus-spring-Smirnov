package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutputFormatter;


@ShellComponent
public class GenreCrudCommands {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;

    private final GenreRepository genreRepository;


    @Autowired
    public GenreCrudCommands(IOService ioService,
                             OutputFormatter outputFormatter,
                             GenreRepository genreRepository) {
        this.ioService = ioService;
        this.outputFormatter = outputFormatter;
        this.genreRepository = genreRepository;
    }


    @ShellMethod(value = "Show all genres", key = {"sag", "show all genres"})
    public void showAllGenres() {
        genreRepository.findAll().forEach(genre -> ioService.printString(outputFormatter.formatGenre(genre)));
    }


    @ShellMethod(value = "Save genre", key = {"sg", "save genre"})
    public void saveGenre(@ShellOption long id, @ShellOption String name) {
        genreRepository.save(new Genre(id, name));
    }
}
