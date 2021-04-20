package ru.otus.spring.service.crud;

import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreCrudService {

    void saveGenre(String name);

    Flux<Genre> findAllGenres();
}
