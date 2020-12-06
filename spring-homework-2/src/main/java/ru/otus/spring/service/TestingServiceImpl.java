package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestingServiceImpl implements TestingService {

    private final QuestionDao questionDao;
    private final TestUserService testUserService;
    private final UserHandler userHandler;


    public void processing() {
        userHandler.startInteraction();
        testUserService.testing(questionDao.findAll());
        userHandler.endInteraction();
    }

}
