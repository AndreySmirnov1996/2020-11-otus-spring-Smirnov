package ru.otus.spring;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.domain.App;
import ru.otus.spring.domain.TechnicalTask;

import java.util.Collection;

@MessagingGateway
public interface ItCompany {

    @Gateway(requestChannel = "technicalTaskChannel", replyChannel = "appChannel")
    Collection<App> process(Collection<TechnicalTask> orderItem);
}
