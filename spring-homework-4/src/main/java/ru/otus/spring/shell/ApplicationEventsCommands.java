package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.UserHandler;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final UserHandler userHandler;

    private User user;

    @ShellMethod(value = "Login command", key = {"login"})
    public void login(@ShellOption(defaultValue = "AnyName") String name,
                      @ShellOption(defaultValue = "AnySurname") String surName) {
        user = userHandler.readUserData();
    }

//    @ShellMethod(value = "Publish event command", key = {"p", "pub", "publish"})
//    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
//    public String publishEvent() {
//        eventsPublisher.publish();
//        return "Событие опубликовано";
//    }


    private Availability isUserLoginSuccess() {
        return user == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }
}
