package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements UserHandler {

    private final LocalizationService localizationService;
    private final IOService ioService;

    @Override
    public User readUserData() {
        ioService.printString(localizationService.getLocalMessage("input.name"));
        String userName = ioService.readString();
        ioService.printString(localizationService.getLocalMessage("input.surname"));
        String userSurname = ioService.readString();
        return new User(userName, userSurname);
    }

    public void showUserTestResult(TestResult restResult) {
        String outputResult = localizationService.getLocalMessage("output.result",
                restResult.getUser().getName(), restResult.getUser().getSurName(),
                        String.valueOf(restResult.getTestResult()), String.valueOf(restResult.getQuestionsNumber()));
        ioService.printStringNewLine(outputResult);
    }
}
