package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.rest.dto.BookDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/api/book")
    public Flux<BookDto> all() {
        return bookRepository.findAll().map(BookDto::toDto);
    }

    @PostMapping("/api/book")
    public Mono<Book> saveBook(@RequestBody BookDto book) {
        return bookRepository.save(book.toBook());
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> deleteBook(@PathVariable("id") String id) {
        return bookRepository.deleteById(id);
    }
}
