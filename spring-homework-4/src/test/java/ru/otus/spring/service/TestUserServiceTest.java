package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.util.TestObjectFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Сервис TestUserService должен")
@SpringBootTest
class TestUserServiceTest {

    private static final int TEST_RESULT = 1;
    private static final String INPUT_ANSWER = "1";

    @Autowired
    private TestUserService testUserService;
    @MockBean
    private IOService ioService;

    @DisplayName("возвращать результат тестирования.")
    @Test
    void userTesting() {
        when(ioService.readString()).thenReturn(INPUT_ANSWER);
        int testResult = testUserService.testing(TestObjectFactory.createQuestionList(1, 1));
        assertEquals(TEST_RESULT, testResult);
    }
}