package ru.otus.spring.confog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
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
                .channel("goToDevelopBack")
                .handle("developService", "develop")
//                .routeToRecipients(router -> router.recipientMessageSelector("goToDevelopBack", customerService)
//                        .defaultOutputToParentFlow())
                .aggregate()
                .channel("appChannel")
                .get();
    }
}
