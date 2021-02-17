package ru.otus.spring.service.crud;

import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.IOService;

@Service
@RequiredArgsConstructor
public class AuthorCrudServiceImpl implements AuthorCrudService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final IOService ioService;

    @Override
    public void saveAuthor(String name, String surName) {
        authorRepository.save(new Author(name, surName));
    }

    @Override
    public void showAuthorById(String id) {
        authorRepository.findById(id)
                .ifPresent(author -> ioService.printString(author.toString()));
    }

    @Override
    public void deleteAuthorById(String id) {
        if (!bookRepository.findByAuthorsId(id).isEmpty()) {
            throw new MongoException("Author has books in library, please firstly delete all his books!");
        }
        authorRepository.deleteById(id);
    }
}
