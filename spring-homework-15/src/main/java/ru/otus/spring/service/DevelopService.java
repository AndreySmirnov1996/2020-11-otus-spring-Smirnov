package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.App;
import ru.otus.spring.domain.TechnicalTask;

@Service
public class DevelopService {

    public App develop(TechnicalTask task) throws InterruptedException {
        System.out.println("Analytic, Develop, Testing, Deploy of " + task.getItemName());
        Thread.sleep(2000);
        System.out.println("App " + task.getItemName() + " DONE");
        return new App(task.getItemName());
    }
}
