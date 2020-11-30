package ru.otus.spring.data;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionStorage {
    List<Question> questionList;

    public QuestionStorage() {
        questionList = new ArrayList<>();
    }
}
