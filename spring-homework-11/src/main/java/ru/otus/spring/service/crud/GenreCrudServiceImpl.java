package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {

    private final GenreRepository genreRepository;

    @Override
    public void saveGenre(String name) {
        genreRepository.save(new Genre(name));
    }

    @Override
    public Flux<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
}
