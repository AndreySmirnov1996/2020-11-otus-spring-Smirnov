package ru.otus.spring.service.crud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.utils.ThreadUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCrudHystrixProxy {

    private final BookCrudServiceImpl bookCrudService;

    @HystrixCommand(commandKey = "books", fallbackMethod = "deleteBookByIdFallback")
    public boolean deleteBookById(long bookId) {
        log.info("Before sleep");
        ThreadUtils.sleepRandomly(10000);
        log.info("After sleep");
        return bookCrudService.deleteBookById(bookId);
    }

    public boolean deleteBookByIdFallback(long id) {
        log.info("Fallback method called");
        return false;
    }

}
