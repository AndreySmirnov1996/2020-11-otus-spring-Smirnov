package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.MongoBook;

import java.util.List;

public interface MongoBookRepository extends MongoRepository<MongoBook, String> {

    List<MongoBook> findByMongoAuthorsId(String id);

    boolean existsBooksByMongoAuthorsId(String authorId);
}
