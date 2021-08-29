package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.App;
import ru.otus.spring.domain.TechnicalTask;

@Service
@Slf4j
public class DevelopService {

    public App developApp(TechnicalTask task) throws InterruptedException {
        log.info("Analytic, Develop, Testing, Deploy of " + task.getItemName());
        Thread.sleep(1000);
        log.info("Analytic, Develop, Testing, Deploy of " + task.getItemName() + " is DONE");
        return new App(task.getItemName());
    }

    public App refactoringApp(App app) throws InterruptedException {
        log.info("Refactoring of " + app.getName());
        Thread.sleep(1000);
        log.info("Refactoring of " + app.getName() + " is DONE");
        return app;
    }
}
