package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_USER_NAME;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_USER_SURNAME;

@ExtendWith(MockitoExtension.class)
class UserHandlerImplTest {

    @InjectMocks
    private UserHandlerImpl userHandler;
    @Mock
    private IOService ioService;
    @Mock
    private MessageSource messageSource;
    @Mock
    private AppProps props;

    @Test
    void readUserData() {
        when(ioService.readString()).thenReturn(DEFAULT_USER_NAME).thenReturn(DEFAULT_USER_SURNAME);
        User user = userHandler.readUserData();

        assertEquals(DEFAULT_USER_NAME, user.getName());
        assertEquals(DEFAULT_USER_SURNAME, user.getSurName());
    }
}