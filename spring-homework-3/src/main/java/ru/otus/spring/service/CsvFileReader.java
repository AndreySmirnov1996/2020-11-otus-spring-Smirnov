package ru.otus.spring.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@PropertySource("classpath:application.yml")
public class CsvFileReader implements FileReader {

    private final String fileName;

    CsvFileReader(@Value("${file-name}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> readFile() {
        List<Question> questions = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResourceAsStream(fileName))))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                List<Answer> answersList = new ArrayList<>();

                for (int j = 1; j < values.length; j += 2) {
                    answersList.add(Answer.builder()
                            .value(values[j])
                            .isRight(Integer.parseInt(values[j + 1]) == 1)
                            .build());
                }

                Question question = Question.builder()
                        .question(values[0])
                        .answers(answersList)
                        .build();
                questions.add(question);
            }
        } catch (CsvValidationException | IllegalArgumentException | IOException e) {
            log.error("Something going wrong! Please check logs:");
            e.printStackTrace();
            return Collections.emptyList();
        }
        return questions;
    }

}
