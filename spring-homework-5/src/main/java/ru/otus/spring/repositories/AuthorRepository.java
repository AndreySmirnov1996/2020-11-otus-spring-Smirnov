package ru.otus.spring.repositories;

import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    void save(Author author);

    void saveAll(List<Author> authorList);

    Optional<Author> findById(long id);

    List<Author> findAllUsed();

    List<Author> findAllByBookId(long id);
}
