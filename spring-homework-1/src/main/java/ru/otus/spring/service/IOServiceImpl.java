package ru.otus.spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class IOServiceImpl implements IOService {

    @Override
    public final void printString(String s) {
        System.out.println(s);
    }

    //TODO Читает строку из консоли
    @Override
    public String readString() {
        return null;
    }
}
