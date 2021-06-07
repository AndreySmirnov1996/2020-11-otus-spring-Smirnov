package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Веб приложение библиотека должно")
@SpringBootTest
class WebApplicationTests {

    @DisplayName("корректно запускать контекст")
    @Test
    void contextLoads() {
    }

}