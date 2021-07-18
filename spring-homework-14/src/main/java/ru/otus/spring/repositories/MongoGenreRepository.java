package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.MongoGenre;

public interface MongoGenreRepository extends MongoRepository<MongoGenre, String> {
}
