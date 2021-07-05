package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;

@Service
@Slf4j
public class ShowBookService {

    public Book doHappyBirthday(Book book) {
        log.info(book.toString());
        return book;
    }
}
