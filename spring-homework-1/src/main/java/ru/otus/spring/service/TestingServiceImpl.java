package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.QuestionDao;

@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService{

    private final IOService ioService;
    private final QuestionDao questionDao;
    private final QuestionPrinter questionPrinter;

    private void showQuestions() {
        questionDao.findAll().forEach(question ->
                ioService.printString(questionPrinter.printQuestion(question)));
    }

    public void processing(){
        showQuestions();
    }

}
