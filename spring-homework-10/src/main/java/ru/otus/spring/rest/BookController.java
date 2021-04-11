package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookCrudService bookCrudService;

    @GetMapping("/api/book")
    public List<BookDto> getAllBooks() {
        return bookCrudService.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @PostMapping("/api/book")
    public void saveBook(@RequestBody BookDto book) {
        bookCrudService.save(book.toBook());
    }
}
