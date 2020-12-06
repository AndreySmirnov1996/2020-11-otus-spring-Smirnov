package ru.otus.spring.ustil;

import lombok.experimental.UtilityClass;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TestObjectFactory {
    public Answer createAnswer(String value, boolean isRight) {
        return new Answer(value, isRight);
    }

    public List<Answer> createAnswerList(int size) {
        List<Answer> answers = new ArrayList<>();
        answers.add(createAnswer("value0", true));
        for (int i = 1; i < size; i++) {
            answers.add(createAnswer("value" + i, false));
        }
        return answers;
    }


    public Question createQuestion(String question, List<Answer> answers) {
        return new Question(question, answers);
    }

    public List<Question> createQuestionList(int size) {
        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            questionList.add(createQuestion("question" + i, createAnswerList(2)));
        }

        return questionList;
    }

}
