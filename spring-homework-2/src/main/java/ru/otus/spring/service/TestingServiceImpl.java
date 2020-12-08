package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestingServiceImpl implements TestingService {

    private final QuestionDao questionDao;
    private final TestUserService testUserService;
    private final UserHandler userHandler;


    public void processing() {
        User user = userHandler.readUserData();
        List<Question> questions = questionDao.findAll();
        int testResult = testUserService.testing(questions);
        userHandler.showTestResult(new TestResult(user, questions.size(), testResult));
    }

}
