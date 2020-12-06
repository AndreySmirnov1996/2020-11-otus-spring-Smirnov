package ru.otus.spring.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.User;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserHandlerImpl implements UserHandler {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    @Getter
    private User user;

    @Override
    public void startInteraction() {
        ioService.printString("Input your name: ");
        String userName = ioService.readString();
        ioService.printString("Input your surname: ");
        String userSurname = ioService.readString();
        user = new User(userName, userSurname);
    }

    @Override
    public void endInteraction() {
        ioService.printStringNewLine(outputFormatter.formatResult(user));
    }
}
