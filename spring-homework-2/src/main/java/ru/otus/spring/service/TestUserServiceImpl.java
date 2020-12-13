package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestUserServiceImpl implements TestUserService {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;

    @Override
    public int testing(List<Question> questions) {
        int result = 0;

        for (Question question : questions) {
            int index;
            do {
                try {
                    ioService.printStringNewLine(outputFormatter.formatQuestion(question));
                    ioService.printString("Please, input your answer: ");
                    index = Integer.parseInt(ioService.readString());
                    index--;
                    result += question.getAnswers().get(index).getIsRight() ? 1 : 0;
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    ioService.printStringNewLine("Your input is incorrect. Please, try again.");
                    index = -1;
                }
            } while (index == -1);
        }
        return result;
    }
}
