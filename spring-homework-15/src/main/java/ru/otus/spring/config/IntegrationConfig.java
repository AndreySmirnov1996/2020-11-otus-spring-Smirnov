package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.domain.App;
import ru.otus.spring.domain.TechnicalTask;
import ru.otus.spring.service.CustomerSelector;
import ru.otus.spring.service.DevelopService;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    private static final String GO_TO_REFACTORING_CHANNEL = "GO_TO_REFACTORING_CHANNEL";

    private final CustomerSelector customerSelector;
    private final DevelopService developService;

    @Bean
    public QueueChannel technicalTaskChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel appChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow developFlow() {
        return IntegrationFlows.from(technicalTaskChannel())
                .split()
                .channel(GO_TO_REFACTORING_CHANNEL)
                .<Object, Class<?>>route(Object::getClass, m -> m
                        .subFlowMapping(TechnicalTask.class, sf -> sf.handle(developService, "developApp"))
                        .subFlowMapping(App.class, sf -> sf.handle(developService, "refactoringApp")))
                .routeToRecipients(route -> route.recipientMessageSelector(GO_TO_REFACTORING_CHANNEL, customerSelector)
                        .defaultOutputToParentFlow())
                .aggregate()
                .channel(appChannel())
                .get();
    }
}
