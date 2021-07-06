package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.MongoGenre;
import ru.otus.spring.repositories.MongoGenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutputFormatter;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final MongoGenreRepository mongoGenreRepository;

    @Override
    public void saveGenre(String name) {
        mongoGenreRepository.save(new MongoGenre(name));
    }

    @Override
    public void showAllGenres() {
        mongoGenreRepository.findAll().forEach(genre -> ioService.printString(outputFormatter.formatGenre(genre)));
    }
}
