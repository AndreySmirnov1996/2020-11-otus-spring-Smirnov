package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class Question {
    String question;
    HashMap<String, Boolean> answers;
}
