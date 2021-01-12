package ru.otus.spring.service;

public interface LocalizationService {
    String getLocalMessage(String name, Object... params) ;
}
