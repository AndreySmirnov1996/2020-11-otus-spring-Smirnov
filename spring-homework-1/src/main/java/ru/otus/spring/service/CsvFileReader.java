package ru.otus.spring.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class CsvFileReader implements FileReader {

    private final String fileName;

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
                            .body(values[j])
                            .value(Integer.parseInt(values[j + 1]) == 1)
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
