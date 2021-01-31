package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.GenreEntity;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutputFormatter;


@ShellComponent
@RequiredArgsConstructor
public class GenreCrudService {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final GenreRepository genreRepository;


    @ShellMethod(value = "Show all genres", key = {"sag", "show all genres"})
    @Transactional(readOnly = true)
    public void showAllGenres() {
        genreRepository.findAll().forEach(genre -> ioService.printString(outputFormatter.formatGenre(genre)));
    }


    @ShellMethod(value = "Save genre (example: sg new_genre)", key = {"sg", "save genre"})
    @Transactional
    public void saveGenre(@ShellOption String name) {
        genreRepository.save(GenreEntity.builder()
                .name(name)
                .build());
    }
}
