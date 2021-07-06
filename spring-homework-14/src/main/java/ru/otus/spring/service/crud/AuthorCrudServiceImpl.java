package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.MongoAuthor;
import ru.otus.spring.repositories.MongoAuthorRepository;
import ru.otus.spring.repositories.MongoBookRepository;
import ru.otus.spring.service.IOService;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorCrudServiceImpl implements AuthorCrudService {

    private final MongoAuthorRepository mongoAuthorRepository;
    private final MongoBookRepository mongoBookRepository;
    private final IOService ioService;

    @Override
    public void showAll() {
        mongoAuthorRepository.findAll().forEach(author -> ioService.printStringNewLine(author.toString()));
    }

    @Override
    public void saveAuthor(String name, String surName) {
        mongoAuthorRepository.save(new MongoAuthor(name, surName));
    }

    @Override
    public void showAuthorById(String id) {
        mongoAuthorRepository.findById(id)
                .ifPresent(author -> ioService.printString(author.toString()));
    }

    @Override
    public void deleteAuthorById(String id) {
        if (mongoBookRepository.existsBooksByMongoAuthorsId(id)) {
            log.error("Author has books in library, please firstly delete all his books!");
        }
        mongoAuthorRepository.deleteById(id);
    }
}
