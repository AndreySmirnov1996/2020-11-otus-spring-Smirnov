package ru.otus.spring.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class CsvFileReader implements FileReader {

    private final QuestionDao questionDao;
    private final String fileName;

    public void readFile() {
        List<Question> questions = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResourceAsStream(fileName))))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                HashMap<String, Boolean> answersFile = new HashMap<>();

                for (int j = 1; j < values.length; j += 2) {
                    answersFile.put(values[j], Integer.parseInt(values[j + 1]) == 1);
                }

                Question question = Question.builder()
                        .question(values[0])
                        .answers(answersFile)
                        .build();
                questions.add(question);
            }
        } catch (CsvValidationException | IllegalArgumentException | IOException e) {
            log.error("Something going wrong! Please check logs:");
            e.printStackTrace();
            return;
        }
        questionDao.setQuestionList(questions);
    }

}
