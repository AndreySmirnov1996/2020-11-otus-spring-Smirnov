package ru.otus.spring.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private final Scanner scanner;
    private final PrintStream printStream;

    public IOServiceImpl(InputStream inputStream, OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = (PrintStream) outputStream;
    }

    @Override
    public final void printStringNewLine(String s) {
        printStream.println(s);
    }

    @Override
    public final void printString(String s) {
        printStream.print(s);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }
}
