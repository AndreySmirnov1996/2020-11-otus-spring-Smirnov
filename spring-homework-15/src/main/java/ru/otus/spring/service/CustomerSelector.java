package ru.otus.spring.service;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.App;

import java.util.Random;

@Service
public class CustomerSelector implements MessageSelector {

    @Override
    public boolean accept(Message<?> message) {
        Random random = new Random();
        boolean notAcceptAppFlag = random.nextBoolean();
        System.out.println("Customer doesn't accept app ? - " + notAcceptAppFlag);

        return notAcceptAppFlag;
    }

}
