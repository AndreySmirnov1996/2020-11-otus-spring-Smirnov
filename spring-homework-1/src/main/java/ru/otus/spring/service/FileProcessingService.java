package ru.otus.spring.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileProcessingService {
    private final FileReader fileReader;
    private final QuestionOutputService questionOutputService;

    public void processing(){
        fileReader.readFile();
        questionOutputService.showQuestions();
    }

}
