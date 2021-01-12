package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.TestUserService;
import ru.otus.spring.service.UserHandler;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationTestingCommands {

    private final QuestionDao questionDao;
    private final UserHandler userHandler;
    private final TestUserService testUserService;

    private TestResult testResult;
    private User user;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public void login(@ShellOption(defaultValue = "AnyName") String name,
                      @ShellOption(defaultValue = "AnySurname") String surName) {
        user = userHandler.readUserData();
    }

    @ShellMethod(value = "Test", key = {"t", "test"})
    @ShellMethodAvailability(value = "isUserLoginSuccess")
    public void test() {
        List<Question> questions = questionDao.findAll();
        int testRes = testUserService.testing(questions);
        testResult = new TestResult(user, questions.size(), testRes);
    }

    @ShellMethod(value = "Show result", key = {"sr", "show result"})
    @ShellMethodAvailability(value = "isUserTesting")
    public void showResult() {
        userHandler.showUserTestResult(testResult);
    }

    private Availability isUserLoginSuccess() {
        return user == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }

    private Availability isUserTesting() {
        return testResult == null ? Availability.unavailable("Сначала пройдите тестирование") : Availability.available();
    }
}
