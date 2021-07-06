package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CleanUpService {

    void cleanUp() {
        log.info("cleanUp do nothing");
        //TODO clean mongo data base

    }

}
