package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_USER_NAME;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_USER_SURNAME;

@DisplayName("Cервис UserHandler должен")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class UserHandlerTest {

    @Autowired
    private UserHandler userHandler;
    @MockBean
    private IOService ioService;

    @DisplayName("считать имя и фамилию пользователя.")
    @Test
    void readUserData() {
        when(ioService.readString()).thenReturn(DEFAULT_USER_NAME).thenReturn(DEFAULT_USER_SURNAME);
        User user = userHandler.readUserData();

        assertEquals(DEFAULT_USER_NAME, user.getName());
        assertEquals(DEFAULT_USER_SURNAME, user.getSurName());
    }
}