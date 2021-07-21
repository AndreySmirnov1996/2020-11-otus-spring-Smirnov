package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.App;
import ru.otus.spring.domain.TechnicalTask;

@Service
public class DevelopService {

    public App developApp(TechnicalTask task) throws InterruptedException {
        System.out.println("Analytic, Develop, Testing, Deploy of " + task.getItemName());
        Thread.sleep(1000);
        System.out.println("App: " + task.getItemName() + " is DONE");
        return new App(task.getItemName());
    }

    public App refactoringApp(App app) throws InterruptedException {
        System.out.println("Refactoring of " + app.getName());
        Thread.sleep(1000);
        System.out.println("Refactoring: " + app.getName() + " is DONE");
        return app;
    }
}
