package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.MongoAuthor;
import ru.otus.spring.repositories.MongoAuthorRepository;
import ru.otus.spring.repositories.MongoBookRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorCrudServiceImpl implements AuthorCrudService {

    private final MongoAuthorRepository mongoAuthorRepository;
    private final MongoBookRepository mongoBookRepository;

    @Override
    public void saveAuthor(String name, String surName) {
        mongoAuthorRepository.save(new MongoAuthor(name, surName));
    }

    @Override
    public void deleteAuthorById(String id) {
        if (mongoBookRepository.existsBooksByMongoAuthorsId(id)) {
            log.error("Author has books in library, please firstly delete all his books!");
        }
        mongoAuthorRepository.deleteById(id);
    }
}
