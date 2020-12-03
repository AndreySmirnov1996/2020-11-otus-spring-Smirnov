package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;
import ru.otus.spring.service.FileReader;

import java.util.List;

public class QuestionCsvDao implements QuestionDao {

    private final FileReader fileReader;

    public QuestionCsvDao(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public List<Question> findAll() {
        return fileReader.readFile();
    }
}
