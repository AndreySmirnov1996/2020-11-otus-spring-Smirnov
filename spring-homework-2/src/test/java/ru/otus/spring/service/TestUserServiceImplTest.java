package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.User;
import ru.otus.spring.ustil.TestObjectFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestUserServiceImplTest {

    @InjectMocks
    private TestUserServiceImpl testUserService;
    @Mock
    private IOService ioService;
    @Mock
    private OutputFormatter outputFormatter;
    @Mock
    private UserHandler userHandler;


    @Test
    void userTesting() {
        User user = new User("name", "surName");
        when(ioService.readString()).thenReturn("1");
        when(userHandler.getUser()).thenReturn(user);
        testUserService.testing(TestObjectFactory.createQuestionList(1, 1));
        assertEquals(1, user.getTestResult());
    }
}