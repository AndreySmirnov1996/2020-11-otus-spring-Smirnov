package ru.otus.spring.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
@ConstructorBinding
@Getter
public class AppProps {
    private final String fileName;
    private final Locale locale;

    public AppProps(Locale locale, String fileNameTemplate) {
        this.locale = locale;
        this.fileName = String.format(fileNameTemplate, locale);
    }
}
