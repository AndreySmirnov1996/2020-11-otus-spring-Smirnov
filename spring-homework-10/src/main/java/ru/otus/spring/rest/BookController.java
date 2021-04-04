package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookCrudService bookCrudService;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookCrudService.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/book/save")
    public void saveBook(@RequestBody BookDto book) {
        bookCrudService.save(book.toBook());
    }
}
