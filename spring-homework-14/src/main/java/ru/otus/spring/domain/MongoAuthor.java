package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class MongoAuthor {

    @Id
    private String id;

    private String name;
    private String surname;

    public MongoAuthor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
