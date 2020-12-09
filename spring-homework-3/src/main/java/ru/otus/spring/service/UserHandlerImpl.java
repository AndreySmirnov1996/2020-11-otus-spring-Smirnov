package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserHandlerImpl implements UserHandler {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;

    @Override
    public User readUserData() {
        ioService.printString("Input your name: ");
        String userName = ioService.readString();
        ioService.printString("Input your surname: ");
        String userSurname = ioService.readString();
        return new User(userName, userSurname);
    }

    public void showTestResult(TestResult restResult) {
        ioService.printStringNewLine(outputFormatter.formatResult(restResult));
    }
}
