package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import ru.otus.spring.service.CustomerService;

@Configuration
public class IntegrationConfig {

    @Autowired
    private CustomerService customerService;

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
                //.channel("goToDevelopBack")
                .handle("developService", "develop")
                .aggregate()
                .channel(appChannel())
                .get();
    }
}
