package ru.otus.spring.repositories;

import ru.otus.spring.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    void save(AuthorEntity author);

    void saveAll(List<AuthorEntity> authorList);

    Optional<AuthorEntity> findById(long id);
}
