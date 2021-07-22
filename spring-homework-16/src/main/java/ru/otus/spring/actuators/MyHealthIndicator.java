package ru.otus.spring.actuators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.repositories.BookRepository;

@Component
@RequiredArgsConstructor
public class MyHealthIndicator implements HealthIndicator {

    private static final String BOOKS_COUNT_MESSAGE = "Books in library";
    private static final String BOOKS_ZERO = "Library is empty :( ";


    private final BookRepository bookRepository;

    @Override
    public Health health() {
        long count = bookRepository.count();
        return Health.up()
                .status(Status.UP)
                .withDetail(count == 0 ? BOOKS_ZERO : BOOKS_COUNT_MESSAGE, count)
                .build();
    }
}
