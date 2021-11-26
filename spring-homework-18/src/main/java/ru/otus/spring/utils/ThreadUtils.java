package ru.otus.spring.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j(topic = "hystrix")
public class ThreadUtils {

    public static void sleepRandomly(int ms) {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum == 3) {
            log.info("It is a chance for demonstrating Hystrix action");
            try {
                log.info("Start sleeping.... {}", System.currentTimeMillis());
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                log.error("Hystrix thread interupted.... {}", System.currentTimeMillis());
            }
            log.info("End sleeping.... {}", System.currentTimeMillis());
        }
    }
}
