package ru.otus.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import ru.otus.spring.config.IntegrationConfig;
import ru.otus.spring.domain.App;
import ru.otus.spring.service.CustomerSelector;

import java.util.Collection;

import static ru.otus.spring.Main.generateTechnicalTaskItem;


@SpringBootTest(classes = {IntegrationConfig.class, CustomerSelector.class})
@ComponentScan
@IntegrationComponentScan
@EnableIntegration
@DisplayName("Процесс разработки должен ")
public class IntegrationFlowTest {

    @Autowired
    private QueueChannel appChannel;
    @Autowired
    private MessageChannel technicalTaskChannel;

    @DisplayName("корректно переводить ТЗ в продакшен")
    @Test
    void flowTest() {
        technicalTaskChannel.send(new GenericMessage<>(generateTechnicalTaskItem(1)));
        Message<?> receiveMessage = appChannel.receive(10000);
        Assertions.assertNotNull(receiveMessage);
        Assertions.assertNotNull(receiveMessage.getPayload());
        Collection<App> apps = (Collection<App>) receiveMessage.getPayload();
        Assertions.assertEquals(generateTechnicalTaskItem(1).getItemName(), apps.iterator().next().getName());
    }
}
