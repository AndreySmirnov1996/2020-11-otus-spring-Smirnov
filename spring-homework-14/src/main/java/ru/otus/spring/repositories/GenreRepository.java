package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.MongoGenre;

public interface GenreRepository extends MongoRepository<MongoGenre, String> {
}
