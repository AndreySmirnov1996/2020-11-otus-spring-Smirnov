package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.FileReader;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionCsvDao implements QuestionDao {

    private final FileReader fileReader;

    @Override
    public List<Question> findAll() {
        return fileReader.readFile();
    }
}
