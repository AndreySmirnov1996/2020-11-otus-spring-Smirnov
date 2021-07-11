package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.H2Author;
import ru.otus.spring.domain.H2Book;
import ru.otus.spring.domain.H2Genre;
import ru.otus.spring.domain.MongoBook;

import java.util.stream.Collectors;

@Service
@Slf4j
public class TransformBookService {

    public H2Book doH2BookFromMongoBook(MongoBook mongoBook) {
        return H2Book.builder()
                .id(mongoBook.getId())
                .title(mongoBook.getTitle())
                .h2Genre(new H2Genre(mongoBook.getMongoGenre().getId(), mongoBook.getMongoGenre().getName()))
                .h2Authors(mongoBook.getMongoAuthors().stream()
                        .map(ma -> new H2Author(ma.getId(), ma.getName(), ma.getSurname()))
                        .collect(Collectors.toList()))
                .build();
    }
}
