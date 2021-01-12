package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.otus.spring.base.DefaultConstant.DEFAULT_QUESTIONS_NUMBER;

@DisplayName("Сервис CsvFileReader должен")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class CsvFileReaderTest {

    @Autowired
    private CsvFileReader fileReader;

    @DisplayName("считать вопросы из csv файла.")
    @Test
    void readFile() {
        List<Question> questions = fileReader.readFile();
        assertEquals(DEFAULT_QUESTIONS_NUMBER, questions.size());
        questions.forEach(f -> assertNotNull(f.getQuestion()));
    }
}