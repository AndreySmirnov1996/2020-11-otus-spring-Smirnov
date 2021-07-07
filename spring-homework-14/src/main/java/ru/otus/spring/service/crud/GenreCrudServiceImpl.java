package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.MongoGenre;
import ru.otus.spring.repositories.MongoGenreRepository;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {
    private final MongoGenreRepository mongoGenreRepository;

    @Override
    public void saveGenre(String name) {
        mongoGenreRepository.save(new MongoGenre(name));
    }
}
