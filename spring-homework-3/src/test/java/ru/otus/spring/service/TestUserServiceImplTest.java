package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.util.TestObjectFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    @Mock
    private MessageSource messageSource;
    @Mock
    private AppProps props;


    @Test
    void userTesting() {
        when(ioService.readString()).thenReturn(INPUT_ANSWER);
        int testResult = testUserService.testing(TestObjectFactory.createQuestionList(1, 1));
        assertEquals(TEST_RESULT, testResult);
    }
}