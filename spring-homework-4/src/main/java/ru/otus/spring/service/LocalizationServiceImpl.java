package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProps;

@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private final AppProps props;

    @Override
    public String getLocalMessage(String name, Object... params) {
        return messageSource.getMessage(name, params, props.getLocale());
    }

}
