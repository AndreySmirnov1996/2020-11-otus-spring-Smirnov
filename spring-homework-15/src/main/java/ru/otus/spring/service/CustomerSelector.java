package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.App;

import java.util.Random;

@Service
@Slf4j
public class CustomerSelector implements MessageSelector {

    @Override
    public boolean accept(Message<?> message) {
        Random random = new Random();
        boolean notAcceptAppFlag = random.nextBoolean();
        App app = (App)message.getPayload();
        log.info("Customer accept " + app.getName() + "? - " + (notAcceptAppFlag ?  "NO" : "YES"));
        return notAcceptAppFlag;
    }

}
