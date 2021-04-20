package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/api/book")
    public Flux<Book> all() {
        return bookRepository.findAll();
    }

    @PostMapping("/api/book")
    public void saveBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @DeleteMapping("/api/book/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        bookRepository.deleteById(id);
    }
}
