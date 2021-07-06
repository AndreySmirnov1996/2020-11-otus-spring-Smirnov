package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.MongoGenre;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutputFormatter;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final GenreRepository genreRepository;

    @Override
    public void saveGenre(String name) {
        genreRepository.save(new MongoGenre(name));
    }

    @Override
    public void showAllGenres() {
        genreRepository.findAll().forEach(genre -> ioService.printString(outputFormatter.formatGenre(genre)));
    }
}
