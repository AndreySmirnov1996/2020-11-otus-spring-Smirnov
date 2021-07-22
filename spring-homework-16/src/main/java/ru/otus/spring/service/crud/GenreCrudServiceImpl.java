package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutputFormatter;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public void saveGenre(String name) {
        genreRepository.save(Genre.builder()
                .name(name)
                .build());
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllGenres() {
        genreRepository.findAll().forEach(genre -> ioService.printString(outputFormatter.formatGenre(genre)));
    }
}
