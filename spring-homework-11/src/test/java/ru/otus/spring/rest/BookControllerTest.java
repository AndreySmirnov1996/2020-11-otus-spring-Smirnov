package ru.otus.spring.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("REST Контроллер для книг должен")
@SpringBootTest
class BookControllerTest {

    @Autowired
    private BookController controller;

    @DisplayName("Возвращать массив книг")
    @Test
    public void getBooksTest() {
        WebTestClient client = WebTestClient.bindToController(controller).build();

        client.get()
                .uri("/api/book")
                .exchange()
                .expectStatus().isOk();
    }
}