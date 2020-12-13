package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.User;
import ru.otus.spring.util.TestObjectFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_USER_NAME;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_USER_SURNAME;

@ExtendWith(MockitoExtension.class)
class TestUserServiceImplTest {

    private static final int TEST_RESULT = 1;
    private static final String INPUT_ANSWER = "1";

    @InjectMocks
    private TestUserServiceImpl testUserService;
    @Mock
    private IOService ioService;
    @Mock
    private OutputFormatter outputFormatter;


    @Test
    void userTesting() {
        when(ioService.readString()).thenReturn(INPUT_ANSWER);
        int testResult = testUserService.testing(TestObjectFactory.createQuestionList(1, 1));
        assertEquals(TEST_RESULT, testResult);
    }
}