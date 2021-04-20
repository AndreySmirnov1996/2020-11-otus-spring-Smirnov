package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorCrudServiceImpl implements AuthorCrudService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void saveAuthor(String name, String surName) {
        authorRepository.save(new Author(name, surName));
    }

    @Override
    public Mono<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        if (bookRepository.existsBooksByAuthorsId(id)) {
            log.error("Author has books in library, please firstly delete all his books!");
            return;
        }
        authorRepository.deleteById(id);
    }
}
