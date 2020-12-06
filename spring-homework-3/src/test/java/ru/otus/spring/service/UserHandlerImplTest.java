package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ru.otus.spring.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserHandlerImplTest {

    private static final String NAME = "name";
    private static final String SURNAME = "surName";

    @InjectMocks
    private UserHandlerImpl userHandler;
    @Mock
    private IOService ioService;

    @Test
    void startInteraction() {

        when(ioService.readString()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 1) {
                    return SURNAME;
                }
                return NAME;
            }
        });

        userHandler.startInteraction();
        User user = userHandler.getUser();

        assertEquals(NAME, user.getName());
        assertEquals(SURNAME, user.getSurName());
        assertNull(user.getTestResult());
    }
}