package ru.otus.spring.dao;

import ru.otus.spring.data.QuestionStorage;
import ru.otus.spring.domain.Question;

import java.util.List;

public class QuestionDao {

    private final QuestionStorage questionStorage;

    public QuestionDao(QuestionStorage questionStorage) {
        this.questionStorage = questionStorage;
    }

    public List<Question> getQuestionList() {
        return questionStorage.getQuestionList();
    }

    public void setQuestionList(List<Question> questions) {
        questionStorage.setQuestionList(questions);
    }

}
