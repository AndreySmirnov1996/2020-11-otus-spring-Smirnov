package ru.otus.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.domain.App;
import ru.otus.spring.domain.TechnicalTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@IntegrationComponentScan
//@SuppressWarnings({ "resource", "Duplicates", "InfiniteLoopStatement" })
@Slf4j
@ComponentScan
@Configuration
@EnableIntegration
public class Main {
    private static final String[] PROJECTS = {"web site", "backend app", "console app"};

    @Bean
    public QueueChannel technicalTaskChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel appChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow developFlow() {
        return IntegrationFlows.from("technicalTaskChannel")
                .split()
                .handle("developService", "develop")
                .aggregate()
                .channel("appChannel")
                .get();
    }


    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

        // here we works with cafe using interface
        ItCompany company = ctx.getBean(ItCompany.class);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        pool.execute(() -> {
            Collection<TechnicalTask> items = generateTechnicalTaskItems();
            System.out.println("New technical task: " +
                    items.stream().map(TechnicalTask::getItemName)
                            .collect(Collectors.joining(",")));
            Collection<App> app = company.process(items);
            System.out.println("Ready app: " + app.stream()
                    .map(App::getName)
                    .collect(Collectors.joining(",")));
        });


    }


    private static TechnicalTask generateTechnicalTaskItem(int index) {
        return new TechnicalTask(PROJECTS[index]);
    }

    private static Collection<TechnicalTask> generateTechnicalTaskItems() {
        List<TechnicalTask> items = new ArrayList<>();
        for (int i = 0; i < PROJECTS.length; i++) {
            items.add(generateTechnicalTaskItem(i));
        }
        return items;
    }
}
