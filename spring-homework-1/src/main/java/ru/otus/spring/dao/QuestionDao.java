package ru.otus.spring.dao;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionDao {

    private List<Question> questionList;

    public QuestionDao() {
        questionList = new ArrayList<>();
    }

    public void showQuestions(){
        questionList.forEach(question -> {
            System.out.println(question.getQuestion());
            final int[] i = {1};
            question.getAnswers().forEach((s, aBoolean) -> {
                System.out.print(i[0] + ")" + s + "  " );
                i[0]++;
            });
            System.out.println();
        });
    }
}
