package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {

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
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
}
