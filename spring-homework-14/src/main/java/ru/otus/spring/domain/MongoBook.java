package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class MongoBook {

    @Id
    private String id;

    private String title;
    @DBRef
    private MongoGenre mongoGenre;
    @DBRef
    private List<MongoAuthor> mongoAuthors;

    public MongoBook(String title, MongoGenre mongoGenre, MongoAuthor... mongoAuthors) {
        this.title = title;
        this.mongoGenre = mongoGenre;
        this.mongoAuthors = Arrays.asList(mongoAuthors);
    }
}
