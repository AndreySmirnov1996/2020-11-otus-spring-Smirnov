package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    //Object save(Author author);

    void saveAll(List<Author> authorList);

    Optional<Author> findById(long id);
}
