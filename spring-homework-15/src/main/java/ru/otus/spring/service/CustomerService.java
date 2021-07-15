package ru.otus.spring.service;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.App;

import java.util.Random;

@Service
public class CustomerService implements MessageSelector {

    @Override
    public boolean accept(Message<?> message) {
        Random random = new Random();
        boolean acceptAppFlag = random.nextBoolean();
        System.out.println("Customer accept '" + message.getPayload() + "' ? " + acceptAppFlag);

        return acceptAppFlag;
    }

}
