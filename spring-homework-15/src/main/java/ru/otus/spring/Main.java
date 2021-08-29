package ru.otus.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.spring.domain.App;
import ru.otus.spring.domain.TechnicalTask;
import ru.otus.spring.integration.ItCompany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@SpringBootApplication
@IntegrationComponentScan
@EnableIntegration
@Slf4j
public class Main {
    private static final String[] PROJECTS = {"web site", "backend app", "console app"};


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        ItCompany company = ctx.getBean(ItCompany.class);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        pool.execute(() -> {
            Collection<TechnicalTask> items = generateTechnicalTaskItems();
            log.info("New technical tasks: " +
                    items.stream().map(TechnicalTask::getItemName)
                            .collect(Collectors.joining(",")));
            Collection<App> app = company.process(items);
            log.info("Ready all app: " + app.stream()
                    .map(App::getName)
                    .collect(Collectors.joining(",")));
        });


    }


    public static TechnicalTask generateTechnicalTaskItem(int index) {
        return new TechnicalTask(PROJECTS[index]);
    }

    public static Collection<TechnicalTask> generateTechnicalTaskItems() {
        List<TechnicalTask> items = new ArrayList<>();
        for (int i = 0; i < PROJECTS.length; i++) {
            items.add(generateTechnicalTaskItem(i));
        }
        return items;
    }
}
