package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.MongoAuthor;

public interface AuthorRepository extends MongoRepository<MongoAuthor, String> {
}
