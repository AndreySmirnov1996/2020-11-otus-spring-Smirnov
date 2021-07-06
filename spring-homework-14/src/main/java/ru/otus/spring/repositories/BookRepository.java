package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.MongoBook;

import java.util.List;

public interface BookRepository extends MongoRepository<MongoBook, String> {

    List<MongoBook> findByAuthorsId(String id);

    boolean existsBooksByAuthorsId(String authorId);
}
