package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements UserHandler {

    private final MessageSource messageSource;
    private final AppProps props;
    private final IOService ioService;

    @Override
    public User readUserData() {
        ioService.printString(messageSource.getMessage("input.name", null,  props.getLocale()));
        String userName = ioService.readString();
        ioService.printString(messageSource.getMessage("input.surname", null,  props.getLocale()));
        String userSurname = ioService.readString();
        return new User(userName, userSurname);
    }

    public void showUserTestResult(TestResult restResult) {
        String outputResult = messageSource.getMessage("output.result",
                new String[]{restResult.getUser().getName(), restResult.getUser().getSurName(),
                        String.valueOf(restResult.getTestResult()), String.valueOf(restResult.getQuestionsNumber())},
                props.getLocale());
        ioService.printStringNewLine(outputResult);
    }
}
