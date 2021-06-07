package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
