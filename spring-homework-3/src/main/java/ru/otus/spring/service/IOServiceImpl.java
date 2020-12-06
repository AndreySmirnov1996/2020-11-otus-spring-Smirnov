package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@Slf4j
public class IOServiceImpl implements IOService {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public final void printStringNewLine(String s) {
        System.out.println(s);
    }

    @Override
    public final void printString(String s) {
        System.out.print(s);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }
}
