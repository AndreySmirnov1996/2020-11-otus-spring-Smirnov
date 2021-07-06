package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.MongoBook;

@Service
@Slf4j
public class ShowBookService {

    public MongoBook doHappyBirthday(MongoBook mongoBook) {
        log.info(mongoBook.toString());
        return mongoBook;
    }
}
